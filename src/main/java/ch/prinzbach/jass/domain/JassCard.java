package ch.prinzbach.jass.domain;

public class JassCard {

    private final int number;
    private final CardColor color;

    public JassCard(int number, CardColor color) {
        this.number = number;
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }
}
