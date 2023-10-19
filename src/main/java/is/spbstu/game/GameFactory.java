package is.spbstu.game;

//Creators & diff products = complex logic
public class GameFactory {

    public static Game createGame(boolean debug, int numberOfPlayers){
        if (debug) {
            return new StubGame(numberOfPlayers);
        }else{
            return new ConsoleGame(numberOfPlayers);
        }
    }
}
