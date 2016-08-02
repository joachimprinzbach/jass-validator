package ch.prinzbach.jass.domain;

public class JassCard {

    private final CardColor color;
    private final CardValue cardValue;

    public JassCard(CardValue cardValue, CardColor color) {
        this.cardValue = cardValue;
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    public CardValue getCardValue() {
        return cardValue;
    }
}
