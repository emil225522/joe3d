package graphics;

import core.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera extends GameObject {
    private Matrix4f perspective;
    private Matrix4f view;

    public Camera(float width, float height){
        super();
        perspective = new Matrix4f();
        view = new Matrix4f();

        lookAt(transform.getPosition().add(0,0,-1));
        setPerspective(width/height);
    }

    public void lookAt(Vector3f target){
        view.identity().lookAt(transform.getPosition(), target, new Vector3f(0,1,0).rotate(getTransform().getRotation()));
    }

    public void lookAt(float x, float y, float z){
        lookAt(new Vector3f(x,y,z));
    }

    public Matrix4f getView() {
        return view;
    }

    public Matrix4f getPerspective() {
        return perspective;
    }

    public void setPerspective(float aspect) {
        perspective.setPerspective((float)Math.toRadians(60), aspect, 0.1f, 1000f);
    }
}
