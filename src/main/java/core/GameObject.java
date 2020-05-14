package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class GameObject {
    Transform transform;
    Mesh mesh;

    public GameObject(Mesh mesh){
        this.transform = new Transform();
        this.mesh = mesh;
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

    public void scale(float scalar){
        transform.scale(scalar);
    }

    public void translate(Vector3f translation){
        transform.translate(translation);
    }

    public void translate(float x, float y, float z){
        translate(new Vector3f(x,y,z));
    }



    public float[] getVerticesFloats() {
        return mesh.getVerticesFloats();
    }

    public int getVertexCount(){
        return mesh.vertices.length;
    }

    public Mesh mesh(){
        return mesh;
    }
}
