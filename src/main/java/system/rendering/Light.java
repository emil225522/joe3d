package system.rendering;

/**
 * A generic light class. Provides basic functionality for concrete light classes.
 */
public class Light {
    public static final float[] GLOBAL_AMBIENT = new float[]{0.3f, 0.3f, 0.3f, 1.0f};

    private Transform transform;
    private float[] ambient;
    private float[] diffuse;
    private float[] specular;



    /**
     * Turns the light on and sets the ambient color to the global ambient.
     */
    public Light(){
        this.transform = new Transform();
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

    public Transform getTransform() {
        return transform;
    }
}
