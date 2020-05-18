import core.Camera;
import core.GameObject;
import core.Scene;
import graphics.*;
import utility.Consts;
import utility.MeshBuilder;
import utility.Paths;

import java.awt.*;

/**
 * An entry point for running the engine.
 */
public class App {
    public static void main(String[] args) {
        Camera camera = new Camera(Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT);
        Scene scene = new Scene(camera);

        GameObject apa1 = new RenderObject(MeshBuilder.build(Paths.MODELS + "monkey.obj"), new Material(Color.pink));
        GameObject apa2 = new RenderObject(MeshBuilder.build(Paths.MODELS + "monkey.obj"), new Material(Color.orange));

        apa1.getTransform().translate(1, 0, 0);
        apa2.getTransform().translate(-1, -0.25f, 1);
        camera.getTransform().translate(0,0,5);

        scene.add(apa1);
        scene.add(apa2);

        new Renderer(scene).run();
    }
}
