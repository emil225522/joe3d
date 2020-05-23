package core;

import static org.lwjgl.glfw.GLFW.*;

public class InputMessage extends Message {
    int key;
    int action;
    int mods;

    public InputMessage(int key, int action, int mods) {    // TODO this probably shouldn't be parameterized like this
        super(Category.Input);
        this.key = key;
        this.action = action;
        this.mods = mods;
    }

    @Override
    public void process() {

    }
}
