package ch.prinzbach.jass.validation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator = new JassCardValidator();

    @Test
    public void validateCard_PlayerHasCardInHand() {
        final JassCard card = new JassCard(6, CardColor.HEARTS);

        boolean isCardValid = jassCardValidator.validateCard(card);

        assertTrue(isCardValid);
    }
}