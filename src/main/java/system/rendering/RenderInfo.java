package system.rendering;

import org.joml.Matrix4f;

import java.util.Arrays;

/**
 * A helper class containing buffer info for a renderable object.
 */
public class RenderInfo {
    private int[] vbo;
    private Mesh mesh;
    private Transform transform;

    /**
     * Creates a new container for VBO start index, number of vertices, model matrix and material of a renderable object.
     *
     * @param vbo
     * @param mesh
     * @param transform
     */
    public RenderInfo(int[] vbo, Mesh mesh, Transform transform) {
        assert vbo.length==3;
        this.vbo = Arrays.copyOf(vbo, 3);
        this.mesh = mesh;
        this.transform = transform;
    }

    public int getVertexBuffer() {
        return vbo[0];
    }

    public int getTexCoordBuffer() {
        return vbo[1];
    }

    public int getNormalBuffer() {
        return vbo[2];
    }

    /**
     * Returns the amount of vertices in the render info container.
     *
     * @return the vertex count.
     */
    public int getVertexCount() {
        return mesh.vertexCount();
    }

    /**
     * Retrieves the model matrix for this render info container.
     *
     * @return 4x4 matrix holding the container's position, scale and rotation.
     */
    public Matrix4f getModelMatrix() {
        return transform.calculateModelMatrix();
    }

    /**
     * Retrieves a reference to the render info mesh.
     *
     * @return the mesh of the renderable.
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Retrieves a copy of the render material
     *
     * @return a material containing the properties such as color, shininess etc.
     */
    public Material getMaterial() {
        return mesh.material.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenderInfo that = (RenderInfo) o;
        return Arrays.equals(vbo, that.vbo);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vbo);
    }
}
