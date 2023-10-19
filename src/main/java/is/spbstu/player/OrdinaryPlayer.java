package is.spbstu.player;

import is.spbstu.card.Card;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrdinaryPlayer extends Player{

    private final String name;

    public void receiveCard(Card card, boolean maxValue) {
        setCurrentScore(currentScore + (maxValue? card.maxValue(): card.minValue()));
    }

    @Override
    public String getPlayerName() {
        return name;
    }
}
