package core;

import java.util.*;
import java.util.concurrent.Flow;

/**
 * A 3D scene, capable of holding multiple 3D objects for future rendering.
 */
public class Scene {
    private List<GameObject> objects;

    /**
     * Creates a new, empty scene. Adds the camera to the scene.
     * @param camera the main camera of the scene.
     */
    public Scene(GameObject camera){
        objects = new ArrayList<>();
        objects.add(camera);
    }

    /**
     * Adds a game object to the scene.
     * @param obj the game object to add.
     */
    public void add(GameObject obj){
        objects.add(obj);
    }

    /**
     * Removes a game object from the scene.
     * @param obj the game object to remove.
     */
    public void remove(GameObject obj){
        objects.remove(obj);
    }

    /**
     * Retrieves all objects in the scene as a list.
     * @return a list containing all objects in the scene.
     */
    public Collection<GameObject> getObjects() {
        return objects;
    }

    /**
     * Gets a reference to the main camera of the scene.
     * @return the main camera of the scene.
     */
    public Camera getMainCamera() {
        return (Camera) objects.get(0);
    }
}
