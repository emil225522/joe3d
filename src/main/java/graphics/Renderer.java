package graphics;

import core.Camera;
import core.GameObject;
import core.Scene;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.BufferUtils;
import utility.*;

import org.joml.Matrix4f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

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

    private final String VERT = Paths.SHADERS + "triangles.vert";
    private final String FRAG = Paths.SHADERS + "triangles.frag";

    // Buffers and containers for such stuff
    private int vao;
    private BufferManager bm;

    // Abstract objects
    private Camera cam;
    private Scene scene;
    private Vector3fc cameraDirection = new Vector3f(0,0,-1);

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
        this.bm = new BufferManager();
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
        glfwSetErrorCallback(null).free();
    }

    /**
     * This initializer method does a few things: <br>
     *     <ul>
     *      <li>Initializes GLFW and GL capabilities.</li>
     *      <li>Sets default OpenGL states.</li>
     *      <li>Sets up vertex and index buffers for all loaded renderable objects</li>
     *      <li>Creates a shader program.</li>
     *     </ul>
     */
    private void init() {
        setupGLFW();
        GL.createCapabilities();
        setupCallbacks();

        glFrontFace(GL_CCW);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH);
        glEnable(GL_MULTISAMPLE);
        glDepthFunc(GL_LEQUAL);
        glClearColor(0.2f, 0.2f, 0.2f, 1);

        setupBuffers();

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

        window = glfwCreateWindow(Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT, "core.rendering.Renderer", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        // Center window
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
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
        // Setup a key callback
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            else if (key == GLFW_KEY_W && action == GLFW_PRESS)
                ; // TODO add camera moves forward! DEBUG
        });

        // Window resize callback
        glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                glViewport(0, 0, width, height);
                cam.setPerspective((float) width / (float) height);
            }
        });

        glfwSetScrollCallback(window, (GLFWScrollCallbackI) (window, x, y) -> {

        });
    }

    /**
     * Sets up vertex and element (index) buffers for all objects in the scene.
     */
    private void setupBuffers() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);
        
        for (GameObject obj : scene.getObjects()) {
            if(!(obj instanceof RenderObject)) continue;
            RenderObject r = (RenderObject) obj;
            int v = glGenBuffers();
            int e = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, v);
            glBufferData(GL_ARRAY_BUFFER, r.getMesh().getVerticesFloats(), GL_STATIC_DRAW);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, e);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, r.getMesh().getIndices(), GL_STATIC_DRAW);
            bm.add(r, v, e);
        }
    }

    /**
     * Creates a shader program with the specified shaders
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
        int pLoc = glGetUniformLocation(program, "pMat");
        int mvLoc = glGetUniformLocation(program, "mvMat");
        int colorLoc = glGetUniformLocation(program, "color");

        // Update camera view
        cam.lookAt(cam.getTransform().getPosition().add(cameraDirection));

        // Create perspective and view matrices
        Matrix4f pMat = cam.getPerspective();
        Matrix4f vMat = cam.getView();

        // Set shader perspective uniform
        FloatBuffer pBuf = BufferUtils.createFloatBuffer(16);
        pMat.get(pBuf);
        glUniformMatrix4fv(pLoc, false, pBuf);

        for (BufferInfo b : bm.getBufferInfos()) {
            Matrix4f mMat = b.getModelMatrix();
            Matrix4f mvMat = new Matrix4f();
            vMat.mul(mMat, mvMat);

            // Set modelview shader uniform
            FloatBuffer mvBuf = BufferUtils.createFloatBuffer(16);
            mvMat.get(mvBuf);
            glUniformMatrix4fv(mvLoc, false, mvBuf);
            glUniform4fv(colorLoc, b.getMaterial().getColor());

            // Bind buffers and enable shader variables
            glBindBuffer(GL_ARRAY_BUFFER, b.getVBO());
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);

            glBindBuffer(GL_ARRAY_BUFFER, b.getNBO());
            glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, b.getEBO());

            // Draw the vertex buffers!
            glDrawElements(GL_TRIANGLES, b.getIndices().length, GL_UNSIGNED_INT, 0);
        }

        // V-sync and callback events!
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
}