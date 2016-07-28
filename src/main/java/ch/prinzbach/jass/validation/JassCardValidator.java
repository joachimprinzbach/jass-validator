package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.validation.strategy.JassValidationStrategy;
import ch.prinzbach.jass.validation.strategy.PlayerHasCardValidationStrategy;
import ch.prinzbach.jass.validation.strategy.PlayerNeedsToPlayCorrectColorValidationStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JassCardValidator {

    private final Set<JassValidationStrategy> validationStrategies = new HashSet<>();

    public JassCardValidator() {
        validationStrategies.add(new PlayerHasCardValidationStrategy());
        validationStrategies.add(new PlayerNeedsToPlayCorrectColorValidationStrategy());
    }

    public boolean validateCard(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        return validationStrategies.stream()
                .map(strategy -> strategy.validate(playedCards, cardToValidate, playersCards))
                .allMatch(valid -> valid);
    }

}
