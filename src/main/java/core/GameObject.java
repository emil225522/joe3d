package core;

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
     * Retrieves the model matrix for the object.
     * @return a position, scale and rotation transformation matrix.
     */
    public Matrix4f getModelMatrix(){
        Matrix4f m = new Matrix4f();
        m.scale(transform.getScale());
        m.translate(transform.getPosition());
        m.rotate(transform.getRotation());
        return m;
    }

    /**
     * Retrieves a reference to the transform of the object.
     * @return a reference containing position, scale and rotation utilities.
     */
    public Transform getTransform() {
        return transform;
    }
}
