package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.domain.JassCard;

import java.util.Set;

public interface JassValidator {

    boolean validate(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards);
}
