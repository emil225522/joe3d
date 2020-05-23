import system.rendering.Camera;
import game.core.Scene;
import system.rendering.*;

import static utility.Const.*;

/**
 * An entry point for running the engine.
 */
public class GameEngine {
    public static void main(String[] args) {
        RenderSystem.startUp();

        RenderSystem.get().run();

        RenderSystem.shutDown();
    }
}
