package game.core;

import game.core.components.Component;
import system.rendering.Transform;

import java.util.HashMap;
import java.util.Map;

/**
 * A generic game object. It is only described by its transform and components.
 */
public  class  GameObject {
    private final Transform transform;
    private final Map<Class<?>, Component> components;

    /**
     * Creates a new game object without components.
     */
    public GameObject() {
        this.transform = new Transform();
        this.components = new HashMap<>();
    }

    /**
     * Creates a new game object with components.
     *
     * @param component the component(s) to add to the object.
     */
    public GameObject(Component... component) {
        this();
        addComponent(component);
    }

    /**
     * Adds a component to the game object.
     *
     * @param c the component to add.
     */
    public void addComponent(Component... c) {
        for (Component comp : c) {
            comp.setParent(this);
            components.put(comp.getClass(), comp);
        }
    }

    /**
     * Gets a component from the game object.
     * @param cls the class of the component to get
     * @return a reference to the queried component.
     */
    public <T> T getComponent(Class<?> cls) {
        return (T)components.get(cls);
    }

    /**
     * Removes the component of the specified class.
     * @param cls the class of the component to remove
     * @return False if the component wasn't found, else returns true.
     */
    public boolean removeComponent(Class cls) {
        return components.remove(cls) != null;
    }

    /**
     * Called at the start of a game.
     */
    public void start() {
        for (Component comp : components.values()) {
            comp.start();
        }
    }

    /**
     * Called every frame.
     *
     * @param delta time passed since last update.
     */
    public void update(float delta) {
        for (Component comp : components.values()) {
            comp.update(delta);
        }
    }

    /**
     * Retrieves a reference to the transform of the object.
     *
     * @return a reference containing position, scale and rotation utilities.
     */
    public Transform getTransform() {
        return transform;
    }
}
