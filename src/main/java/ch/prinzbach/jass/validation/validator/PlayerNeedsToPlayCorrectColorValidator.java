package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;

import java.util.Set;

public class PlayerNeedsToPlayCorrectColorValidator implements JassCardValidator {

    @Override
    public boolean validate(Set<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        if (isPlayerStartPlayer(playedCards)) {
            return true;
        } else {
            final CardColor startCardColor = playedCards.iterator().next().getColor();
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

    private boolean isPlayerStartPlayer(Set<JassCard> playedCards) {
        return playedCards.isEmpty();
    }

}
