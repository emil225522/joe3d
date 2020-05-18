package core;

import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * A container for position, scale and rotation values.
 */
public class Transform {
    private Vector3f position;
    private Vector3f scale;
    private Quaternionf rotation;

    /**
     * Creates a new transform, defaulting position to (0,0,0), scale to (1,1,1) and rotation to the identity rotation.
     */
    public Transform() {
        this.position = new Vector3f();
        this.scale = new Vector3f(1);
        this.rotation = new Quaternionf();
    }

    /**
     * Translates the transform in world space along the translation vector.
     * @param translation a vector with x, y and z components.
     */
    public void translate(Vector3f translation) {
        position.add(translation);
    }

    /**
     * Translates the transform in world space along the x, y and z axes.
     * @param x the translation along the x-axis.
     * @param y the translation along the y-axis.
     * @param z the translation along the z-axis.
     */
    public void translate(float x, float y, float z) {
        position.add(x,y,z);
    }

    /**
     * Scales the transform along the x, y and z axes.
     * @apiNote Mutates state
     * @param scalar value to scale all components with.
     * @apiNote Mutates state
     */
    public void scale(float scalar) {
        scale.mul(scalar);
    }

    /**
     * Scales the transform along its x, y and z axes.
     * @apiNote Mutates state
     * @param x scalar for scaling the x-component
     * @param y scalar for scaling the y-component
     * @param z scalar for scaling the z-component
     */
    public void scale(float x, float y, float z){
        scale.mul(x,y,z);
    }

    /**
     * Performs a rotation deg around the axis specified by the vector (x,y,z)
     * @param deg angle in degrees
     * @param x the x-component of the rotation axis
     * @param y the y-component of the rotation axis
     * @param z the z-component of the rotation axis
     */
    public void rotate(float deg, float x, float y, float z){
        rotation.rotateAxis((float)Math.toRadians(deg), x, y, z);
    }

    /**
     * Returns a <b>cloned</b> instance of the transform position
     * @return cloned position value
     */
    public Vector3f getPosition() { return new Vector3f(position);
    }

    /**
     * Returns a <b>cloned</b> instance of the transform scale
     * @return cloned scale values
     */
    public Vector3f getScale() {
        return new Vector3f(scale);
    }

    /**
     * Returns a <b>cloned</b> instance of the transform rotation
     * @return cloned rotation value
     */
    public Quaternionf getRotation() {
        return new Quaternionf(rotation);
    }
}
