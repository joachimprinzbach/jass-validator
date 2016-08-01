package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, CardColor trump, JassCard cardToValidate, Player player) {
        if (jassTable.isFirstPlayedCard()) {
            return true;
        }
        final CardColor startCardColor = jassTable.getFirstCard().get().getColor();
        if (isCorrectColor(cardToValidate.getColor(), startCardColor)) {
            return true;
        }
        if (isTrump(cardToValidate, trump)) {
            return true;
        }
        return player.hasNoCardWithColor(startCardColor, trump);
    }

    private boolean isCorrectColor(CardColor color, CardColor startCardColor) {
        return startCardColor.equals(color);
    }

    private boolean isTrump(JassCard cardToValidate, CardColor trump) {
        return cardToValidate.getColor().equals(trump);
    }
}
