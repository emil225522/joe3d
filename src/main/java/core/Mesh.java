package core;

import org.joml.Vector3f;

import java.util.Arrays;

public class Mesh {
    String name;
    Vector3f[] vertices;
    int[] indices;
    Vector3f[] normals;

    public int vertexCount(){
        return vertices.length;
    }

    // TODO is this the most sensible way?
    public float[] getVerticesFloats() {
        float[] vs = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            vs[3*i] = vertices[i].x;
            vs[3*i+1] = vertices[i].y;
            vs[3*i+2] = vertices[i].z;
        }
        return vs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesh mesh = (Mesh) o;
        return Arrays.equals(vertices, mesh.vertices);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vertices);
    }

    public int[] getIndices() {
        return indices;
    }
}
