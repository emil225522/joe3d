package system.input;

import system.EngineSystem;
import system.Window;

import java.util.HashMap;
import java.util.Map;

public class InputSystem extends EngineSystem {
    private static InputSystem instance;

    private Window window;
    private Map<Integer, Boolean> keys;

    /**
     * Creates an OpenGL 3D renderer, operating in a GLFW-managed window.
     */
    private InputSystem(Window window) {
        this.window = window;
        this.keys = new HashMap<>();
    }

    /**
     * Starts the render system up.
     */
    public static void startUp(Window window) {
        if (instance == null) {
            instance = new InputSystem(window);
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
    public static InputSystem get() {
        if(instance==null) throw new NullPointerException("Unable to find active render system.");
        return instance;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void free() {

    }

    @Override
    public void update() {

    }
}
