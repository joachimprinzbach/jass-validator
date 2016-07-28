package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.validation.strategy.JassValidator;
import ch.prinzbach.jass.validation.strategy.PlayerHasCardValidator;
import ch.prinzbach.jass.validation.strategy.PlayerNeedsToPlayCorrectColorValidator;

import java.util.HashSet;
import java.util.Set;

public class JassCardValidationEngine {

    private final Set<JassValidator> validationStrategies = new HashSet<>();

    public JassCardValidationEngine() {
        validationStrategies.add(new PlayerHasCardValidator());
        validationStrategies.add(new PlayerNeedsToPlayCorrectColorValidator());
    }

    public boolean validateCard(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        return validationStrategies.stream()
                .map(strategy -> strategy.validate(playedCards, cardToValidate, playersCards))
                .allMatch(valid -> valid);
    }

}
