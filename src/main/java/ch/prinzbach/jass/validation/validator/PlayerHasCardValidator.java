package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;

import java.util.Set;

public class PlayerHasCardValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassCard cardToValidate, Set<JassCard> playersCards) {
        return hasPlayerCardInHisHand(playersCards, cardToValidate);
    }

    private boolean hasPlayerCardInHisHand(Set<JassCard> playersCards, JassCard card) {
        return playersCards.contains(card);
    }
}
