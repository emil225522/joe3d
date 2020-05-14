package core;

import core.geometry.Mesh;
import org.joml.Matrix4f;

public class GameObject {
    Transform transform;
    Mesh mesh;
    Material material;

    public GameObject(Mesh mesh){
        this.transform = new Transform();
        this.mesh = mesh;
        this.material = new Material();
    }

    public Matrix4f getModelMatrix(){
        Matrix4f m = new Matrix4f();
        m.scale(transform.getScale());
        m.translate(transform.getPosition());
        m.rotate(transform.getRotation());
        return m;
    }

    public Mesh getMesh(){
        return mesh;
    }

    public Transform getTransform() {
        return transform;
    }

    public Material getMaterial() {
        return material;
    }
}
