package render;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

public class Plane extends Mesh {
    public Plane() {
        super();
        vertices = new Vector3f[]{
                new Vector3f(0, 0, 0),
                new Vector3f(1, 0, 0),
                new Vector3f(1, 0, -1),
                new Vector3f(0, 0, 0),
                new Vector3f(1, 0, -1),
                new Vector3f(0, 0, -1)
        };
    }

}
