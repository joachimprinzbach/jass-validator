package ch.prinzbach.jass.validation;

import java.util.List;
import java.util.Set;

public class JassCardValidator {

    public boolean validateCard(List<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        if (!hasPlayerCardInHisHand(playersCards, cardToValidate)) {
            return false;
        } else {
            if (isPlayerStartPlayer(playedCards)) {
                return true;
            } else {
                final CardColor startCardColor = playedCards.get(0).getColor();
                if (startCardColor.equals(cardToValidate.getColor())) {
                    return true;
                } else {
                    return playersCards.stream()
                            .map(card -> card.getColor())
                            .noneMatch(cardColor -> cardColor.equals(startCardColor));
                }
            }
        }
    }

    private boolean isPlayerStartPlayer(List<JassCard> playedCards) {
        return playedCards.isEmpty();
    }

    private boolean hasPlayerCardInHisHand(Set<JassCard> playersCards, JassCard card) {
        return playersCards.contains(card);
    }

}
