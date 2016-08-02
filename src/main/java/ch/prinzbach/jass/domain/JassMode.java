package ch.prinzbach.jass.domain;

/**
 * JassMode can be one of the "Schieber" modes: OBEN_ABE, UNDEN_UFE or a TRUMP mode.
 */
public enum JassMode {

    OBEN_ABE(null),
    UNDEN_UFE(null),
    TRUMP_HEARTS(CardColor.HEARTS),
    TRUMP_CLUBS(CardColor.CLUBS),
    TRUMP_SPADES(CardColor.SPADES),
    TRUMP_DIAMONDS(CardColor.DIAMONDS);

    private CardColor trump;

    JassMode(CardColor trump) {
        this.trump = trump;
    }

    public CardColor getTrump() {
        return trump;
    }
}
