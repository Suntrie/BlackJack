package is.spbstu.loop;

import is.spbstu.game.ConsoleGameCreator;
import is.spbstu.game.Game;

public class GameLoop {

    public static void main(String[] args) {
        Game game = new ConsoleGameCreator().createGame(3);

        game.handOutCards();

        boolean isCroupierWinner = game.getCroupier().isFastWinner();

        if (!isCroupierWinner) {
            game.performOrdinaryPlayerRounds();
            game.performCroupierRounds();
        }

        System.out.println(String.format("Croupier: %s", game.getCroupier()));
        System.out.println(String.format("Winners: %s", game.getWinners()));
    }
}
