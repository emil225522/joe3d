import core.*;
import utility.Paths;

public class App {
    public static void main(String[] args) {
        GameObject go = new GameObject(new Model(Paths.MODELS+"monkey.obj"));
        GameObject go2 = new GameObject(new Model(Paths.MODELS+"monkey.obj"));

        Scene scene = new Scene();
        scene.add(go);
        scene.add(go2);

        go2.translate(-1,-1,0);
        go2.scale(1.5f);

        go.translate(0,0,-5);

        new Graphics(scene).run();
    }
}
