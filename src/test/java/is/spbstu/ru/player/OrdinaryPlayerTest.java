package is.spbstu.ru.player;

import is.spbstu.card.Card;
import is.spbstu.player.OrdinaryPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdinaryPlayerTest {
    @Test
    void correct_ordinary_player_instantiation(){
        assertDoesNotThrow(()-> new OrdinaryPlayer("Player 1"),"Player wasn't created");
    }

    @Test
    void incorrect_ordinary_player_instantiation(){
        assertThrows(IllegalArgumentException.class,
                ()-> new OrdinaryPlayer(" "),"Player was created");
    }

    @Test
    void set_incorrect_current_score(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        assertThrows(IllegalArgumentException.class,
                ()-> ordinaryPlayer.setCurrentScore(-11),"Incorrect current score was set");
    }

    @Test
    void set_correct_current_score(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        assertDoesNotThrow(()-> ordinaryPlayer.setCurrentScore(11),"Correct current score wasn't set");
    }

    @Test
    void receive_not_ace_cards_by_correct_method(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        ordinaryPlayer.receiveCard(new Card(10));
        assertEquals(10, ordinaryPlayer.getCurrentScore());
        ordinaryPlayer.receiveCard(new Card(2));
        assertEquals(12, ordinaryPlayer.getCurrentScore());
    }

    @Test
    void receive_not_ace_cards_by_incorrect_method(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        assertThrows(IllegalArgumentException.class,
                ()-> ordinaryPlayer.receiveCard(new Card(1,11)));
    }

    @Test
    void receive_ace_cards_by_correct_method(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        ordinaryPlayer.receiveCard(new Card(1,11),true);
        assertEquals(11, ordinaryPlayer.getCurrentScore());
        ordinaryPlayer.receiveCard(new Card(1,11),false);
        assertEquals(12, ordinaryPlayer.getCurrentScore());
    }

    @Test
    void is_fast_winner(){
        OrdinaryPlayer ordinaryPlayer = new OrdinaryPlayer("Player 1");
        ordinaryPlayer.setCurrentScore(21);
        assertTrue(ordinaryPlayer.isFastWinner());
        ordinaryPlayer.setCurrentScore(30);
        assertFalse(ordinaryPlayer.isFastWinner());
    }
}
