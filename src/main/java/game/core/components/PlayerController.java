package game.core.components;

import system.InputSystem;
import system.rendering.Transform;

public class PlayerController extends Component {
    InputSystem input;
    Transform transform;
    private int speed = 2;



    @Override
    public void start() {
        input = InputSystem.get();
        transform = parent.getTransform();
    }

    @Override
    public void update(float interval) {
        if(input.isKeyPressed('w')){
            transform.translate(0,0,-speed*interval);
        }
        if(input.isKeyPressed('a')){
            transform.translate(-speed*interval,0,0);
        }
        if(input.isKeyPressed('s')){
            transform.translate(0,0,speed*interval);
        }
        if(input.isKeyPressed('d')){
            transform.translate(speed*interval,0,0);
        }
    }

    @Override
    public void onRemove() {

    }
}
