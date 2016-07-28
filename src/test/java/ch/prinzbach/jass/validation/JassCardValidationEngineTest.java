package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidationEngineTest {

    private JassCardValidationEngine jassCardValidationEngine;
    private JassTable jassTable;
    private Set<JassCard> playersCards;

    @Before
    public void setUp() {
        jassCardValidationEngine = new JassCardValidationEngine();
        jassTable = new JassTable();
        playersCards = new HashSet<>();
    }

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayedSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        jassTable.addCardToTable(cardToValidate);
        jassTable.addCardToTable(new JassCard(10, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, cardToValidate, playersCards);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardsButNotSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardAndPlayedCorrectColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        playersCards.add(cardToValidate);
        jassTable.addCardToTable(new JassCard(7, CardColor.HEARTS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, cardToValidate, playersCards);

        assertTrue(isCardValid);
    }

}
