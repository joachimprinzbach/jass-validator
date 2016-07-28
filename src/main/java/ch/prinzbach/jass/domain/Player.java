package ch.prinzbach.jass.domain;

import java.util.HashSet;
import java.util.Set;

public class Player {

    private Set<JassCard> handCards;

    public Player() {
        handCards = new HashSet<>();
    }

    public void addCard(JassCard card) {
        handCards.add(card);
    }

    public boolean hasCard(JassCard card) {
        return handCards.contains(card);
    }

    public boolean hasNoCardWithColor(CardColor startCardColor) {
        return handCards.stream()
                .map(JassCard::getColor)
                .noneMatch(cardColor -> cardColor.equals(startCardColor));
    }
}
