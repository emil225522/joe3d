package core;

import java.util.*;

public class Scene {
    private List<GameObject> renderObjects;
    private Camera mainCamera;

    public Scene(Camera camera){
        this.mainCamera = camera;
        renderObjects = new ArrayList<>();
    }

    public void add(GameObject ro){
        renderObjects.add(ro);
    }

    public void remove(GameObject ro){
        renderObjects.remove(ro);
    }

    public Collection<GameObject> getObjects() {
        return renderObjects;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }
}
