package core;

import utility.Const;
import utility.Paths;
import utility.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class KeyInput implements Publisher<String> {
    public static final int SHIFT = 0b0001;
    public static final int CTRL = 0b0010;
    public static final int ALT = 0b0100;

    Map<Integer, String> messages;
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

    public KeyInput() {
        this.messages = Utils.parseKeyMap(Const.CONFIG + "keymap.txt");
    }

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        publisher.subscribe(subscriber);
    }

    public void read(int key, int action, int mods) {
        int code = key + (mods << 8);
        String message = messages.get(code);
        publisher.submit(message);
    }
}

