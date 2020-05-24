package game;

import game.core.GameObject;
import game.core.MeshRenderer;
import game.core.Rotator;
import system.RenderSystem;
import system.rendering.Mesh;
import utility.MeshBuilder;

import java.util.HashSet;
import java.util.Set;

import static utility.Const.MESHES;

public class SpinningMonkeyGame implements Game {
    RenderSystem renderSystem;
    Set<GameObject> gos = new HashSet<>();

    @Override
    public void init() {
        renderSystem = RenderSystem.get();
        Mesh mesh = MeshBuilder.build(MESHES + "suzanne.obj");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                GameObject go = new GameObject(new MeshRenderer(mesh), new Rotator((40+10 * i * j) * (float) Math.pow(-1, j * i), 0, 1, 0));
                go.getTransform().translate(-3 + 2 * i, -3f + 2 * j, -2);
                gos.add(go);
            }
        }
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
