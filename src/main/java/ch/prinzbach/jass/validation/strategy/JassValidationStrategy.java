package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.validation.JassCard;

import java.util.Set;

public interface JassValidationStrategy {

    boolean validate(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards);
}
