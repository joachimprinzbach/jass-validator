package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassCard cardToValidate, Player player) {
        if (jassTable.isFirstPlayedCard()) {
            return true;
        } else {
            final CardColor startCardColor = jassTable.getFirstCard().get().getColor();
            if (startCardColor.equals(cardToValidate.getColor())) {
                return true;
            } else {
                return player.hasNoCardWithMatchingColor(startCardColor);
            }
        }
    }
}
