package game.core.components;

import system.RenderSystem;
import system.rendering.Camera;

public class CameraController extends Component{
    Camera cam;

    @Override
    public void start() {
        cam = RenderSystem.get().getCam();
    }

    @Override
    public void update(float interval) {
        cam.getTransform().setPosition(parent.getTransform().getPosition());
    }

    @Override
    public void onRemove() {

    }
}
