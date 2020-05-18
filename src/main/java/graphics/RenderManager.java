package graphics;

import java.util.ArrayList;
import java.util.List;



/**
 *  A container class for BufferInfo instances.
 */
public class RenderManager {
    List<RenderInfo> renderInfos;

    /**
     * Creates a new empty BufferManager
     */
    public RenderManager(){
        renderInfos = new ArrayList<>();
    }

    /**
     * Loads a new buffer info instance into the manager.
     * @param obj a renderable object
     * @param vid the vertex buffer id
     */
    public void add(RenderObject obj, int vid, int tid, int nid){
        renderInfos.add(new RenderInfo(obj, vid, tid, nid));
    }

    /**
     * Retrieves a list of all buffer info instances in the manager
     * @return a list containing all buffer info instances
     */
    public List<RenderInfo> getRenderInfos(){
        return renderInfos;
    }
}
