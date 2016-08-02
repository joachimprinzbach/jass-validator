package ch.prinzbach.jass.domain;

/**
 * A JassCard consists of a {@link CardValue} and its corresponding {@link CardColor}.
 */
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

    public boolean isTrumpJack(CardColor trump) {
        return this.getCardValue().equals(CardValue.JACK) && this.getColor().equals(trump);
    }


}
