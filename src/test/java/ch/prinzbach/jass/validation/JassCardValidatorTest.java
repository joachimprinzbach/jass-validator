package ch.prinzbach.jass.validation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator = new JassCardValidator();

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();
        playersCards.add(cardToValidate);
        final List<JassCard> playedCards = Collections.emptyList();

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();
        final List<JassCard> playedCards = Collections.emptyList();

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayeSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();
        playersCards.add(cardToValidate);
        final List<JassCard> playedCards = new ArrayList<>();
        playedCards.add(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }
}
