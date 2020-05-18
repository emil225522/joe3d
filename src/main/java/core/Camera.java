package core;

import core.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * A camera for transforming views in OpenGL contexts.
 */
public class Camera extends GameObject {
    private Matrix4f perspective;
    private Matrix4f view;

    /**
     * Creates a new camera with the viewport dimensions width and height.
     * @param width the width of the viewport.
     * @param height the height of the viewport.
     */
    public Camera(float width, float height){
        super();
        perspective = new Matrix4f();
        view = new Matrix4f();

        lookAt(transform.getPosition().add(0,0,-1));
        setPerspective(width/height);
    }

    /**
     * Sets the camera view matrix to look at the specified target. The camera up vector is calculated from the roll (z) of the camera rotation
     * @param target the target to look at
     */
    public void lookAt(Vector3f target){
        view.identity().lookAt(transform.getPosition(), target, new Vector3f(0,1,0).rotate(getTransform().getRotation()).normalize());
    }

    /**
     * Sets the camera view matrix to look at the world point (x,y,z). The camera up vector is calculated from the roll (z) of the camera rotation
     * @param x the world space x-coordinate
     * @param y the world space y-coordinate
     * @param z the world space z-coordinate
     */
    public void lookAt(float x, float y, float z){
        lookAt(new Vector3f(x,y,z));
    }

    /**
     * Retrieves a copy of the camera view matrix.
     * @return a view matrix copy.
     * @apiNote As a copy, mutating this matrix will not change the view matrix of the camera.
     */
    public Matrix4f getView() {
        return new Matrix4f(view);
    }

    /**
     * Retrieves a copy of the camera perspective (i.e. the projection matrix)
     * @return a projection matrix copy.
     * @apiNote As a copy, mutating this matrix will not change the view matrix of the camera.
     */
    public Matrix4f getPerspective() {
        return new Matrix4f(perspective);
    }

    /**
     * Updates the perspective (projection) matrix with the specified aspect.
     * @param aspect the width/height ratio of the viewport
     */
    public void setPerspective(float aspect) {
        perspective.setPerspective((float)Math.toRadians(60), aspect, 0.1f, 1000f);
    }
}
