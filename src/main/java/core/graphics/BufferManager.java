package core.graphics;

import core.GameObject;
import core.geometry.Mesh;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

// TODO very crude solution. Should look into something less memory heavy or even singular VBO solution, once I get the hang of OpenGL
public class BufferManager {
    List<BufferInfo> bufferInfos;

    public BufferManager(){
        bufferInfos = new ArrayList<>();
    }

    public void load(int vid, int eid, GameObject obj){
        bufferInfos.add(new BufferInfo(vid, eid, obj));
    }

    public List<BufferInfo> getVBOs(){
        return bufferInfos;
    }
}
