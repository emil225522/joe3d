package graphics;

import core.GameObject;
import org.joml.Matrix4f;

/**
 * A helper class containing buffer info for a renderable object.
 */
public class BufferInfo {
    private int vbo;
    private int ebo;
    private int[] indices;
    private Material material;
    private Matrix4f modelMatrix;

    /**
     * Creates a new container, holding the material and model matrix of a renderable object. Also, it stores the associated index buffer and vertex buffer IDs.
     * @param obj a renderable object
     * @param vbo the vertex buffer ID
     * @param ebo the index buffer ID
     */
    public BufferInfo(RenderObject obj, int vbo, int ebo) {
        this.vbo = vbo;
        this.ebo = ebo;
        this.indices = obj.getMesh().getIndices();
        this.material = obj.getMaterial();
        this.modelMatrix = obj.getModelMatrix();
    }

    /**
     * Gets the vertex buffer id
     * @return vertex buffer id
     */
    public int getVBO() {
        return vbo;
    }

    /**
     * Gets the index buffer id
     * @return index buffer id
     */
    public int getEBO() {
        return ebo;
    }

    /**
     * Retrieves a copy of the indices.
     * @return an integer array holding all indices.
     */
    public int[] getIndices() {
        return indices.clone();
    }

    /**
     * Retrieves a copy of the stored object model matrix.
     * @return 4x4 matrix holding the object position, scale and rotation.
     */
    public Matrix4f getModelMatrix() {
        return new Matrix4f(modelMatrix);
    }

    /**
     * Retrieves a copy of the stored object material
     * @return a material containing the properties such as color, shininess etc.
     */
    public Material getMaterial() {
        return material.clone();
    }
}
