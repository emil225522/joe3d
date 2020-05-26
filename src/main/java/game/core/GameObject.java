package game.core;

import game.core.components.Component;
import system.rendering.Transform;

import java.util.HashSet;
import java.util.Set;

/**
 * A generic game object. It is only described by its transform and components.
 */
public class GameObject {
    private Transform transform;
    private Set<Component> components;

    /**
     * Creates a new game object without components.
     */
    public GameObject() {
        this.transform = new Transform();
        this.components = new HashSet<>();
    }

    /**
     * Creates a new game object with components.
     *
     * @param component the component(s) to add to the object.
     */
    public GameObject(Component... component) {
        this.transform = new Transform();
        this.components = new HashSet<>();
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
            components.add(comp);
        }
    }

    /**
     * Called at the start of a game.
     */
    public void start() {
        for (Component comp : components) {
            comp.start();
        }
    }

    /**
     * Called every frame.
     *
     * @param delta time passed since last update.
     */
    public void update(float delta) {
        for (Component comp : components) {
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
