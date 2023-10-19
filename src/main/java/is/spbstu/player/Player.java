package is.spbstu.player;

import is.spbstu.card.Card;
import java.util.Objects;

public abstract class Player {
    protected int currentScore = 0;

    public void receiveCard(Card card) {
        setCurrentScore(currentScore + (currentScore + card.maxValue() > 21 ? card.minValue() : card.maxValue()));
    }

    public boolean isFastWinner() {
        return getCurrentScore() == 21;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return "Player{" +
                "name=" + getName() + "," +
                "currentScore=" + currentScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        if (currentScore < 0) {
            throw new IllegalArgumentException("Current score shouldn't be less than zero");
        }
        this.currentScore = currentScore;
    }
}
