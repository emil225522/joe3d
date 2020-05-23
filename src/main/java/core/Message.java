package core;

public abstract class Message {
    protected enum Category {
        Render,
        Update,
        Input
    }

    Category category;

    public Message(Category category){
        this.category = category;
    }

    public abstract void process();
}
