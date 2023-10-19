package is.spbstu.player;


import is.spbstu.card.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Player {

    protected int currentScore = 0;


    public void receiveCard(Card card) {
        setCurrentScore(currentScore + (currentScore + card.maxValue() > 21? card.minValue(): card.maxValue()));
    }

    public boolean isWinner() {
        return getCurrentScore() == 21;
    }

}
