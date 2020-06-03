package game.core.components;

import system.RenderSystem;
import system.rendering.Mesh;

/**
 * A renderable game object. It has a mesh and a material.
 */
public class MeshRenderer extends Component {
    private int[] bufferIds;
    private Mesh mesh;

    /**
     * Creates a new renderable object with the specified mesh and material.
     *
     * @param mesh the object mesh
     */
    public MeshRenderer(Mesh mesh) {
        setMesh(mesh);
    }

    /**
     * Sets the mesh of the mesh renderer component.
     * @param mesh the mesh to render
     */
    public void setMesh(Mesh mesh){
        this.mesh = mesh;
    }

    @Override
    public void start() {
        bufferIds = RenderSystem.get().addRenderInfo(mesh, parent.getTransform());  // TODO remove SRP violation
    }

    @Override
    public void update(float interval) {
        // Called every frame
    }

    @Override
    public void onRemove() {
        RenderSystem.get().removeRenderInfo(bufferIds);
    }
}
