package ch.prinzbach.jass.domain;

import java.util.LinkedHashSet;
import java.util.Optional;

/**
 * JassTable is the current jassRound that consists of a maximum of 4 cards.
 */
public class JassTable {

    private final LinkedHashSet<JassCard> playedCards;

    public JassTable() {
        playedCards = new LinkedHashSet<>();
    }

    public void addCardToTable(JassCard card) {
        playedCards.add(card);
    }

    public boolean isFirstPlayedCard() {
        return !getFirstCard().isPresent();
    }

    public Optional<JassCard> getFirstCard() {
        if(playedCards.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(playedCards.iterator().next());
        }
    }

    public Optional<Integer> getHighestTrumpOrderPlayedSoFar(CardColor trump) {
        return playedCards.
                stream().filter(jassCard -> jassCard.getColor().equals(trump))
                .map(JassCard::getCardValue)
                .map(CardValue::getTrumpOrder)
                .max((trumpOrder1, trumpOrder2) -> (trumpOrder1 - trumpOrder2));
    }
}
