package graphics;

import java.awt.*;

public class Material {
    float[] color;

    /**
     * Creates a new material with a default, grey color.
     */
    public Material(){
        color = new float[]{0.5f,0.5f,0.5f,1.0f};
    }

    /**
     * Creates a new customly colored material.
     * @param color an array of values specifying the RGB (or RGBA) channels of the color. If not set, alpha defaults to 1.0f.
     */
    public Material(float[] color){
        setColor(color);
    }

    /**
     * Creates a new customly colored material.
     * @param color an AWT color, needs no further introduction, I gather.
     */
    public Material(Color color){
        this.color = color.getRGBComponents(null);
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
}
