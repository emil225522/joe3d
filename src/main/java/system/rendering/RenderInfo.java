package system.rendering;

import org.joml.Matrix4f;

/**
 * A helper class containing buffer info for a renderable object.
 */
public class RenderInfo {
    private int index;
    private Mesh mesh;
    private Transform transform;

    /**
     * Creates a new container for VBO start index, number of vertices, model matrix and material of a renderable object.
     *
     * @param index
     * @param mesh
     * @param transform
     */
    public RenderInfo(int index, Mesh mesh, Transform transform) {
        this.index = index;
        this.mesh = mesh;
        this.transform = transform;
    }

    /**
     * Returns the index in the VBO for this particular render info instance.
     *
     * @return the start index in the VBO.
     */
    public int getIndex() {
        return index;
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
     * Retrieves a copy of the render material
     *
     * @return a material containing the properties such as color, shininess etc.
     */
    public Material getMaterial() {
        return mesh.material.clone();
    }
}
