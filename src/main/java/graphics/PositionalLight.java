package graphics;

/**
 * A positional light.
 */
public class PositionalLight extends Light {
    float[] diffuse;
    float[] specular;

    /**
     * Creates a new positional light with default ambient, diffuse and specular colors.
     */
    public PositionalLight(){
        // TODO implement
        super();
        diffuse = new float[]{};
        specular = new float[]{};
    }
}
