package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.validation.JassCard;

import java.util.Set;

public class PlayerHasCardValidator implements JassValidator {

    @Override
    public boolean validate(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        return hasPlayerCardInHisHand(playersCards, cardToValidate);
    }

    private boolean hasPlayerCardInHisHand(Set<JassCard> playersCards, JassCard card) {
        return playersCards.contains(card);
    }
}
