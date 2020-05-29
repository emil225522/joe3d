import game.Game;
import system.input.InputSystem;
import system.rendering.RenderSystem;
import system.Window;

import static utility.Const.*;

/**
 * An entry point for running the engine.
 */
public class GameEngine {
    private static Window window = new Window(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, USE_VSYNC);    // TODO make its own system?
    public static void run(Game game) {
        // Start up systems in CORRECT ORDER
        RenderSystem.startUp(window);
        InputSystem.startUp(window);

        // Get singletons
        RenderSystem renderer = RenderSystem.get();
        InputSystem input = InputSystem.get();

        // Do game stuff
        game.init();
        game.start();
        float previous = getTime();
        while (!window.windowShouldClose()) {
            float current = getTime();
            float elapsed = current - previous;

            input.update();
            game.update(elapsed);   // TODO make game loop do steps, update game state til its time for rendering, then updates wont need the elapsed factor.
            renderer.update();      // TODO call from here or from Game?
            previous = current;
        }

        // Shut down systems in REVERSE ORDER
        InputSystem.shutDown();
        RenderSystem.shutDown();
    }

    private static float getTime() {
        return (float)System.nanoTime() / (float)1000000000.0;
    }
}
