package game;

import java.util.ArrayList;

import game.core.GameObject;
import game.core.components.*;
import system.rendering.Mesh;
import utility.Const;
import utility.MeshBuilder;

public class CollisionTestGame implements Game{
	ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    @Override
    public void init() {
        Mesh mesh1 = MeshBuilder.build(Const.MESHES+"suzanne.obj");
        Mesh mesh2 = MeshBuilder.build(Const.MESHES+"sphere.obj");
        gameObjects.add(new GameObject(new MeshRenderer(mesh1),
                new PlayerController(),
                new Mover(), new HitBox()));
        gameObjects.add(new GameObject(new LightSource()));
        gameObjects.get(1).getTransform().translate(0,0, 10);
        gameObjects.add(new GameObject(new CameraController()));
        gameObjects.get(2).getTransform().translate(0,5,5);
        gameObjects.add(new GameObject(new MeshRenderer(mesh2)));

    }

    @Override
    public void start() {
        for (GameObject go : gameObjects) {
            go.start();
        }
    }

    @Override
    public void update(float delta) {
        for (GameObject go : gameObjects) {
            go.update(delta);
        }
    }
}
