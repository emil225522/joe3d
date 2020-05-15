import core.GameObject;
import core.Scene;
import graphics.*;
import utility.Consts;
import utility.Paths;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        Camera camera = new Camera(Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT);
        Scene scene = new Scene(camera);

        GameObject apa1 = new RenderObject(new Model(Paths.MODELS + "monkey.obj"), new Material(Color.pink));
        GameObject apa2 = new RenderObject(new Model(Paths.MODELS + "monkey.obj"), new Material(Color.orange));
        GameObject man1 = new RenderObject(new Model(Paths.MODELS + "human.obj"), new Material(Color.cyan));
        GameObject man2 = new RenderObject(new Model(Paths.MODELS + "human.obj"), new Material(Color.cyan));

        apa1.getTransform().translate(1, 0, 0);
        apa2.getTransform().translate(-1, -0.25f, 1);
        man1.getTransform().translate(0, -0.5f, -1);
        man2.getTransform().scale(-1,1,1);
        man2.getTransform().translate(0, -0.5f, -1);

        camera.getTransform().translate(0,0,3);

        scene.add(apa1);
        scene.add(apa2);
        scene.add(man1);
        scene.add(man2);

        new Renderer(scene).run();
    }
}
