package game.core.components;

import org.joml.Vector3f;

public interface Controller {
    Vector3f getMoveInput();
    int getRotateInput();   // TODO return vector
}
