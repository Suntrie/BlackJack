package is.spbstu.card;

import java.util.ArrayList;
import java.util.List;

public class CardPack {

    public static final int NUMERIC_CARDS_MIN_VALUE = 2;
    public static final int NUMERIC_CARDS_MAX_VALUE = 10;

    public static final int SYMBOLIC_CARDS_NUMBER = 3;
    public static final int SYMBOLIC_CARDS_VALUE = 10;

    public static final int ACE_MIN_VALUE = 1;
    public static final int ACE_MAX_VALUE = 11;

    public static final int CARD_COLORS_NUMBER = 4;

    private final List<Card> cards = new ArrayList<>();

    public CardPack() {

        for (int k = 0; k < CARD_COLORS_NUMBER; k++) {

            for (int i = NUMERIC_CARDS_MIN_VALUE; i <= NUMERIC_CARDS_MAX_VALUE; i++) {
                cards.add(new Card(i));
            }

            for (int i = 0; i < SYMBOLIC_CARDS_NUMBER; i++) {
                cards.add(new Card(SYMBOLIC_CARDS_VALUE));
            }

            cards.add(new Card(ACE_MIN_VALUE, ACE_MAX_VALUE));
        }
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
