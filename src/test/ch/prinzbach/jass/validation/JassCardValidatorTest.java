package ch.prinzbach.jass.validation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator = new JassCardValidator();

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<JassCard>();
        playersCards.add(cardToValidate);

        boolean isCardValid = jassCardValidator.validateCard(cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<JassCard>();

        boolean isCardValid = jassCardValidator.validateCard(cardToValidate, playersCards);

        assertFalse(isCardValid);
    }
}