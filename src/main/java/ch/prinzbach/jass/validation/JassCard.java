package ch.prinzbach.jass.validation;

public class JassCard {

    private int number;
    private CardColor color;

    public JassCard(int number, CardColor color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }
}
