package is.spbstu.player;

import is.spbstu.card.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdinaryPlayer extends Player{

    public void receiveCard(Card card, boolean maxValue) {
        setCurrentScore(currentScore + (maxValue? card.maxValue(): card.minValue()));
    }

}
