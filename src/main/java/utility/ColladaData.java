package utility;

import java.util.Objects;

public class ColladaData {
    private String id;
    private int numTriangles;
    private float[] positions;
    private float[] normals;
    private float[] texCoords;
    private int[] indices;

    public ColladaData(String id, int numTriangles) {
        this.id = id;
        this.numTriangles = numTriangles;
    }

    public void setPositions(float[] positions) {
        this.positions = positions;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
    }

    public void setTexCoords(float[] texCoords) {
        this.texCoords = texCoords;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public float[] getPositions() {

        return null; // TODO build array with indices
    }

    public float[] getNormals() {
        return null; // TODO build array with indices
    }

    public float[] getTexCoords() {
        return null; // TODO build array with indices
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColladaData that = (ColladaData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int[] getIndices() {
        return indices;
    }
}
