package game.core.components;

/**
 * A rotator component that spins the object around an axis.
 */
public class Rotator extends Component {
    float axisX, axisY, axisZ;
    float rotationAmount;

    /**
     * Creates a new rotator component.
     *
     * @param rotationAmount degrees rotation per second
     * @param x              the x component of the rotation axis
     * @param y              the y component of the rotation axis
     * @param z              the z component of the rotation axis
     */
    public Rotator(float rotationAmount, float x, float y, float z) {
        this.axisX = x;
        this.axisY = y;
        this.axisZ = z;
        this.rotationAmount = rotationAmount;
    }

    @Override
    public void start() {

        // Do nothing
    }

    @Override
    public void update(float interval) {
        parent.getTransform().rotate(interval * rotationAmount, axisX, axisY, axisZ);
    }
}
