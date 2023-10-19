package is.spbstu.ru.card;

import is.spbstu.card.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {

    @Test
    void correct_cards_instantiation(){
        assertDoesNotThrow(()->List.of(new Card(1,11), new Card(2), new Card(10)),"Cards were not created");
    }

    @Test
    void incorrect_cards_instantiation(){
        assertThrows(IllegalArgumentException.class,()->new Card(2,11),"Card with illegal argument values was created");
        assertThrows(IllegalArgumentException.class,()->new Card(0),"Card with illegal argument values was created");
        assertThrows(IllegalArgumentException.class,()->new Card(13),"Card with illegal argument values was created");
    }

}
