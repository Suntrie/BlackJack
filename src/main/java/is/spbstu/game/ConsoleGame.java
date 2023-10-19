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

        if (numberOfPlayers < 0) {
            throw new IllegalArgumentException("Number of players should be >=0");
        }

        for (int i = 0; i < NUMBER_OF_CARD_PACKS; i++) {
            cards.addAll(new CardPack().getCards());
        }
        Collections.shuffle(cards);
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new OrdinaryPlayer(String.format("Player %d", i)));
        }

        croupier = new Croupier();
    }

    @Override
    public void handOutCards() {
        for (int i = 0; i < 2; i++) {
            for (OrdinaryPlayer player : players) {
                Card nextCard = giveNextCard();
                boolean isMaxValue = false;
                if (nextCard.isAce()) {
                    isMaxValue = askUseMaxAceValue(player);
                }
                player.receiveCard(nextCard, isMaxValue);
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
        Deque<OrdinaryPlayer> playersInGame = new ArrayDeque<>(players.stream().filter(player -> player.getCurrentScore() < 21).toList());

        while (!playersInGame.isEmpty()) {
            OrdinaryPlayer currentPlayer = playersInGame.removeLast();
            System.out.println("--------------------------------------------");
            boolean wantMoreCards = askWantMoreCards(currentPlayer);
            boolean askedOnce = false;
            while (wantMoreCards) {
                askedOnce = true;
                Card nextCard = giveNextCard();
                boolean isMaxValue = false;
                if (nextCard.isAce()) {
                    isMaxValue = askUseMaxAceValue(currentPlayer);
                }
                currentPlayer.receiveCard(nextCard, isMaxValue);
                System.out.println(String.format("Current score of %s is: %s", currentPlayer.getName(),
                        currentPlayer.getCurrentScore()));
                wantMoreCards = askWantMoreCards(currentPlayer);
            }
            if ((currentPlayer.getCurrentScore() < 21) && (askedOnce)) {
                playersInGame.addFirst(currentPlayer);
            }
        }
    }

    protected boolean askWantMoreCards(OrdinaryPlayer ordinaryPlayer) {
        return ordinaryPlayer.getCurrentScore() < 21 && askValidation(ordinaryPlayer, "Do you want more cards?");
    }

    protected boolean askUseMaxAceValue(OrdinaryPlayer ordinaryPlayer) {
        return askValidation(ordinaryPlayer, "Do you want to use the ace with value of 11? Yes / no");
    }


    private boolean askValidation(OrdinaryPlayer ordinaryPlayer, String proposal) {
        Scanner s = new Scanner(System.in);

        while (true) {

            System.out.println(String.format("Hello, %s", ordinaryPlayer.getName()));
            System.out.println(String.format("Your current score: %s", ordinaryPlayer.getCurrentScore()));
            System.out.println(proposal);
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

    @Override
    public Deque<OrdinaryPlayer> getPlayers() {
        return new ArrayDeque<>(this.players);
    }

}
