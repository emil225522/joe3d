package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class GameObject{
    Transform transform;

    public GameObject(){
        transform = new Transform();
    }

    public Vector3f getPosition() {
        return transform.getPosition();
    }

    public Matrix4f getModelMatrix(){
        Matrix4f m = new Matrix4f();
        m.scale(transform.getScale());
        m.translate(transform.getPosition());
        m.rotate(transform.getRotation());
        return m;
    }
}
