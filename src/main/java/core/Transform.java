package core;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
    private Vector3f position;
    private Vector3f scale;
    private Quaternionf rotation;

    public Transform() {
        this.position = new Vector3f();
        this.scale = new Vector3f(1);
        this.rotation = new Quaternionf();
    }

    public void translate(Vector3f translation) {
        position.add(translation);
    }

    public void translate(float x, float y, float z) {
        position.add(x,y,z);
    }

    public void scale(float scalar) {
        scale.mul(scalar);
    }

    public void scale(float x, float y, float z){
        scale.mul(x,y,z);
    }

    public void rotate(float deg, float x, float y, float z){
        rotation.rotateAxis((float)Math.toRadians(deg), x, y, z);
    }

    public Vector3f getPosition() {
        return new Vector3f(position);
    }

    public Vector3f getScale() {
        return new Vector3f(scale);
    }

    public Quaternionf getRotation() {
        return new Quaternionf(rotation);
    }
}
