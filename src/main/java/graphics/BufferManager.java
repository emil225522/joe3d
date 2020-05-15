package graphics;

import core.GameObject;
import java.util.ArrayList;
import java.util.List;

// TODO very crude solution. Should look into something less memory heavy or even singular VBO solution, once I get the hang of OpenGL
public class BufferManager {
    List<BufferInfo> bufferInfos;

    public BufferManager(){
        bufferInfos = new ArrayList<>();
    }

    public void load(int vid, int eid, RenderObject obj){
        bufferInfos.add(new BufferInfo(vid, eid, obj));
    }

    public List<BufferInfo> getVBOs(){
        return bufferInfos;
    }
}
