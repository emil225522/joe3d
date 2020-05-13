import org.joml.Quaternionf;
import org.joml.Vector3f;

public class GameObject{
    Vector3f position;
    Vector3f scale;
    Quaternionf rotation;

    public GameObject(){
        this.position = new Vector3f();
        this.scale = new Vector3f(1);
        this.rotation = new Quaternionf();
    }

    public Vector3f getPosition() {
        return position;
    }
}
