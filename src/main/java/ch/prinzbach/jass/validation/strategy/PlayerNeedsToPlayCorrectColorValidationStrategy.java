package ch.prinzbach.jass.validation.strategy;

import ch.prinzbach.jass.validation.CardColor;
import ch.prinzbach.jass.validation.JassCard;

import java.util.List;
import java.util.Set;

public class PlayerNeedsToPlayCorrectColorValidationStrategy implements JassValidationStrategy {

    @Override
    public boolean validate(List<JassCard> playedCards, JassCard cardToValidate, Set<JassCard> playersCards) {
        if (isPlayerStartPlayer(playedCards)) {
            return true;
        } else {
            final CardColor startCardColor = playedCards.get(0).getColor();
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

    private boolean isPlayerStartPlayer(List<JassCard> playedCards) {
        return playedCards.isEmpty();
    }

}