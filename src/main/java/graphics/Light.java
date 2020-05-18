package graphics;

import core.GameObject;
import core.Transform;

/**
 * A generic light class. Provides basic functionality for concrete light classes.
 */
public abstract class Light extends GameObject {
    public static final float[] globalAmbient = new float[]{0.3f, 0.3f, 0.3f, 1.0f};
    float[] ambient;
    float[] diffuse;
    float[] specular;

    /**
     * Turns the light on and sets the ambient color to the global ambient.
     */
    public Light(){
        ambient = new float[]{0.3f,0.3f,0.3f,1};
        diffuse = new float[]{0.5f,0.5f,0.5f,1};
        specular = new float[]{0.7f, 0.7f, 0.7f, 1};

    }

    public float[] getAmbient() {
        return ambient;
    }

    public float[] getDiffuse() {
        return diffuse;
    }

    public float[] getSpecular(){
        return specular;
    }
}
