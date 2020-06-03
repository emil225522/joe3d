package game.core.components;

import org.joml.Vector3f;
import system.InputSystem;
import system.rendering.Transform;

public class PlayerController extends Component implements Controller {
    private InputSystem input;
    private Transform transform;

    private int speed = 10;
    private Vector3f moveVector = new Vector3f();
    private int rotate = 0; // TODO refactor to vector and change Mover rotate function

    @Override
    public void start() {
        input = InputSystem.get();
        transform = parent.getTransform();
    }

    @Override
    public void update(float interval) {
        handleMove();
        handleRotate();
    }

    private void handleRotate() {
        if (input.isKeyPressed('a') && !input.isKeyPressed('d')) {
            rotate = 1;
        } else if (input.isKeyPressed('d') && !input.isKeyPressed('a')) {
            rotate = -1;
        } else {
            rotate = 0;
        }
    }

    private void handleMove() {
        if (input.isKeyPressed('w') && !input.isKeyPressed('s')) {
            moveVector.z = 1;
        } else if (input.isKeyPressed('s') && !input.isKeyPressed('w')) {
            moveVector.z = -1;
        } else {
            moveVector.z = 0;
        }

//        if(input.isKeyPressed('a') && !input.isKeyPressed('d')){
//            moveVector.x = -1;
//        } else if(input.isKeyPressed('d') && !input.isKeyPressed('a')){
//            moveVector.x = 1;
//        } else {
//            moveVector.x = 0;
//        }
    }

    @Override
    public void onRemove() {

    }

    @Override
    public Vector3f getMoveInput() {
        return moveVector.lengthSquared() < 0.01f ? new Vector3f() : moveVector.normalize();
    }

    @Override
    public int getRotateInput() {
        return rotate;
    }
}
