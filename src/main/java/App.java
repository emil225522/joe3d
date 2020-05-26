import game.Game;
import game.LightTestGame;
import game.SpinningMonkeyGame;

public class App {
    public static void main(String[] args) {
        Game game = new LightTestGame();
        GameEngine.run(game);
    }
}
