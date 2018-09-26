package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.*;
import ch.prinzbach.jass.validation.validator.JassCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerHasCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerNeedsToPlayCorrectColorValidator;
import ch.prinzbach.jass.validation.validator.UndertrumpValidator;

import java.util.HashSet;
import java.util.Set;

/**
 * Validates {@link JassCard}s and determines whether player is allowed to
 * play this card.
 */
public class JassCardValidationEngine {

    private final Set<JassCardValidator> validators = new HashSet<>();

    public JassCardValidationEngine() {
        validators.add(new PlayerHasCardValidator());
        validators.add(new PlayerNeedsToPlayCorrectColorValidator());
        validators.add(new UndertrumpValidator());
    }

    /**
     * Validates a single {@link JassCard}.
     * Determines whether player is allowed to play this card.
     *
     * @param jassTable {@link JassTable} the current tables content
     * @param jassMode {@link JassMode} the current game mode
     * @param cardToValidate {@link JassCard} the card to validate
     * @param player player whose card should be validated
     * @return @{@link ValidationResult}
     */
    public ValidationResult validateCard(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        return validators
                .stream()
                .map(validator -> validator.validate(jassTable, jassMode, cardToValidate, player))
                .reduce(new ValidationResultReducer())
                .get();
    }

}
