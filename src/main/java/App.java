import core.Camera;
import core.GameObject;
import core.Scene;
import graphics.*;
import utility.Const;
import utility.MeshBuilder;
import utility.Paths;

import java.awt.*;

/**
 * An entry point for running the engine.
 */
public class App {
    public static void main(String[] args) {
        Camera camera = new Camera(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
        Scene scene = new Scene(camera);

        GameObject apa1 = new RenderObject(MeshBuilder.build(Const.MODELS + "suzanne.obj"), new Material(Color.pink.darker().darker()));
        GameObject apa2 = new RenderObject(MeshBuilder.build(Const.MODELS + "monkey.obj"), new Material(Color.pink.darker().darker()));
        GameObject ball = new RenderObject(MeshBuilder.build(Const.MODELS+"sphere.obj"), new Material(Color.cyan));

        apa1.getTransform().translate(2, 0, 0);
        apa1.getTransform().rotate(-30, 0, 1, 0);
        apa2.getTransform().translate(-2, 0, 0);
        apa2.getTransform().rotate(45, 0, 1, 0);
        camera.getTransform().translate(0,0, 5);
        ball.getTransform().translate(0,-1, -2);
        ball.getTransform().rotate(20, 0,1,1);

        scene.add(apa1);
        scene.add(apa2);
        scene.add(ball);

        new Renderer(scene).run();
    }
}
