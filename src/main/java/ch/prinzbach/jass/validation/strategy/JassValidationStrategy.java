package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.validation.JassCard;

import java.util.List;
import java.util.Set;

public interface JassValidationStrategy {

    public boolean validate(List<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards);
}
