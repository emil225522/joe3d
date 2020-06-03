package game.core.components;

import org.joml.Vector3f;

public class Mover extends Component {

    Controller controller;
    private float speed = 5;
    private float rotateSpeed = 120;

    @Override
    public void start() {
        controller = getSibling(PlayerController.class);
        if(controller == null) throw new IllegalStateException("Unable to find controller component on gameobject!");
    }

    @Override
    public void update(float interval) {
        move(interval);
        rotate(interval);
    }

    private void rotate(float interval) {
        parent.getTransform().rotate(interval*rotateSpeed*controller.getRotateInput(), 0, 1, 0);
    }

    private void move(float interval) {
        Vector3f move = controller.getMoveInput().mul(interval*speed);
        move.rotate(parent.getTransform().getRotation());
        parent.getTransform().translate(move);
    }

    @Override
    public void onRemove() {

    }
}
