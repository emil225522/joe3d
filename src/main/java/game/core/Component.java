package game.core;

public abstract class Component {
    GameObject parent;

    Component(GameObject parent){
        this.parent = parent;
    }

    abstract void start();
    abstract void update();
}
