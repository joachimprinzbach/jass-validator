package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class PlayerHasCardValidator implements JassCardValidator {

    public static final String PLAYER_HAS_CARD_ERR_MSG = "Playing a lower trump card is not allowed";

    @Override
    public ValidationResult validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if (player.hasCard(cardToValidate)) {
            return ValidationResult.validationSuccess();
        }
        return ValidationResult.validationFailed(PLAYER_HAS_CARD_ERR_MSG);
    }
}
