package control;

import org.joml.Vector2f;

import java.util.concurrent.Flow.*;

public class PlayerController implements Subscriber<String> {
    Vector2f moveVector;

    public PlayerController(){
        moveVector = new Vector2f();
    }

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(String item) {
        switch(item){
            case "WALK_FORWARD_PRESS":
            case "WALK_FORWARD_RELEASE":

        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
