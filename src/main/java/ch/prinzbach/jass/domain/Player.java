package ch.prinzbach.jass.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * A JassPlayer that has {@link JassCard}s in his hands.
 */
public class Player {

    private final Set<JassCard> handCards;

    public Player() {
        handCards = new HashSet<>();
    }

    public void addCard(JassCard card) {
        handCards.add(card);
    }

    public boolean hasCard(JassCard card) {
        return handCards.contains(card);
    }

    public boolean hasNoCardWithColor(CardColor startCardColor, CardColor trump) {
        return handCards
                .stream()
                .filter(card -> !card.isTrumpJack(trump))
                .map(JassCard::getColor)
                .noneMatch(cardColor -> cardColor.equals(startCardColor));
    }

    public boolean hasOnlyTrump(CardColor trump) {
        return handCards
                .stream()
                .map(JassCard::getColor)
                .allMatch(cardColor -> cardColor.equals(trump));
    }
}
