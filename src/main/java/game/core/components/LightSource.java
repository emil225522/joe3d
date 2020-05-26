package game.core.components;

import system.RenderSystem;
import system.rendering.Light;

public class LightSource extends Component {
    Light light;

    public LightSource(){
        light = new Light();
    }

    @Override
    void start() {
        RenderSystem.get().addLight(light);
    }

    @Override
    void update(float interval) {
        light.getTransform().setPosition(parent.getTransform().getPosition());
    }
}
