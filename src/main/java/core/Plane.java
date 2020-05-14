package core;

import org.joml.Vector3f;

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
