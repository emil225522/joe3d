package core.lights;

public class PositionalLight extends Light {
    float[] diffuse;
    float[] specular;

    public PositionalLight(){
        diffuse = new float[]{};
        specular = new float[]{};
    }
}
