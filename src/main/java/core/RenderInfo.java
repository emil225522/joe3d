package core;

public class RenderInfo {
    private RenderObject ro;
    private int first;
    private int vertexCount;

    public RenderInfo(RenderObject ro, int first, int vertexCount){
        this.ro = ro;
        this.first = first;
        this.vertexCount = vertexCount;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getFirst() {
        return first;
    }

    public RenderObject getRo() {
        return ro;
    }
}
