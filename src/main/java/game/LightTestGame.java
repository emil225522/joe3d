package game;

import game.core.GameObject;
import game.core.components.*;
import system.rendering.Mesh;
import utility.Const;
import utility.MeshBuilder;

public class LightTestGame implements Game{
    GameObject[] gos = new GameObject[3];

    @Override
    public void init() {
        Mesh mesh = MeshBuilder.build(Const.MESHES+"suzanne.obj");
        gos[0] = new GameObject(new MeshRenderer(mesh),
                new PlayerController(),
                new Mover());
        gos[1] = new GameObject(new LightSource());
        gos[1].getTransform().translate(0,0, 10);
        gos[2] = new GameObject(new CameraController());
        gos[2].getTransform().translate(0,5,5);

    }

    @Override
    public void start() {
        for (GameObject go : gos) {
            go.start();
        }
    }

    @Override
    public void update(float delta) {
        for (GameObject go : gos) {
            go.update(delta);
        }
    }
}
