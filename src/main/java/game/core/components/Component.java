package game.core.components;
import game.core.GameObject;

public abstract class Component {
    GameObject parent;

    /**
     * Sets the parent of the component.
     *
     * @param obj game object to parent this component to
     */
    public void setParent(GameObject obj) {
        this.parent = obj;
    }

    /**
     * Called at the start of the game.
     */
    public abstract void start();

    /**
     * Called every frame.
     *
     * @param interval time passed since last update
     */
    public abstract void update(float interval);

    /**
     *  Called when the component is removed. Usually undoing what the start method did.
     */
    public abstract void onRemove();

    protected <T> T getSibling(Class<?> cls){
        return parent.getComponent(cls);
    }
}
