import game.Game;
import system.RenderSystem;
import system.Window;

import static utility.Const.*;

/**
 * An entry point for running the engine.
 */
public class GameEngine {
    private static Window window = new Window(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, true);    // TODO do like this...?
    public static void run(Game game) {
        // Start up systems in CORRECT ORDER

        RenderSystem.startUp(window);

        // Get singletons
        RenderSystem renderer = RenderSystem.get();

        // Do game stuff
        game.init();
        game.start();
        float previous = getTime();
        while (!window.windowShouldClose()) {
            float current = getTime();
            float elapsed = current - previous;
            System.out.println(1/elapsed);
            // TODO add input system here
            game.update(elapsed);
            renderer.update();      // TODO call from here or from Game?
            previous = current;
        }

        // Shut down systems in REVERSE ORDER
        RenderSystem.shutDown();
    }

    private static float getTime() {
        return (float)System.nanoTime() / (float)1000000000.0;
    }
}
