package core;

import java.awt.*;

public class Material {
    float[] color;

    public Material(float[] color){
        if(color.length < 3 || color.length > 4) throw new IllegalArgumentException("The color argument needs to consist of 3 or 4 float values.");
        if(color.length == 4) this.color = color;
        else this.color = new float[]{color[0], color[1], color[2], 1.0f};
    }

    public Material(Color color){
        this.color = color.getRGBComponents(null);
    }
}
