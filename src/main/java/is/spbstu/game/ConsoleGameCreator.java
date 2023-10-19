package is.spbstu.game;

public class ConsoleGameCreator implements GameCreator{
    @Override
    public Game createGame(int numberOfPlayers) {
        return new ConsoleGame(numberOfPlayers);
    }
}
