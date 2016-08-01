package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JassCardValidationEngineTest {

    private JassCardValidationEngine jassCardValidationEngine;
    private JassTable jassTable;
    private Player player;

    @Before
    public void setUp() {
        jassCardValidationEngine = new JassCardValidationEngine();
        jassTable = new JassTable();
        player = new Player();
    }

    @Test
    public void validateCard_playerHasCardInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        player.addCard(cardToValidate);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.SPADES, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayedSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        jassTable.addCardToTable(cardToValidate);
        jassTable.addCardToTable(new JassCard(10, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardsButNotSameColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardAndPlayedCorrectColor() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(7, CardColor.HEARTS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsFirstPlayer() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        player.addCard(cardToValidate);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_trumpCardIsAllowed() {
        final JassCard cardToValidate = new JassCard(6, CardColor.DIAMONDS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(8, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(10, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_nonTrumpCardShouldNotBeAllowed() {
        final JassCard cardToValidate = new JassCard(6, CardColor.HEARTS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(8, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(10, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(7, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_trumpJackDoesNotNeedToBePlayed() {
        final JassCard cardToValidate = new JassCard(10, CardColor.SPADES);
        final JassCard trumpJack = new JassCard(11, CardColor.HEARTS);
        player.addCard(cardToValidate);
        player.addCard(trumpJack);
        jassTable.addCardToTable(new JassCard(10, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(7, CardColor.HEARTS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, CardColor.HEARTS, cardToValidate, player);

        assertTrue(isCardValid);
    }

}
