package core;

import java.util.*;

public class Scene implements Iterator<RenderInfo> {
    List<RenderObject> ros;
    Map<RenderObject, RenderInfo> map;
    int bufferSize;
    int iteratorPointer;

    public Scene(){
        ros = new ArrayList<>();
        map = new HashMap<>();
        bufferSize = 0;
        iteratorPointer = -1;
    }

    public void add(RenderObject ro){
        ros.add(ro);

        if(!map.containsKey(ro)){
            int vCount = ro.getVertexCount();
            map.put(ro, new RenderInfo(ro, bufferSize, vCount));
            bufferSize += vCount*3;
        }

    }

    public float[] getAllBufferData(){
        float[] data = new float[bufferSize];
        Set<RenderObject> added = new HashSet<>();
        for (RenderObject ro : ros) {
            if(!added.contains(ro)){
                int start = map.get(ro).getFirst();
                float[] vertices = ro.getVerticesFloats();
                for (int i = 0; i < vertices.length; i++) {
                    data[start+i] = vertices[i];
                }
            }
            added.add(ro);
        }
        return data;
    }

    @Override
    public boolean hasNext() {
        return iteratorPointer + 1 < ros.size();
    }

    @Override
    public RenderInfo next() {
        return map.get(ros.get(iteratorPointer++));
    }

    public void reset(){
        iteratorPointer = -1;
    }
}
