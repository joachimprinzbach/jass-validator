package ch.prinzbach.jass.validation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator = new JassCardValidator();

    @Test
    public void validateCard_PlayerHasCardInHand() {
        int cardNumber = 6;
        String cardColor = "HEARTS";

        boolean isCardValid = jassCardValidator.validateCard(cardNumber, cardColor);

        assertTrue(isCardValid);
    }
}