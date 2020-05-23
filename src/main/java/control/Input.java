package control;

import core.InputMessage;
import core.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class Input implements Publisher<InputMessage> {
    public static final int RELEASED = 0;
    public static final int PRESSED = 1;
    public static final int HELD = 2;
    public static final int SHIFT = 0b0001;
    public static final int CTRL = 0b0010;
    public static final int ALT = 0b0100;

    Map<Integer, String> monitor;
    SubmissionPublisher<InputMessage> publisher = new SubmissionPublisher<>();

    /**
     * A hardcoded input class, holding and sending messages about what key actions answer to what messages.
     * TODO make customizable
     */
    public Input() {
        this.monitor = new HashMap<>();
    }

    @Override
    public void subscribe(Subscriber<? super InputMessage> subscriber) {
        publisher.subscribe(subscriber);
    }

    public void read(int key, int action, int mods) {
        if(monitor.containsKey(key)){
            publisher.submit(new InputMessage(key, action, mods));  // TODO actually interpret and send specific messages, not just relay the callback event...
        }
    }

    private Map<Integer, Message> parseKeyMap(String filepath){
        // TODO implement customizable keymaps
        return null;
    }
}

