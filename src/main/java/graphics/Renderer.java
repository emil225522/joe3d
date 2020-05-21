package graphics;

import core.Camera;
import core.GameObject;
import core.KeyInput;
import core.Scene;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import utility.*;

import org.joml.Matrix4f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL43.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * A 3D rendering engine.
 */
public class Renderer {
    private long window;
    private int program;
    KeyInput input = new KeyInput();

    private final String VERT = Const.SHADERS + "bp.vert";
    private final String FRAG = Const.SHADERS + "bp.frag";

    // Buffers and containers for such stuff
    private int vao;

    private List<RenderInfo> renders = new ArrayList<>();

    // Abstract objects
    private Camera cam;
    private Scene scene;
    private Vector3fc cameraDirection = new Vector3f(0, 0, -1);

    // TODO remove shader testing light
    PositionalLight light = new PositionalLight();

    /**
     * Creates an OpenGL 3D renderer, operating in a GLFW-managed window. Throws exception if the scene has no main camera.
     *
     * @param scene the scene to render.
     */
    public Renderer(Scene scene) {
        try {
            this.cam = scene.getMainCamera();
        } catch (NullPointerException e) {
            throw new NullPointerException("Unable to find a main camera in the scene.");
        }
        this.scene = scene;
    }

    /**
     * Initializes the renderer and starts the render loop.
     */
    public void run() {
        init();

        while (!glfwWindowShouldClose(window)) {
            render();
        }
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    /**
     * This initializer method does a few things: <br>
     * <ul>
     *  <li>Initializes GLFW and GL capabilities.</li>
     *  <li>Sets default OpenGL states.</li>
     *  <li>Sets up vertex and index buffers for all loaded renderable objects</li>
     *  <li>Creates a shader program.</li>
     * </ul>
     */
    private void init() {
        // OpenGL and GLFW must-haves
        setupGLFW();
        GL.createCapabilities();

        // Setup a key callback
        glfwSetKeyCallback(window, (window,  key,  scancode,  action,  mods) -> {

            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            else {

                input.read(key, action, mods);

            }


        });

        // Window resize callback
        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
                cam.setPerspective((float) width / (float) height);
            }
        });

        // OpenGL states
        glFrontFace(GL_CCW);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH);
        glEnable(GL_MULTISAMPLE);
        glDepthFunc(GL_LEQUAL);
        glClearColor(0.2f, 0.2f, 0.2f, 1);

        // Setup all the vertices!
        setupBuffers();

        // Create shader program
        program = createProgram(VERT, FRAG);
        glUseProgram(program);
    }

    /**
     * Helper method for setting up GLFW, creating a window and some default hints.
     */
    private void setupGLFW() {
        // GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 16);

        window = glfwCreateWindow(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT, "Joe3D", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        // Center window
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            assert vidmode != null;
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    /**
     * Sets up callbacks such as key inputs and window actions.
     */
    private void setupCallbacks() {

    }

    /**
     * Sets up vertex and element (index) buffers for all objects in the scene.
     */
    private void setupBuffers() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        for (GameObject obj : scene.getObjects()) {
            if (!(obj instanceof RenderObject)) continue;
            RenderObject r = (RenderObject) obj;
            int v = glGenBuffers();
            int t = glGenBuffers();
            int n = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, v);
            glBufferData(GL_ARRAY_BUFFER, r.getMesh().getVertexPositions(), GL_STATIC_DRAW);
            glBindBuffer(GL_ARRAY_BUFFER, t);
            glBufferData(GL_ARRAY_BUFFER, r.getMesh().getVertexTexCoords(), GL_STATIC_DRAW);
            glBindBuffer(GL_ARRAY_BUFFER, n);
            glBufferData(GL_ARRAY_BUFFER, r.getMesh().getVertexNormals(), GL_STATIC_DRAW);
            renders.add(new RenderInfo(r, v, t, n));
        }
    }

    /**
     * Creates a shader program with the specified shaders
     *
     * @param vert the path to a vertex shader
     * @param frag the path to a fragment shader
     * @return the program id
     */
    private int createProgram(String vert, String frag) {
        String vString = Utils.parseText(vert),
                fString = Utils.parseText(frag);

        // Create, assigned source to and compile VERTEX shader. Output errors if present.
        int vShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vShader, vString);
        glCompileShader(vShader);
        if (glGetShaderi(vShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("Error compiling vertex shader");
            System.out.println(glGetShaderInfoLog(vShader));
        }

        // Create, assigned source to and compile FRAGMENT shader. Output errors if present.
        int fShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fShader, fString);
        glCompileShader(fShader);
        if (glGetShaderi(fShader, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("Error compiling fragment shader");
            System.out.println(glGetShaderInfoLog(fShader));
        }

        // Create, attach shaders to and link the program. Output errors if present.
        int program = glCreateProgram();
        glAttachShader(program, vShader);
        glAttachShader(program, fShader);
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Error linking program");
            System.err.println(glGetProgramInfoLog(program));
        }
        return program;
    }

    /**
     * Meaty method! It renders the frame with all the objects and phew... I won't bother commenting this further as of now...
     */
    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Get shader uniforms
        int proj_mat_loc = glGetUniformLocation(program, "proj_mat");
        int mv_mat_loc = glGetUniformLocation(program, "mv_mat");
        int n_mat_loc = glGetUniformLocation(program, "n_mat");
        int Mcolor = glGetUniformLocation(program, "material.color");
        int MshiLoc = glGetUniformLocation(program, "material.shininess");


        // Update camera view
        cam.lookAt(cam.getTransform().getPosition().add(cameraDirection));

        // Create perspective and view matrices
        Matrix4f pMat = cam.getPerspective();
        Matrix4f vMat = cam.getView();

        // Install lights
        light.getTransform().translate(6, 6, 6);
        installLights(vMat);

        // Set shader perspective uniform
        glUniformMatrix4fv(proj_mat_loc, false, pMat.get(new float[16]));

        for (RenderInfo r : renders) {
            Matrix4f mMat = r.getModelMatrix();
            Matrix4f mvMat = new Matrix4f();
            vMat.mul(mMat, mvMat);
            Material material = r.getMaterial();

            // Set per model shader uniform
            glUniformMatrix4fv(mv_mat_loc, false, mvMat.get(new float[16]));
            glUniformMatrix4fv(n_mat_loc, false, mvMat.invert().transpose().get(new float[16]));
            glProgramUniform4fv(program, Mcolor, material.getColor());
            glProgramUniform1f(program, MshiLoc, material.getShininess());

            // Set layout variables in shaders
            // Positions
            glBindBuffer(GL_ARRAY_BUFFER, r.getVBO());
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);
            // Tex coords
            glBindBuffer(GL_ARRAY_BUFFER, r.getTBO());
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);
            // Normals
            glBindBuffer(GL_ARRAY_BUFFER, r.getNBO());
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(2);

            // Draw the vertex buffers!
            glDrawArrays(GL_TRIANGLES, 0, r.getNumVertex());
        }

        // V-sync and callback events!
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    private void installLights(Matrix4f vMat) {
        // convert light’s position to view space, and save it in a float array
        float x = light.getTransform().getPosition().x;
        float y = light.getTransform().getPosition().y;
        float z = light.getTransform().getPosition().z;
        Vector4f lightP = new Vector4f(x, y, z, 1);
        Vector4f lightPv = lightP.mul(vMat);
        float[] viewspaceLightPos = new float[]{lightPv.x, lightPv.y, lightPv.z};

        // set the current globalAmbient settings
        int globalAmbLoc = glGetUniformLocation(program, "globalAmbient");
        glProgramUniform4fv(program, globalAmbLoc, Light.globalAmbient);

        // get the locations of the light and material fields in the shader
        int ambLoc = glGetUniformLocation(program, "light.ambient");
        int diffLoc = glGetUniformLocation(program, "light.diffuse");
        int specLoc = glGetUniformLocation(program, "light.specular");
        int posLoc = glGetUniformLocation(program, "light.position");

        // set the uniform light and material values in the shader
        glProgramUniform4fv(program, ambLoc, light.getAmbient());
        glProgramUniform4fv(program, diffLoc, light.getDiffuse());
        glProgramUniform4fv(program, specLoc, light.getSpecular());
        glProgramUniform3fv(program, posLoc, viewspaceLightPos);
    }
}