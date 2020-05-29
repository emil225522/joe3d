package system.rendering;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.joml.Matrix4f;
import system.EngineSystem;
import system.Window;

import java.util.*;

import static org.lwjgl.opengl.GL43.*;
import static utility.Const.*;
import static utility.Utils.*;

/**
 * A 3D rendering engine.
 */
public class RenderSystem extends EngineSystem {
    private static RenderSystem instance; // TODO can the singleton pattern with startUp(), shutDown() and get() be templated elegantly?

    private Window window;
    private int program;
    private final String VERT = SHADERS + "bp.vert";
    private final String FRAG = SHADERS + "bp.frag";

    // Buffers and containers for such stuff
    private int vao;
    private final int POSITION = 0;
    private final int TEXCOORD = 1;
    private final int NORMAL = 2;
    private final int NUM_BUFFERS = 3;
    private int[] vbo = new int[NUM_BUFFERS];

    // Camera related
    private Camera cam;
    Matrix4f pMat;
    Matrix4f vMat;

    // Render related
    private List<RenderInfo> renders;
    private Light light;

    /**
     * Creates an OpenGL 3D renderer, operating in a GLFW-managed window.
     */
    private RenderSystem(Window window) {
        this.window = window;
        this.renders = new Vector<>();
        this.cam = new Camera(WINDOW_WIDTH, WINDOW_HEIGHT);
        cam.getTransform().translate(0, 0, 5);  // TODO remove debug
    }

    /**
     * Starts the render system up.
     */
    public static void startUp(Window window) {
        if (instance == null) {
            instance = new RenderSystem(window);
            instance.init();
        }
    }

    /**
     * Shuts the render system down.
     */
    public static void shutDown() {
        if (instance != null) {
            instance.free();
            instance = null;
        }
    }

    /**
     * Gets a RenderSystem reference
     *
     * @return the reference to the RenderSystem singleton
     */
    public static RenderSystem get() {
        if(instance==null) throw new NullPointerException("Unable to find active render system.");
        return instance;
    }

    /**
     * Renders a frame.
     */
    public void update() {
        // Actual rendering and state updates
        updateCamera();
        updateSceneElements();
        renderScene();
        window.update();
    }

    public boolean shouldClose(){
        return window.windowShouldClose();
    }

    /**
     * This initializer method does a few things:
     * <ul>
     *  <li>Sets default OpenGL states.</li>
     *  <li>Sets up vertex buffers</li>
     *  <li>Creates a shader program.</li>
     * </ul>
     */
    protected void init() {
        // OpenGL states
        glFrontFace(GL_CCW);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH);
        glEnable(GL_MULTISAMPLE);
        glDepthFunc(GL_LEQUAL);

        // Create and bind the VAO
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Create shader program
        program = createProgram(VERT, FRAG);
        glUseProgram(program);
    }

    /**
     * Creates a shader program with the specified shaders
     *
     * @param vert the path to a vertex shader
     * @param frag the path to a fragment shader
     * @return the program id
     */
    private int createProgram(String vert, String frag) {
        String vString = parseText(vert),
                fString = parseText(frag);

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
     * Updates camera view, and perspective if window has been resized.
     */
    private void updateCamera() {
        cam.lookAt(cam.getTransform().getPosition().add(new Vector3f(0, 0, -1)));
        vMat = cam.getView();

        // Update perspective if resized
        if (window.isResized()) {
            cam.setPerspective((float) window.getWidth() / (float) window.getHeight());
            pMat = cam.getPerspective();
        }
    }

    /**
     *
     */
    private void updateSceneElements() {
        // TODO multiple light support
        if(light==null)return;
        installLights(vMat);
    }

    /**
     * Meaty method! It renders the frame with all the objects and phew... I won't bother commenting this further as of now...
     */
    private void renderScene() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Get shader uniforms
        int proj_mat_loc = glGetUniformLocation(program, "proj_mat");
        int mv_mat_loc = glGetUniformLocation(program, "mv_mat");
        int n_mat_loc = glGetUniformLocation(program, "n_mat");
        int material_color_loc = glGetUniformLocation(program, "material.color");
        int material_shininess_loc = glGetUniformLocation(program, "material.shininess");

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
            glProgramUniform4fv(program, material_color_loc, material.getColor());
            glProgramUniform1f(program, material_shininess_loc, material.getShininess());

            // Bind buffers
            glBindBuffer(GL_ARRAY_BUFFER, r.getVertexBuffer());
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);
            glBindBuffer(GL_ARRAY_BUFFER, r.getTexCoordBuffer());
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);
            glBindBuffer(GL_ARRAY_BUFFER, r.getNormalBuffer());
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(2);

            // Draw the vertex buffers!
            glDrawArrays(GL_TRIANGLES, 0, r.getVertexCount());
        }
    }

    /**
     * Adds render info for the specified mesh and transform to the renderer
     *
     * @param mesh      the 3d mesh to add to render info
     * @param transform the transform values to add to render info
     */
    public int[] addRenderInfo(Mesh mesh, Transform transform) {    // TODO return type should be some integer identifier
        int[] vbo = new int[3];
        glGenBuffers(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[POSITION]);
        glBufferData(GL_ARRAY_BUFFER, mesh.getVertexPositions(), GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[TEXCOORD]);
        glBufferData(GL_ARRAY_BUFFER, mesh.getVertexTexCoords(), GL_DYNAMIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[NORMAL]);
        glBufferData(GL_ARRAY_BUFFER, mesh.getVertexNormals(), GL_DYNAMIC_DRAW);
        renders.add(new RenderInfo(vbo, mesh, transform));
        return vbo;
    }



    public boolean removeRenderInfo(int[] vbo) {
        glDeleteBuffers(vbo);
        return renders.remove(new RenderInfo(vbo, null, null));

    }

    // TODO multiple light support
    public int addLight(Light light){
        this.light = light;
        return 0;   // TODO return light id for future remove referencing.
    }

    // TODO multiple light support
    public boolean removeLight(){
        light = null;
        return true;
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
        glProgramUniform4fv(program, globalAmbLoc, Light.GLOBAL_AMBIENT);

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

    protected void free() {
        glDeleteVertexArrays(vao);
        glDeleteBuffers(vbo);
        glDeleteProgram(program);
        renders.clear();
        window.destroy();
        renders = null;
        window = null;
        cam = null;
        pMat = null;
        vMat = null;
    }
}