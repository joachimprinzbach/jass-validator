package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class PlayerHasCardValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        return player.hasCard(cardToValidate);
    }
}
