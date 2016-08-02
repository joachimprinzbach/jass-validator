package ch.prinzbach.jass.domain;

public enum JassMode {

    OBEN_ABE(null),
    UNDEN_UFE(null),
    TRUMP_HEARTS(CardColor.HEARTS),
    TRUMP_CLUBS(CardColor.CLUBS),
    TRUMP_SPADES(CardColor.SPADES),
    TRUMP_DIAMONDS(CardColor.DIAMONDS);

    private CardColor trump;

    private JassMode(CardColor trump) {
        this.trump = trump;
    }

    public CardColor getTrump() {
        return trump;
    }

    public boolean isTrumpMode() {
        return this.equals(TRUMP_HEARTS) || this.equals(TRUMP_CLUBS) || this.equals(TRUMP_SPADES) || this.equals(TRUMP_DIAMONDS);
    }
}
