package graphics;

import org.joml.Matrix4f;

/**
 * A helper class containing buffer info for a renderable object.
 */
public class RenderInfo { // TODO rather crude solution. Should look into something less memory heavy or even singular EBO and VBO solution, once I get the hang of OpenGL
    private int vbo;
    private int tbo;
    private int nbo;
    private int numVertex;
    private int[] indices;
    private Material material;
    private Matrix4f modelMatrix;

    /**
     * Creates a new container, holding the material and model matrix of a renderable object. Also, it stores the associated index buffer and vertex buffer IDs.
     * @param obj a renderable object
     * @param vbo the vertex buffer ID
     */
    public RenderInfo(RenderObject obj, int vbo, int tbo, int nbo) {
        this.vbo = vbo;
        this.tbo = tbo;
        this.nbo = nbo;
        this.numVertex = obj.getMesh().vertexCount();
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

    public int getTBO() {
        return tbo;
    }

    public int getNBO() {
        return nbo;
    }

    /**
     * Retrieves a copy of the indices.
     * @return an integer array holding all indices.
     */
    public int getNumVertex() {
        return numVertex;
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
