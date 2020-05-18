package graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Arrays;

/**
 * An generic 3D mesh class. Provides basic functionality for concrete 3D primitives and models.
 */
public class Mesh {
    Vertex[] vertices;

    /**
     * Creates a new model. Preferably built with the static ModelBuilder class.
     * @param vertices
     */
    public Mesh(Vertex[] vertices) {
        this.vertices = vertices;
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
    public float[] getVertexPositions() {
        float[] vs = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            Vector3f position = vertices[i].getPosition();
            vs[3*i] = position.x;
            vs[3*i+1] = position.y;
            vs[3*i+2] = position.z;
        }
        return vs;
    }

    public float[] getVertexTexCoords() {
        float[] ts = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            Vector2f texCoord = vertices[i].getTexCoord();
            if(texCoord != null){
                ts[2*i] = texCoord.x;
                ts[2*i+1] = texCoord.y;
            }
        }
        return ts;
    }

    public float[] getVertexNormals() {
        float[] ns = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            Vector3f normal = vertices[i].getNormal();
            if(normal != null) {
                ns[3*i] = normal.x;
                ns[3*i+1] = normal.y;
                ns[3*i+2] = normal.z;
            }
        }
        return ns;
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
