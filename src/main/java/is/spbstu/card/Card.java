package is.spbstu.card;

public record Card(int minValue, int maxValue) {
    public Card(int commonValue){
        this(commonValue, commonValue);
    }

    public boolean isAce(){
        return minValue!=maxValue;
    }
}
