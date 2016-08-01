package ch.prinzbach.jass.domain;

import javax.smartcardio.Card;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

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

    public Optional<JassCard> getHighestTrumpPlayedSoFar(CardColor trump) {
        return playedCards.
                stream().filter(jassCard -> jassCard.getColor().equals(trump)).findFirst();
               // .max((o1, o2) -> o1.getNumber() - o2.getNumber());
    }
}
