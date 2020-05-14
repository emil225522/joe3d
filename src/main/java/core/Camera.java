package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private Vector3f position;
    private Matrix4f perspective;

    public Camera(float width, float height){
        position = new Vector3f(0f, 0, 5f);
        perspective = new Matrix4f().setPerspective((float)Math.toRadians(60), width/height, 0.1f, 1000f);
    }

    public Matrix4f lookAt(Vector3f target){
        return new Matrix4f().lookAt(position, target, new Vector3f(0,1,0));
    }

    public Matrix4f getPerspective() {
        return perspective;
    }
}
