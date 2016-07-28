package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.JassCard;

import java.util.Set;

public interface JassCardValidator {

    boolean validate(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards);
}
