package graphics;

import core.GameObject;
import java.util.ArrayList;
import java.util.List;



/**
 *  A container class for BufferInfo instances.
 */
public class BufferManager {    // TODO rather crude solution. Should look into something less memory heavy or even singular VBO solution, once I get the hang of OpenGL
    List<BufferInfo> bufferInfos;

    /**
     * Creates a new empty BufferManager
     */
    public BufferManager(){
        bufferInfos = new ArrayList<>();
    }

    /**
     * Loads a new buffer info instance into the manager.
     * @param obj a renderable object
     * @param vid the vertex buffer id
     * @param eid the index buffer id
     */
    public void add(RenderObject obj, int vid, int eid){
        bufferInfos.add(new BufferInfo(obj, vid, eid));
    }

    /**
     * Retrieves a list of all buffer info instances in the manager
     * @return a list containing all buffer info instances
     */
    public List<BufferInfo> getBufferInfos(){
        return bufferInfos;
    }
}
