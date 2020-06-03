import game.Game;
import system.InputSystem;
import system.RenderSystem;
import system.Window;

import static utility.Const.*;

/**
 * An entry point for running the engine.
 */
public class GameEngine implements Runnable {
    private Window window = new Window(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, USE_VSYNC);
    private Game game;

    public GameEngine(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        // Start up systems in CORRECT ORDER
        RenderSystem.startUp(window);
        InputSystem.startUp(window);

        // Get singletons
        RenderSystem renderer = RenderSystem.get();
        InputSystem input = InputSystem.get();

        // Do game stuff
        game.init();
        game.start();

        long current = 0;
        long previous = getTime();
        while (!window.windowShouldClose()) {
            current = getTime();
            float elapsed = (current - previous) / 1000f;

            input.update();
            game.update(elapsed);
            renderer.update();

            previous = current;
        }

        // Shut down systems in REVERSE ORDER
        InputSystem.shutDown();
        RenderSystem.shutDown();
    }

    private void sync(long current) {
        long loopSlot = 1000 / 50;
        float endTime = current + loopSlot;
        while (getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    private long getTime() {
        return System.nanoTime() / 1000000;
    }
}
