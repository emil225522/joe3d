import game.Game;
import game.SpinningMonkeyGame;

public class App {
    public static void main(String[] args) {
        Game game = new SpinningMonkeyGame();
        GameEngine.run(game);
    }
}
