package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class PlayerHasCardValidator implements JassCardValidator {

    @Override
    public ValidationResult validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if (player.hasCard(cardToValidate)) {
            return ValidationResult.validationSuccess();
        }
        return ValidationResult.validationFailed("Player does not have the card in his hands.");
    }
}
