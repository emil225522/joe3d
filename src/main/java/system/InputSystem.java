package system;

import java.util.HashMap;
import java.util.Map;

public class InputSystem {
    private static InputSystem instance;

    private Window window;
    private Map<Character, Boolean> keysHeld;

    /**
     * Creates an OpenGL 3D renderer, operating in a GLFW-managed window.
     */
    private InputSystem(Window window) {
        this.window = window;
        this.keysHeld = window.getKeysHeld();
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

    private void init() {

    }

    private void free() {

    }

    public void update() {

    }

    public boolean isKeyPressed(char key){
        return keysHeld.getOrDefault(key, false);
    }
}
