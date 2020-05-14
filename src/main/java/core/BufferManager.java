package core;

import java.util.HashMap;
import java.util.Map;

public class BufferManager {
    Map<Mesh, Integer> vertexMap;
    Map<Mesh, Integer> elementMap;
    int vBufferSize;
    int eBufferSize;

    public BufferManager(){
        vertexMap = new HashMap<>();
        elementMap = new HashMap<>();
        vBufferSize = 0;
        eBufferSize = 0;
    }

    public BufferManager(Scene scene){
        this();
        for (GameObject obj : scene.getObjects()){
            add(obj.mesh());
        }
    }

    public void add(Mesh mesh){
        if(vertexMap.containsKey(mesh)) return;
        vertexMap.put(mesh, vBufferSize);
        elementMap.put(mesh, eBufferSize);
        vBufferSize += mesh.vertexCount()*3;
        eBufferSize += mesh.faceCount();
    }

    public int getFirstVertexIndex(Mesh mesh){
        return vertexMap.get(mesh);
    }

    public float[] generateVertexData() {
        float[] data = new float[vBufferSize];
        for(Map.Entry<Mesh,Integer> entry : vertexMap.entrySet()) {
            float[] vertices = entry.getKey().getVerticesFloats();
            int first = entry.getValue();
            for (int i = 0; i < vertices.length; i++){
                data[first+i] = vertices[i];
            }
        }
        return data;
    }

    public int[] generateElementData(){
        int[] data = new int[eBufferSize];
        for(Map.Entry<Mesh,Integer> entry : elementMap.entrySet()) {
            int [] elements = entry.getKey().getFaces();
            int first = entry.getValue();
            for (int i = 0; i < elements.length; i++){
                data[first+i] = elements[i];
            }
        }
        return data;
    }

    public int getElementOffset(Mesh mesh) {
        return elementMap.get(mesh);
    }
}
