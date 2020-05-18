package graphics;

import core.GameObject;

/**
 * A renderable game object. In addition to being a game object, it also has a mesh and a material.
 */
public class RenderObject extends GameObject {
    private Mesh mesh;
    private Material material;

    /**
     * Creates a new renderable object with the specified mesh and a default material.
     * @param mesh the object mesh
     */
    public RenderObject(Mesh mesh) {
        this(mesh, new Material());
    }

    /**
     * Creates a new renderable object with the specified mesh and material.
     * @param mesh the object mesh
     * @param material the object material
     */
    public RenderObject(Mesh mesh, Material material) {
        super();
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
}
