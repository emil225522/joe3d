package core;

import org.joml.Vector3f;

public class Mesh {
    String name;
    Vector3f[] vertices;

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
}
