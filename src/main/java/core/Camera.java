package core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private Transform transform;
    private Matrix4f perspective;

    public Camera(float width, float height){
        transform = new Transform();
        transform.translate(0,0,5);
        perspective = new Matrix4f().setPerspective((float)Math.toRadians(60), width/height, 0.1f, 1000f);
    }

    public Transform getTransform() {
        return transform;
    }

    public Matrix4f lookAt(Vector3f target){
        return new Matrix4f().lookAt(transform.getPosition(), target, new Vector3f(0,1,0));
    }

    public Matrix4f getPerspective() {
        return perspective;
    }

    public void setPerspective(float aspect) {
        perspective = new Matrix4f().setPerspective((float)Math.toRadians(60), aspect, 0.1f, 1000f);
    }
}
