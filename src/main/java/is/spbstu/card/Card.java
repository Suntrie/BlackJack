package is.spbstu.card;

public record Card(int minValue, int maxValue) {

    public Card {
        if ((minValue < 1 || minValue > 10) ||
                (maxValue < 2 || maxValue > 11) ||
                (minValue != maxValue) && !(
                        minValue == 1 && maxValue == 11)) {
            throw new IllegalArgumentException("Illegal values");
        }
    }

    public Card(int commonValue) {
        this(commonValue, commonValue);
    }

    public boolean isAce() {
        return minValue != maxValue;
    }
}
