package system.rendering;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
    private Vector3f position;
    private Vector2f texCoord;
    private Vector3f normal;

    public Vertex(Vector3f position, Vector2f texCoord, Vector3f normal) {
        this.position = position;
        this.texCoord = texCoord;
        this.normal = normal;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public Vector2f getTexCoord() {
        return texCoord;
    }
}
