package ch.prinzbach.jass.validation;

import java.util.List;

public class JassCardValidator {

    public boolean validateCard(List<JassCard> playedCards, JassCard card, List<JassCard> playersCards) {
        final boolean playerHasCardInHand = playersCards.contains(card);
        if(!playerHasCardInHand) {
            return false;
        } else {
            return playedCards.isEmpty();
        }
    }
}
