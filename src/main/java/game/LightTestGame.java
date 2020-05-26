package game;

import game.core.GameObject;
import game.core.components.LightSource;
import game.core.components.MeshRenderer;
import game.core.components.Rotator;
import utility.Const;
import utility.MeshBuilder;

public class LightTestGame implements Game{
    GameObject[] gos = new GameObject[3];

    @Override
    public void init() {
        gos[0] = new GameObject(new MeshRenderer(MeshBuilder.build(Const.MESHES+"suzanne.obj")));
        gos[1] = new GameObject(new Rotator(20,0,1,0),new MeshRenderer(MeshBuilder.build(Const.MESHES+"suzanne.obj")));
        gos[2] = new GameObject(new LightSource());
        gos[0].getTransform().translate(-2, 0, 0);
        gos[1].getTransform().translate(2, 0, 0);
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
