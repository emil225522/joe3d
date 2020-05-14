package core;

public class RenderObject extends GameObject {
    Mesh mesh;

    public RenderObject(Mesh mesh){
        this.mesh = mesh;
    }

    public float[] getVerticesFloats() {
        return mesh.getVerticesFloats();
    }
}
