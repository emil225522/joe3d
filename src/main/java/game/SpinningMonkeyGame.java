package game;

import game.core.GameObject;
import game.core.components.LightSource;
import game.core.components.MeshRenderer;
import game.core.components.Rotator;
import system.rendering.Mesh;
import utility.MeshBuilder;

import java.util.HashSet;
import java.util.Set;

import static utility.Const.MESHES;

public class SpinningMonkeyGame implements Game {
    Set<GameObject> gos = new HashSet<>();

    @Override
    public void init() {
        Mesh mesh = MeshBuilder.build(MESHES + "suzanne.obj");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                GameObject go = new GameObject(
                        new MeshRenderer(mesh),
                        new Rotator((40+10 * i * j / 100) * (float) Math.pow(-1, j * i), 0, 1, 0));
                go.getTransform().translate(-100 + 2 * i, -70f + 2 * j, -100);
                gos.add(go);
            }
        }
        GameObject light = new GameObject(new LightSource());
        gos.add(light);
        System.out.println("Init");
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
