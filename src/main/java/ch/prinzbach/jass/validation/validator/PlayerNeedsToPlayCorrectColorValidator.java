package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    public static final String PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG = "Player needs to play correct color.";

    @Override
    public ValidationResult validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if (jassTable.isFirstPlayedCard()) {
            return ValidationResult.validationSuccess();
        }
        final CardColor startCardColor = jassTable.getFirstCard().get().getColor();
        if (isCorrectColor(cardToValidate.getColor(), startCardColor)) {
            return ValidationResult.validationSuccess();
        }
        if (isTrump(cardToValidate, jassMode.getTrump())) {
            return ValidationResult.validationSuccess();
        }
        if(player.hasNoCardWithColor(startCardColor, jassMode.getTrump())) {
            return ValidationResult.validationSuccess();
        }
        return ValidationResult.validationFailed(PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG);
    }

    private boolean isCorrectColor(CardColor color, CardColor startCardColor) {
        return startCardColor.equals(color);
    }

    private boolean isTrump(JassCard cardToValidate, CardColor trump) {
        return cardToValidate.getColor().equals(trump);
    }
}
