package core.graphics;

import core.Camera;
import core.GameObject;
import core.Scene;
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

public class Renderer {
    private long window;
    private int program;

    final String VERT = Paths.SHADERS + "triangles.vert";
    final String FRAG = Paths.SHADERS + "triangles.frag";

    final int WINDOW_HEIGHT = 512;
    final int WINDOW_WIDTH = 512;

    // Buffers and containers for such stuff
    int vao;
    BufferManager bm;

    // Abstract objects
    Camera cam;
    Scene scene;


    public Renderer(Scene scene) {
        this.cam = scene.getMainCamera();
        this.scene = scene;
        this.bm = new BufferManager();
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
        setupCallbacks();

        glFrontFace(GL_CCW);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_DEPTH);
        glDepthFunc(GL_LEQUAL);
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

        window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "core.rendering.Renderer", NULL, NULL);
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
                glViewport(0,0,width, height);
                cam.setPerspective((float)width/(float)height);
            }
        });
    }

    private void setupVertices() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        for(GameObject obj : scene.getObjects()){
            int v = glGenBuffers();
            int e = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, v);
            glBufferData(GL_ARRAY_BUFFER, obj.getMesh().getVerticesFloats(), GL_STATIC_DRAW);
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, e);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, obj.getMesh().getIndices(), GL_STATIC_DRAW);
            bm.load(v, e, obj.getMesh(), obj.getModelMatrix());
        }
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

        // Create perspective and view matrices
        Matrix4f pMat = cam.getPerspective();
        Matrix4f vMat = cam.lookAt(new Vector3f(0, 0, -1));

        // Set shader perspective uniform
        FloatBuffer pBuf = BufferUtils.createFloatBuffer(16);
        pMat.get(pBuf);
        glUniformMatrix4fv(pLoc, false, pBuf);

        for (VBO vbo : bm.getVBOs()) {
            Matrix4f mMat = vbo.getModelMatrix();
            Matrix4f mvMat = new Matrix4f();
            vMat.mul(mMat, mvMat);

            // Set modelview shader uniform
            FloatBuffer mvBuf = BufferUtils.createFloatBuffer(16);
            mvMat.get(mvBuf);
            glUniformMatrix4fv(mvLoc, false, mvBuf);

            // Bind buffers and enable shader variables
            glBindBuffer(GL_ARRAY_BUFFER, vbo.getVertexID());
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo.elementID);

            // Draw the vertex buffers!
            glDrawElements(GL_TRIANGLES, vbo.getIndices().length, GL_UNSIGNED_INT, 0);
        }

        // V-sync and key events!
        glfwSwapBuffers(window);
        glfwPollEvents();


    }
}