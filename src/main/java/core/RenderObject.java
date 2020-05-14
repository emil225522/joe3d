package core;

import java.util.Objects;

public class RenderObject extends GameObject {
    Mesh mesh;

    public RenderObject(Mesh mesh){
        this.mesh = mesh;
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
