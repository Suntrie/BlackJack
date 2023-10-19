package is.spbstu.ru.card;

import is.spbstu.card.CardPack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardPackTest {
    @Test
    void correct_card_pack_instantiation(){

        CardPack cardPack = new CardPack();
        int k=0;

        while(k<cardPack.getCards().size()) {
            for (int i = CardPack.NUMERIC_CARDS_MIN_VALUE; i <= CardPack.NUMERIC_CARDS_MAX_VALUE; i++) {
                assertEquals(i, cardPack.getCards().get(k).minValue());
                assertEquals(i, cardPack.getCards().get(k).maxValue());
                k++;
            }

            for (int i=0; i< CardPack.SYMBOLIC_CARDS_NUMBER; i++){
                assertEquals(CardPack.SYMBOLIC_CARDS_VALUE, cardPack.getCards().get(k).minValue());
                assertEquals(CardPack.SYMBOLIC_CARDS_VALUE, cardPack.getCards().get(k).maxValue());
                k++;
            }

            assertEquals(CardPack.ACE_MIN_VALUE, cardPack.getCards().get(k).minValue());
            assertEquals(CardPack.ACE_MAX_VALUE, cardPack.getCards().get(k).maxValue());

            k++;
        }

    }



}
