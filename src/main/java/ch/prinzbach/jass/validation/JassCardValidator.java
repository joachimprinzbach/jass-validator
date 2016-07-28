package ch.prinzbach.jass.validation;

import java.util.List;

public class JassCardValidator {

    public boolean validateCard(List<JassCard> playedCards, JassCard card, List<JassCard> playersCards) {
        final boolean playerHasCardInHand = playersCards.contains(card);
        if (!playerHasCardInHand) {
            return false;
        } else {
            final boolean isStartPlayer = playedCards.isEmpty();
            if (isStartPlayer) {
                return true;
            } else {
                final JassCard startCard = playedCards.get(0);
                if (startCard.getColor().equals(card.getColor())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
