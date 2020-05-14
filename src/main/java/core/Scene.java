package core;

import java.util.*;

public class Scene {
    List<GameObject> renderObjects;

    public Scene(){
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
}
