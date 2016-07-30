package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;

public class PlayerHasCardValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, CardColor trump, JassCard cardToValidate, Player player) {
        return player.hasCard(cardToValidate);
    }
}
