package ch.prinzbach.jass.validation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidatorTest {

    private JassCardValidator jassCardValidator;
    private List<JassCard> playedCards;

    @Before
    public void setUp() {
        jassCardValidator = new JassCardValidator();
        playedCards = new ArrayList<>();
    }

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();
        playersCards.add(cardToValidate);

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayeSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        final List<JassCard> playersCards = new ArrayList<>();
        playersCards.add(cardToValidate);
        playedCards.add(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidator.validateCard(playedCards, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }
}
