package ch.prinzbach.jass.validation;

import java.util.List;

public class JassCardValidator {

    public boolean validateCard(List<JassCard> playedCards, JassCard cardToValidate, List<JassCard> playersCards) {
        if (!hasPlayerCardInHisHand(playersCards, cardToValidate)) {
            return false;
        } else {
            if (isPlayerStartPlayer(playedCards)) {
                return true;
            } else {
                final JassCard startCard = playedCards.get(0);
                if (startCard.getColor().equals(cardToValidate.getColor())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private boolean isPlayerStartPlayer(List<JassCard> playedCards) {
        return playedCards.isEmpty();
    }

    private boolean hasPlayerCardInHisHand(List<JassCard> playersCards, JassCard card) {
        return playersCards.contains(card);
    }

}
