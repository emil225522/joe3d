package core;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
    private Vector3f position;
    private Vector3f scale;
    private Quaternionf rotation;

    public Transform(){
        this.position = new Vector3f();
        this.scale = new Vector3f(1);
        this.rotation = new Quaternionf();
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Quaternionf getRotation() {
        return rotation;
    }
}
