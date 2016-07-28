package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.validation.strategy.JassValidationStrategy;
import ch.prinzbach.jass.validation.strategy.PlayerHasCardValidationStrategy;
import ch.prinzbach.jass.validation.strategy.PlayerNeedsToPlayCorrectColorValidationStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JassCardValidator {

    public boolean validateCard(List<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        final Set<JassValidationStrategy> validationStrategies = new HashSet<>();
        validationStrategies.add(new PlayerHasCardValidationStrategy());
        validationStrategies.add(new PlayerNeedsToPlayCorrectColorValidationStrategy());
        return validationStrategies.stream()
                .map(strategy -> strategy.validate(playedCards, cardToValidate, playersCards))
                .noneMatch(valid -> !valid);
    }

}
