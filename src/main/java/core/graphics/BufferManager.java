package core.graphics;

import core.geometry.Mesh;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

// TODO very crude solution. Should look into something less memory heavy or even singular VBO solution, once I get the hang of OpenGL
public class BufferManager {
    List<VBO> vbos;

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
