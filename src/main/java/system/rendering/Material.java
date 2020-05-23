package system.rendering;

import java.awt.*;

/**
 * A material class, describing materialistic properties such as color, shininess etc.
 */
public class Material implements Cloneable {
    private float[] color;
    float shininess;

    /**
     * Creates a new material with a default, grey color.
     */
    public Material(){
        color = new float[]{0.5f,0.5f,0.5f,1.0f};
        shininess = 1;
    }

    /**
     * Creates a new customly colored material.
     * @param color an AWT color, needs no further introduction, I gather.
     */
    public Material(Color color){
        this(color.getRGBComponents(null), 1);
    }

    /**
     * Creates a new customly colored material.
     * @param color an array of values specifying the RGB (or RGBA) channels of the color. If not set, alpha defaults to 1.0f.
     * @param shininess the shininess factor.
     */
    public Material(float[] color, float shininess){
        setColor(color);
        this.shininess = shininess;
    }

    /**
     * Retrieves a reference to the material color.
     * @return
     */
    public float[] getColor() {
        return color;
    }

    /**
     * Sets the material color.
     * @param color an array of values specifying the RGB (or RGBA) channels of the color. If not set, alpha defaults to 1.0f.
     */
    public void setColor(float[] color) {
        if(color.length < 3 || color.length > 4) throw new IllegalArgumentException("The color argument needs to consist of 3 or 4 float values.");
        if(color.length == 4) this.color = color;
        else this.color = new float[]{color[0], color[1], color[2], 1.0f};
    }

    public float getShininess() {
        return shininess;
    }

    public Material clone(){
        return new Material(color, shininess);
    }
}
