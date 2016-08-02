package ch.prinzbach.jass.domain;

public enum CardValue {
    SIX(0, 0),
    SEVEN(1, 1),
    EIGHT(2, 2),
    NINE(3, 7),
    TEN(4, 3),
    JACK(5, 8),
    QUEEN(6, 4),
    KING(7, 5),
    ACE(8, 6);

    private final int normalOrder;
    private final int trumpOrder;

    private CardValue(int normalOrder, int trumpOrder) {
        this.normalOrder = normalOrder;
        this.trumpOrder = trumpOrder;
    }

    public int getNormalOrder() {
        return normalOrder;
    }

    public int getTrumpOrder() {
        return trumpOrder;
    }
}
