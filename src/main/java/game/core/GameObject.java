package game.core;

import system.rendering.Transform;
import org.joml.Matrix4f;

/**
 * A generic game object. It is only described by its transform.
 */
public abstract class GameObject {
    protected Transform transform;

    /**
     * Creates a new game object.
     */
    public GameObject(){
        this.transform = new Transform();
    }

    /**
     * Retrieves a reference to the transform of the object.
     * @return a reference containing position, scale and rotation utilities.
     */
    public Transform getTransform() {
        return transform;
    }
}
