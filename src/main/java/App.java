import core.GameObject;
import core.*;
import core.geometry.Model;
import core.graphics.Renderer;
import utility.Paths;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        final int WIDTH = 512;
        final int HEIGHT = 512;

        Camera camera = new Camera(WIDTH, HEIGHT);
        Scene scene = new Scene(camera);

        GameObject apa1 = new GameObject(new Model(Paths.MODELS + "monkey.obj"));
        GameObject apa2 = new GameObject(new Model(Paths.MODELS + "monkey.obj"));
        GameObject man = new GameObject(new Model(Paths.MODELS + "human.obj"));
        GameObject man2 = new GameObject(new Model(Paths.MODELS + "human.obj"));

        apa1.getTransform().translate(1, 0, 0);
        apa2.getTransform().translate(2, -2, 0);
        man.getTransform().translate(-1, -0.5f, 0);
        man2.getTransform().scale(-1,1,1);
        man2.getTransform().translate(1, -0.5f, 0);

        scene.add(apa1);
        scene.add(apa2);
        scene.add(man);
        scene.add(man2);

        new Renderer(scene).run();
    }
}
