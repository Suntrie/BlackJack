package is.spbstu.player;

import is.spbstu.card.Card;


public class OrdinaryPlayer extends Player {

    private final String name;

    public OrdinaryPlayer(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        if (card.isAce()) {
            throw new IllegalArgumentException("Method receiveCard(Card card, boolean maxValue) should be used");
        }
        super.receiveCard(card);
    }

    public void receiveCard(Card card, boolean maxValue) {
        setCurrentScore(currentScore + (maxValue ? card.maxValue() : card.minValue()));
    }

    @Override
    public String getName() {
        return name;
    }
}
