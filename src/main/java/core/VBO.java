package core;

import org.joml.Matrix4f;

public class VBO {
    int vertexID;
    int elementID;
    float[] vertexBuffer;
    int[] indices;
    Matrix4f modelMatrix;

    public VBO(int vertexID, int elementID, Mesh mesh, Matrix4f modelMatrix){
        this.vertexID = vertexID;
        this.elementID = elementID;
        this.vertexBuffer = mesh.getVerticesFloats();
        this.indices = mesh.getIndices();
        this.modelMatrix = modelMatrix;
    }

    public int getVertexID() {
        return vertexID;

    }public int getElementID() {
        return elementID;
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
}
