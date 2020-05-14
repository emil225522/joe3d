package core;

import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferManager {
    List<VBO> vbos;
    static int id = 0;

    public BufferManager(){
        vbos = new ArrayList<>();
    }

    public void load(int vid, int eid, Mesh mesh, Matrix4f modelMatrix){
        vbos.add(new VBO(vid, eid, mesh, modelMatrix));
    }

    public List<VBO> getVBOs(){
        return vbos;
    }
}
