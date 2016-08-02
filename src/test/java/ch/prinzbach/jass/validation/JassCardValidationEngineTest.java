package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.*;
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
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        player.addCard(cardToValidate);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerShouldHavePlayedSameColor() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        jassTable.addCardToTable(cardToValidate);
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardsButNotSameColor() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerHasCardAndPlayedCorrectColor() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.HEARTS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsFirstPlayer() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        player.addCard(cardToValidate);

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_trumpCardIsAllowed() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.DIAMONDS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.EIGHT, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_nonTrumpCardShouldNotBeAllowed() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.HEARTS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.EIGHT, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.CLUBS));
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.CLUBS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_DIAMONDS, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_trumpJackDoesNotNeedToBePlayed() {
        final JassCard cardToValidate = new JassCard(CardValue.TEN, CardColor.SPADES);
        final JassCard trumpJack = new JassCard(CardValue.JACK, CardColor.HEARTS);
        player.addCard(cardToValidate);
        player.addCard(trumpJack);
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.SEVEN, CardColor.HEARTS));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_HEARTS, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsNotAllowedToPlayerLowerTrumpCardWhenNonTrumpWasStarted() {
        final JassCard cardToValidate = new JassCard(CardValue.TEN, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.EIGHT, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.QUEEN, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_playerIsAllowedToPlayHigherTrumpCard() {
        final JassCard cardToValidate = new JassCard(CardValue.TEN, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.EIGHT, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.EIGHT, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsAllowedToPlayHigherTrumpCardIfIsNell() {
        final JassCard cardToValidate = new JassCard(CardValue.NINE, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.EIGHT, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsAllowedToUndertrumpWhenHasOnlyTrump() {
        final JassCard cardToValidate = new JassCard(CardValue.SIX, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.JACK, CardColor.SPADES));
        jassTable.addCardToTable(new JassCard(CardValue.EIGHT, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.TEN, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_playerIsNotAllowedToUndertrumpJackWithNell() {
        final JassCard cardToValidate = new JassCard(CardValue.NINE, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.ACE, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.EIGHT, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.KING, CardColor.SPADES));
        jassTable.addCardToTable(new JassCard(CardValue.JACK, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.TRUMP_SPADES, cardToValidate, player);

        assertFalse(isCardValid);
    }

    @Test
    public void validateCard_thereIsNoUndertrumpForObenAbe() {
        final JassCard cardToValidate = new JassCard(CardValue.NINE, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.ACE, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.EIGHT, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.KING, CardColor.SPADES));
        jassTable.addCardToTable(new JassCard(CardValue.JACK, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.OBEN_ABE, cardToValidate, player);

        assertTrue(isCardValid);
    }

    @Test
    public void validateCard_thereIsNoUndertrumpForUndeUfe() {
        final JassCard cardToValidate = new JassCard(CardValue.NINE, CardColor.SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(CardValue.ACE, CardColor.DIAMONDS));
        jassTable.addCardToTable(new JassCard(CardValue.EIGHT, CardColor.HEARTS));
        jassTable.addCardToTable(new JassCard(CardValue.KING, CardColor.SPADES));
        jassTable.addCardToTable(new JassCard(CardValue.JACK, CardColor.SPADES));

        boolean isCardValid = jassCardValidationEngine.validateCard(jassTable, JassMode.UNDEN_UFE, cardToValidate, player);

        assertTrue(isCardValid);
    }


}
