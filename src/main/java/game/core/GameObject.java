package game.core;

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
     * Creates a new game object.
     */
    public GameObject(){
        this.transform = new Transform();
        this.components = new HashSet<>();
    }

    /**
     * Retrieves a reference to the transform of the object.
     * @return a reference containing position, scale and rotation utilities.
     */
    public Transform getTransform() {
        return transform;
    }

    public void start(){

    }

    public void update(){

    }
}
