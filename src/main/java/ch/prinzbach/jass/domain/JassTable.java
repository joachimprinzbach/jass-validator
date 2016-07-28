package ch.prinzbach.jass.domain;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class JassTable {

    private Set<JassCard> playedCards;

    public JassTable() {
        playedCards = new LinkedHashSet<>();
    }

    public void addCardToTable(JassCard card) {
        playedCards.add(card);
    }

    public boolean isFirstCard() {
        return playedCards.isEmpty();
    }

    public Optional<CardColor> getStartColor() {
        if(playedCards.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(playedCards.iterator().next().getColor());
        }
    }


}
