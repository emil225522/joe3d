package graphics;

import core.Transform;

public abstract class Light {
    private static final float[] globalAmbient = new float[]{0.3f, 0.3f, 0.3f, 1.0f};
    Transform transform;
    boolean lightOn;
    float[] ambient;

    public Light(){
        lightOn = true;
        ambient = globalAmbient;
    }

    public void turnOff(){
        lightOn = false;
    }

    public void turnOn(){
        lightOn = true;
    }

    public void flip(){
        lightOn = !lightOn;
    }
}
