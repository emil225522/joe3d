import system.rendering.*;
import utility.MeshBuilder;

import static utility.Const.*;

/**
 * An entry point for running the engine.
 */
public class GameEngine {
    public static void main(String[] args) {
        RenderSystem.startUp();
        RenderSystem renderer = RenderSystem.get();

        Mesh mesh = MeshBuilder.build(MESHES+"suzanne.obj");
        renderer.addRenderInfo(mesh, new Transform());

        renderer.run();

        RenderSystem.shutDown();
    }
}
