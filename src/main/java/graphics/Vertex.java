package graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
    Vector3f position;
    Vector2f texCoord;
    Vector3f normal;

    public Vertex(Vector3f position, Vector2f texCoord, Vector3f normal) {
        this.position = position;
        this.texCoord = texCoord;
        this.normal = normal;
    }
}
