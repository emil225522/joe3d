package game.core.components;

import system.RenderSystem;
import system.rendering.Light;

public class LightSource extends Component {
    Light light;

    public LightSource(){
        light = new Light();
    }

    @Override
    public void start() {
        RenderSystem.get().addLight(light);
    }

    @Override
    public void update(float interval) {
        light.getTransform().setPosition(parent.getTransform().getPosition());
    }

    @Override
    public void onRemove() {
        RenderSystem.get().removeLight(light);
    }
}
