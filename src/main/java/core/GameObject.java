package core;

import graphics.Material;
import graphics.Mesh;
import org.joml.Matrix4f;

public abstract class GameObject {
    protected Transform transform;

    public GameObject(){
        this.transform = new Transform();
    }

    public Matrix4f getModelMatrix(){
        Matrix4f m = new Matrix4f();
        m.scale(transform.getScale());
        m.translate(transform.getPosition());
        m.rotate(transform.getRotation());
        return m;
    }

    public Transform getTransform() {
        return transform;
    }
}
