package is.spbstu.ru.game;

import is.spbstu.game.ConsoleGame;
import is.spbstu.player.OrdinaryPlayer;
import is.spbstu.player.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleGameTest {
    @Test
    void console_game_incorrect_instantiation(){
        assertThrows(IllegalArgumentException.class,
                ()-> new ConsoleGame(-9),"ConsoleGame with negative number of players was created");
    }

    @Test
    void console_game_correct_instantiation(){
        AtomicReference<ConsoleGame> consoleGame = new AtomicReference<>();
        int numberOfPlayers = 3;
        assertDoesNotThrow(()-> consoleGame.set(new ConsoleGame(numberOfPlayers)),
                "ConsoleGame with valid number of players wasn't created");
        assertEquals(numberOfPlayers, consoleGame.get().getPlayers().size());
    }

    @Test
    void perform_croupier_rounds(){
        ConsoleGame consoleGame = new ConsoleGame(3);
        consoleGame.performCroupierRounds();
        assertTrue(consoleGame.getCroupier().getCurrentScore()>=17);
    }

    @Test
    void get_winners_no_winners(){
        ConsoleGame consoleGame = new ConsoleGame(3);

        consoleGame.getCroupier().setCurrentScore(22);
        consoleGame.getPlayers().forEach(
                ordinaryPlayer ->
                        ordinaryPlayer.setCurrentScore(22)
        );

        assertTrue(consoleGame.getWinners().isEmpty());
    }

    @Test
    void get_winners_several_winners_without_croupier(){
        ConsoleGame consoleGame = new ConsoleGame(3);

        consoleGame.getCroupier().setCurrentScore(22);
        consoleGame.getPlayers().forEach(
                ordinaryPlayer ->
                        ordinaryPlayer.setCurrentScore(21)
        );

        assertEquals(3,consoleGame.getWinners().size());
    }

    @Test
    void get_winners_several_winners_with_croupier(){
        ConsoleGame consoleGame = new ConsoleGame(3);

        consoleGame.getCroupier().setCurrentScore(21);
        consoleGame.getPlayers().forEach(
                ordinaryPlayer ->
                        ordinaryPlayer.setCurrentScore(21)
        );

        assertEquals(4,consoleGame.getWinners().size());
    }

    @Test
    void hand_out_cards(){
        ConsoleGame consoleGame = new ConsoleGame(3){
            @Override
            protected boolean askUseMaxAceValue(OrdinaryPlayer ordinaryPlayer) {
                return true;
            }
        };

        Deque<Player> players = new ArrayDeque<>(consoleGame.getPlayers());
        players.add(consoleGame.getCroupier());

        consoleGame.handOutCards();

        players.forEach(player -> {
                    assertTrue(player.getCurrentScore() >= 4);
                    assertTrue(player.getCurrentScore() <=22);
                }
        );

    }

    @Test
    void perform_players_rounds(){
        ConsoleGame consoleGame = new ConsoleGame(3){

            final Map<OrdinaryPlayer, Integer> moreCardsCounterByPlayer = new HashMap<>();

            @Override
            protected boolean askUseMaxAceValue(OrdinaryPlayer ordinaryPlayer) {
                return true;
            }

            @Override
            protected boolean askWantMoreCards(OrdinaryPlayer ordinaryPlayer) {
                int oldValue =moreCardsCounterByPlayer.getOrDefault(ordinaryPlayer, 3);
                int newValue = oldValue - 1;
                moreCardsCounterByPlayer.put(ordinaryPlayer, newValue);

                return newValue>0;
            }
        };

        consoleGame.performOrdinaryPlayerRounds();

        consoleGame.getPlayers().forEach(
                ordinaryPlayer -> {
                    assertTrue(ordinaryPlayer.getCurrentScore()>=4);
                    assertTrue(ordinaryPlayer.getCurrentScore()<=22);
                }
        );
    }
}
