package is.spbstu.game;

import is.spbstu.card.Card;
import is.spbstu.card.CardPack;
import is.spbstu.player.Croupier;
import is.spbstu.player.OrdinaryPlayer;
import is.spbstu.player.Player;

import java.util.*;

public class ConsoleGame implements Game {

    public static final int NUMBER_OF_CARD_PACKS = 8;
    private final Deque<OrdinaryPlayer> players = new ArrayDeque<>();
    private final List<Card> cards = new ArrayList<>();
    private final Croupier croupier;

    public ConsoleGame(int numberOfPlayers) {
        for (int i = 0; i < NUMBER_OF_CARD_PACKS; i++) {
            cards.addAll(new CardPack().getCards());
        }
        Collections.shuffle(cards);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new OrdinaryPlayer());
        }

        croupier = new Croupier();
    }

    @Override
    public void handOutCards() {
        for (int i = 0; i < 2; i++) {
            for (OrdinaryPlayer player : players) {
                Card nextCard = giveNextCard();
                player.receiveCard(nextCard);
            }
        }

        for (int i = 0; i < 2; i++) {
            Card nextCard = giveNextCard();
            getCroupier().receiveCard(nextCard);
        }
    }



    @Override
    public Card giveNextCard() {
        return cards.remove(cards.size() - 1);
    }


    @Override
    public Croupier getCroupier() {
        return croupier;
    }


    @Override
    public void performOrdinaryPlayerRounds() {
        Deque<OrdinaryPlayer> playersInGame = new ArrayDeque<>(players.stream().filter(player -> player.getCurrentScore()<21).toList());

        while (!playersInGame.isEmpty()) {
            OrdinaryPlayer currentPlayer = playersInGame.removeLast();
            boolean wantMoreCards = askWantMoreCards(currentPlayer);
            if (wantMoreCards) {
                Card nextCard = giveNextCard();
                boolean isMaxValue = false;
                if (nextCard.isAce()) {
                    isMaxValue = askWhichValueToUse();
                }
                currentPlayer.receiveCard(nextCard, isMaxValue);
            }
            if ((currentPlayer.getCurrentScore() < 21) && (wantMoreCards)){
                playersInGame.addFirst(currentPlayer);
            }
        }
    }

    private boolean askWantMoreCards(OrdinaryPlayer ordinaryPlayer) {
        return ordinaryPlayer.getCurrentScore()<21 && askValidation();
    }

    private Boolean askWhichValueToUse() {
        return askValidation();
    }


    private Boolean askValidation() {
        Scanner s = new Scanner(System.in);

        while (true) {

            System.out.println("Do you want to use th ace with value of 11? Yes / no");
            String answer = s.next().toLowerCase();

            if (!Set.of("yes", "no").contains(answer)) {
                System.out.println("Incorrect answer, please, try again");
            } else {
                return "yes".equals(answer);
            }
        }
    }

    @Override
    public void performCroupierRounds() {
        while (getCroupier().getCurrentScore() < 17) {
            Card nextCard = giveNextCard();
            getCroupier().receiveCard(nextCard);
        }
    }

    //Collections.unmodifiableList(players.stream().toList());
    public List<Player> getWinners() {
        List<Player> playersWithCroupier = new ArrayList<>(players);
        playersWithCroupier.add(getCroupier());
        List<Player> nonFailingPlayers = playersWithCroupier.stream().filter(player -> player.getCurrentScore() <= 21).toList();
        Optional<Integer> maxScore = nonFailingPlayers.stream().map(Player::getCurrentScore).max(Comparator.naturalOrder());
        return nonFailingPlayers.stream().filter(player -> player.getCurrentScore() == maxScore.get()).toList();
    }
}
