package core;

import org.joml.Vector3f;
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

public class Graphics {
    private long window;
    private int program;

    final String VERT = Paths.SHADERS + "triangles.vert";
    final String FRAG = Paths.SHADERS + "triangles.frag";

    final int WINDOW_HEIGHT = 512;
    final int WINDOW_WIDTH = 512;

    final short NUM_VAOS = 1;
    final short RENDER_OBJECTS = 0;
    final short NUM_VBOS = 1;
    int vao;
    int[] vbo = new int[NUM_VBOS];
    int bufferIndexPointer = 0;

    // Render objects
    Camera cam;
    Mesh m = new Model(Paths.MODELS + "quad.obj");
    RenderObject ro = new RenderObject(m);

    // TODO take a scene
    public Graphics(){
        cam = new Camera(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

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

    private void init() {
        setupGLFW();
        GL.createCapabilities();
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH);
        glClearColor(0.2f, 0.2f, 0.2f, 1);

        setupVertices();

        program = createProgram(VERT, FRAG);
        glUseProgram(program);
    }

    private void setupGLFW() {
        // GLFW
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "core.Renderer", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            else if (key == GLFW_KEY_W && action == GLFW_PRESS)
                ; // TODO add camera moves forward! DEBUG
        });

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

    private void setupVertices() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        glGenBuffers(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo[RENDER_OBJECTS]);
        glBufferData(GL_ARRAY_BUFFER, ro.getVerticesFloats(), GL_STATIC_DRAW);

        // Transform render objects
        ro.scale(1);
    }

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

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Get shader uniforms
        int pLoc = glGetUniformLocation(program, "pMat");
        int mvLoc = glGetUniformLocation(program, "mvMat");

        // Setup matrices
        Matrix4f pMat = cam.getPerspective();
        Matrix4f vMat = cam.lookAt(new Vector3f(0, 0, -1));
        Matrix4f mMat = ro.getModelMatrix();    // TODO this needs to be done once per object. Combined Buffer and Object manager, or separate ones with dependencies?
        Matrix4f mvMat = new Matrix4f();
        vMat.mul(mMat, mvMat);

        // Set shader uniforms
        FloatBuffer pBuf = BufferUtils.createFloatBuffer(16);
        pMat.get(pBuf);
        glUniformMatrix4fv(pLoc, false, pBuf);
        FloatBuffer mvBuf = BufferUtils.createFloatBuffer(16);  // TODO this needs to be done once per object
        mvMat.get(mvBuf);
        glUniformMatrix4fv(mvLoc, false, mvBuf);

        // Bind buffers and enable shader variables
        glBindBuffer(GL_ARRAY_BUFFER, vbo[RENDER_OBJECTS]);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        // Draw the vertex buffers! TODO multiple buffer support, possible indexed as well
        glDrawArrays(GL_TRIANGLES, 0, ro.getVertexCount());

        // V-sync and key events!
        glfwSwapBuffers(window);
        glfwPollEvents();
    }
}