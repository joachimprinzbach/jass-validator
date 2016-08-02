package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.*;
import ch.prinzbach.jass.validation.validator.JassCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerHasCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerNeedsToPlayCorrectColorValidator;
import ch.prinzbach.jass.validation.validator.UndertrumpValidator;

import java.util.HashSet;
import java.util.Set;

public class JassCardValidationEngine {

    private final Set<JassCardValidator> validators = new HashSet<>();

    public JassCardValidationEngine() {
        validators.add(new PlayerHasCardValidator());
        validators.add(new PlayerNeedsToPlayCorrectColorValidator());
        validators.add(new UndertrumpValidator());
    }

    public ValidationResult validateCard(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        return validators.stream()
                .map(validator -> validator.validate(jassTable, jassMode, cardToValidate, player))
                .reduce(new ValidationResultReducer())
                .get();
    }

}
