package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;

import javax.smartcardio.Card;
import java.util.Set;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassCard cardToValidate, Set<JassCard> playersCards) {
        if (jassTable.isFirstCard()) {
            return true;
        } else {
            if (jassTable.getStartColor().isPresent()) {
                final CardColor startCardColor = jassTable.getStartColor().get();
                if (startCardColor.equals(cardToValidate.getColor())) {
                    return true;
                } else {
                    return playerHasNoCardWithMatchingColor(playersCards, startCardColor);
                }
            } else {
                return true;
            }
        }
    }

    private boolean playerHasNoCardWithMatchingColor(Set<JassCard> playersCards, CardColor startCardColor) {
        return playersCards.stream()
                .map(JassCard::getColor)
                .noneMatch(cardColor -> cardColor.equals(startCardColor));
    }

    private boolean isPlayerStartPlayer(Set<JassCard> playedCards) {
        return playedCards.isEmpty();
    }

}
