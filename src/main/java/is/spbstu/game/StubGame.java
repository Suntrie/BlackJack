package is.spbstu.game;

import is.spbstu.card.Card;
import is.spbstu.player.Croupier;
import is.spbstu.player.OrdinaryPlayer;
import is.spbstu.player.Player;

import java.util.Deque;
import java.util.List;

public class StubGame implements Game{
    public StubGame(int numberOfPlayers) {
    }

    @Override
    public void handOutCards() {
    }

    @Override
    public Card giveNextCard() {
        return null;
    }

    @Override
    public Croupier getCroupier() {
        return null;
    }

    @Override
    public void performOrdinaryPlayerRounds() {
    }

    @Override
    public void performCroupierRounds() {

    }

    @Override
    public List<Player> getWinners() {
        return null;
    }

    @Override
    public Deque<OrdinaryPlayer> getPlayers() {
        return null;
    }
}
