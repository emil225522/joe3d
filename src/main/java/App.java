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
        GameObject man1 = new GameObject(new Model(Paths.MODELS + "human.obj"));
        GameObject man2 = new GameObject(new Model(Paths.MODELS + "human.obj"));

        apa1.getTransform().translate(1, 0, 0);
        apa2.getTransform().translate(-1, -0.25f, 1);
        man1.getTransform().translate(0, -0.5f, -1);
        man2.getTransform().scale(-1,1,1);
        man2.getTransform().translate(0, -0.5f, -1);

        man1.getMaterial().setColor(Color.BLUE.getRGBComponents(null));
        man2.getMaterial().setColor(Color.BLUE.getRGBComponents(null));
        apa1.getMaterial().setColor(Color.ORANGE.getRGBComponents(null));
        apa2.getMaterial().setColor(Color.PINK.getRGBComponents(null));

        scene.add(apa1);
        scene.add(apa2);
        scene.add(man1);
        scene.add(man2);

        new Renderer(scene).run();
    }
}
