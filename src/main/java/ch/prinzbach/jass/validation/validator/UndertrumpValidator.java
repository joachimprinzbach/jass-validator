package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

/**
 * Validator to check whether the card would be a lower trump than the ones already played.
 */
public class UndertrumpValidator implements JassCardValidator {

    public static final String UNDERTRUMP_ERR_MSG = "Playing a lower trump card is not allowed";

    @Override
    public ValidationResult validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if(aCardHasAlreadyBeenPlayed(jassTable)) {
            final CardColor trump = jassMode.getTrump();
            if(player.hasOnlyTrump(trump)) {
                return ValidationResult.validationSuccess();
            }
            if (trumpHasBeenPlayedAsFirstCard(jassTable, trump)) {
                return ValidationResult.validationSuccess();
            }
            if(jassTable.getHighestTrumpOrderPlayedSoFar(trump).isPresent()) {
                if(playerPlaysHigherTrump(jassTable, trump, cardToValidate)) {
                    return ValidationResult.validationSuccess();
                }
                return ValidationResult.validationFailed(UNDERTRUMP_ERR_MSG);
            }
        }
        return ValidationResult.validationSuccess();
    }

    private boolean playerPlaysHigherTrump(JassTable jassTable, CardColor trump, JassCard cardToValidate) {
        return jassTable.getHighestTrumpOrderPlayedSoFar(trump).getAsInt() < cardToValidate.getCardValue().getTrumpOrder();
    }

    private boolean trumpHasBeenPlayedAsFirstCard(JassTable jassTable, CardColor trump) {
        return jassTable.getFirstCard().get().getColor().equals(trump);
    }

    private boolean aCardHasAlreadyBeenPlayed(JassTable jassTable) {
        return jassTable.getFirstCard().isPresent();
    }
}
