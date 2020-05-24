package game.core;

import org.joml.Matrix4f;
import system.rendering.Material;
import system.rendering.Mesh;

/**
 * A renderable game object. In addition to being a game object, it also has a mesh and a material.
 */
public class MeshRenderer extends Component {
    private Mesh mesh;
    private Material material;

    /**
     * Creates a new renderable object with the specified mesh and a default material.
     * @param mesh the object mesh
     */
    public MeshRenderer(GameObject parent, Mesh mesh) {
        this(parent, mesh, new Material());
    }

    /**
     * Creates a new renderable object with the specified mesh and material.
     * @param mesh the object mesh
     * @param material the object material
     */
    public MeshRenderer(GameObject parent, Mesh mesh, Material material) {
        super(parent);
        this.mesh = mesh;
        this.material = material;
    }

    /**
     * Retrieves a reference to the object mesh
     * @return the object material
     */
    public Mesh getMesh(){
        return mesh;
    }

    /**
     * Retrieves a reference to the object material
     * @return the object material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Retrieves the model matrix for the parent GameObject of this component.
     * @return a position, scale and rotation transformation matrix.
     */
    public Matrix4f getModelMatrix(){
        return parent.getTransform().calculateModelMatrix();
    }



    @Override
    void start() {

    }

    @Override
    void update() {

    }
}
