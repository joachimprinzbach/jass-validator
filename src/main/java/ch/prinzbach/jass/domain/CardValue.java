package ch.prinzbach.jass.domain;

/**
 * CardValue contains a unique order for nonTrump and trump modes.
 *
 * In Trump mode, JACK is the highest card, followed by nine.
 */
public enum CardValue {
    SIX(0),
    SEVEN(1),
    EIGHT(2),
    NINE(7),
    TEN(3),
    JACK(8),
    QUEEN(4),
    KING(5),
    ACE(6);

    private final int trumpOrder;

    CardValue(int trumpOrder) {
        this.trumpOrder = trumpOrder;
    }

    public int getTrumpOrder() {
        return trumpOrder;
    }
}
