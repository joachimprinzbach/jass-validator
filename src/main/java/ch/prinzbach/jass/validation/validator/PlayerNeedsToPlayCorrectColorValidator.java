package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if (jassTable.isFirstPlayedCard()) {
            return true;
        }
        final CardColor startCardColor = jassTable.getFirstCard().get().getColor();
        if (isCorrectColor(cardToValidate.getColor(), startCardColor)) {
            return true;
        }
        if (isTrump(cardToValidate, jassMode.getTrump())) {
            return true;
        }
        return player.hasNoCardWithColor(startCardColor, jassMode.getTrump());
    }

    private boolean isCorrectColor(CardColor color, CardColor startCardColor) {
        return startCardColor.equals(color);
    }

    private boolean isTrump(JassCard cardToValidate, CardColor trump) {
        return cardToValidate.getColor().equals(trump);
    }
}
