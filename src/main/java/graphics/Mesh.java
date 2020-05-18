package graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Arrays;

/**
 * An generic 3D mesh class. Provides basic functionality for concrete 3D primitives and models.
 */
public class Mesh {
    String name;
    Vector3f[] vertices;
    Vector3f[] normals;
    Vector2f[] texCoords;
    int[] indices;

    /**
     * Creates a new model. Preferably built with the static ModelBuilder class.
     * @param vertices
     * @param texCoords
     * @param normals
     * @param indices
     */
    public Mesh(Vector3f[] vertices, Vector2f[] texCoords, Vector3f[] normals, int[] indices) {
        this.vertices = vertices;
        this.texCoords = texCoords;
        this.normals = normals;
        this.indices = indices;
    }

    /**
     * Returns the amount of vertices in the mesh.
     * @return the vertices count.
     */
    public int vertexCount(){
        return vertices.length;
    }

    /**
     * Returns a copy of all vertices. Vertices are stored in a float array to use with OpenGL draw methods.
     * @return an array containing all mesh vertices.
     */
    public float[] getVerticesFloats() {
        float[] vs = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            vs[3*i] = vertices[i].x;
            vs[3*i+1] = vertices[i].y;
            vs[3*i+2] = vertices[i].z;
        }
        return vs;
    }

    public float[] getNormalFloats() {
        float[] ns = new float[normals.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            ns[3*i] = normals[i].x;
            ns[3*i+1] = normals[i].y;
            ns[3*i+2] = normals[i].z;
        }
        return ns;
    }

    /**
     * Returns a copy of all indices, to use with OpenGL element draw methods.
     * @return an array containing all indices.
     */
    public int[] getIndices() {
        return indices.clone();
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


}
