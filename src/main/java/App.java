import core.*;
import utility.Paths;

public class App {
    public static void main(String[] args) {
        Scene scene = new Scene();

        GameObject apa1 = new GameObject(new Model(Paths.MODELS + "monkey.obj"));
        GameObject apa2 = new GameObject(new Model(Paths.MODELS + "monkey.obj"));
        GameObject man = new GameObject(new Model(Paths.MODELS + "human.obj"));

        apa1.translate(1, 0, 0);
        apa2.translate(2, -2, 0);
        man.scale(2);
        man.translate(-1, 0, 0);

        scene.add(apa1);
        scene.add(apa2);
        scene.add(man);

        new Graphics(scene).run();
    }
}
