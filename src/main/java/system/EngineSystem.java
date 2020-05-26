package system;

public abstract class EngineSystem<T> {

    /**
     * What to do right after starting up the system.
     */
    protected abstract void init();

    /**
     * What to do right before shutting down the system
     */
    protected abstract void free();

    /**
     * Update the engine state.
     */
    public abstract void update();
}
