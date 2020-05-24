package game.core;

public abstract class Component {
    GameObject parent;

    /**
     * Sets the parent of the component.
     *
     * @param obj game object to parent this component to
     */
    void setParent(GameObject obj) {
        this.parent = obj;
    }

    /**
     * Called at the start of the game.
     */
    abstract void start();

    /**
     * Called every frame.
     *
     * @param interval time passed since last update
     */
    abstract void update(float interval);
}
