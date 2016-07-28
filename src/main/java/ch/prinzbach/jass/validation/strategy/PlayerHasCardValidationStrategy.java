package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.validation.JassCard;

import java.util.List;
import java.util.Set;

public class PlayerHasCardValidationStrategy implements  JassValidationStrategy {

    @Override
    public boolean validate(List<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        return hasPlayerCardInHisHand(playersCards, cardToValidate);
    }

    private boolean hasPlayerCardInHisHand(Set<JassCard> playersCards, JassCard card) {
        return playersCards.contains(card);
    }
}
