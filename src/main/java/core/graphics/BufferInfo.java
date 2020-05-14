package core.graphics;

import core.GameObject;
import core.Material;
import core.geometry.Mesh;
import org.joml.Matrix4f;

public class BufferInfo {
    private int vbo;
    private int ebo;
    private float[] vertexBuffer;
    private int[] indices;
    private Material material;
    private Matrix4f modelMatrix;

    public BufferInfo(int vbo, int ebo, GameObject obj){
        this.vbo = vbo;
        this.ebo = ebo;
        this.vertexBuffer = obj.getMesh().getVerticesFloats();
        this.indices = obj.getMesh().getIndices();
        this.material = obj.getMaterial();
        this.modelMatrix = obj.getModelMatrix();
    }

    public int getVBO() {
        return vbo;

    }public int getEBO() {
        return ebo;
    }

    public float[] getVertexBuffer() {
        return vertexBuffer.clone();
    }

    public int[] getIndices() {
        return indices.clone();
    }

    public Matrix4f getModelMatrix(){
        return new Matrix4f(modelMatrix);
    }

    public Material getMaterial() {
        return material;
    }
}
