import org.joml.Quaternionf;
import org.joml.Vector3f;
import render.Mesh;

public class RenderObject extends GameObject {
    Mesh mesh;

    public RenderObject(Mesh mesh){
        this.mesh = mesh;
    }

    public float[] getVerticesFloats() {
        return mesh.getVerticesFloats();
    }
}
