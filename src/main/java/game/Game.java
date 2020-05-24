package game;

public interface Game {

    /**
     * Initializes the game. Always run this before starting the game.
     */
    void init();

    /**
     * Starts the game. Usually iterates over all scene elements and runs their start() methods.
     */
    void start();

    /**
     * Updates the game state. Usually iterates over all scene elements and runs their start() methods.
     *
     * @param delta time passed since last update
     */
    void update(float delta);
}
