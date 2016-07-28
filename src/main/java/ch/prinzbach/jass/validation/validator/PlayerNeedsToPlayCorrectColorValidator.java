package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;

import java.util.Set;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassCard cardToValidate, Set<JassCard> playersCards) {
        if (jassTable.isFirstPlayedCard()) {
            return true;
        } else {
            final CardColor startCardColor = jassTable.getFirstCard().get().getColor();
            if (startCardColor.equals(cardToValidate.getColor())) {
                return true;
            } else {
                return playerHasNoCardWithMatchingColor(playersCards, startCardColor);
            }
        }
    }

    private boolean playerHasNoCardWithMatchingColor(Set<JassCard> playersCards, CardColor startCardColor) {
        return playersCards.stream()
                .map(JassCard::getColor)
                .noneMatch(cardColor -> cardColor.equals(startCardColor));
    }
}
