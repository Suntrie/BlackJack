package is.spbstu.game;

import is.spbstu.card.Card;
import is.spbstu.player.Croupier;
import is.spbstu.player.OrdinaryPlayer;
import is.spbstu.player.Player;

import java.util.Deque;
import java.util.List;

public interface Game {

    void handOutCards();

    Card giveNextCard();

    Croupier getCroupier();

    void performOrdinaryPlayerRounds();

    void performCroupierRounds();

    List<Player> getWinners();

    Deque<OrdinaryPlayer> getPlayers();
}
