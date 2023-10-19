package is.spbstu.loop;

import is.spbstu.game.Game;
import is.spbstu.game.GameFactory;

public class GameLoop {

    public static void main(String[] args) {
        Game game = GameFactory.createGame(false,3);

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
