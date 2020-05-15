package graphics;

import core.GameObject;

public class RenderObject extends GameObject {
    private Mesh mesh;
    private Material material;

    public RenderObject(Mesh mesh) {
        this(mesh, new Material());
    }

    public RenderObject(Mesh mesh, Material material) {
        super();
        this.mesh = mesh;
        this.material = material;
    }

    /**
     * Retrieves a reference to the mesh of the game object
     * @return
     */
    public Mesh getMesh(){
        return mesh;
    }

    public Material getMaterial() {
        return material;
    }
}
