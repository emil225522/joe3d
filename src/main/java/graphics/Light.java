package graphics;

import core.GameObject;
import core.Transform;

/**
 * A generic light class. Provides basic functionality for concrete light classes.
 */
public abstract class Light extends GameObject {
    private static final float[] globalAmbient = new float[]{0.3f, 0.3f, 0.3f, 1.0f};
    boolean lightOn;
    float[] ambient;

    /**
     * Turns the light on and sets the ambient color to the global ambient.
     */
    public Light(){
        lightOn = true;
        ambient = globalAmbient;
    }

    /**
     * Turns the light off.
     */
    public void turnOff(){
        lightOn = false;
    }

    /**
     * Turns the light on.
     */
    public void turnOn(){
        lightOn = true;
    }

    /**
     * Flips the light switch!
     */
    public void flip(){
        lightOn = !lightOn;
    }
}
