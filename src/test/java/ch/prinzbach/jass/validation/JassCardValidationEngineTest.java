package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.*;
import org.junit.Before;
import org.junit.Test;

import static ch.prinzbach.jass.domain.CardColor.*;
import static ch.prinzbach.jass.domain.CardValue.*;
import static ch.prinzbach.jass.domain.JassMode.*;
import static ch.prinzbach.jass.validation.validator.PlayerHasCardValidator.*;
import static ch.prinzbach.jass.validation.validator.PlayerNeedsToPlayCorrectColorValidator.*;
import static ch.prinzbach.jass.validation.validator.UndertrumpValidator.*;
import static org.junit.Assert.assertEquals;
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
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_CLUBS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerHasCardNotInHand() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_HAS_CARD_ERR_MSG);
    }

    @Test
    public void validateCard_playerShouldHavePlayedSameColor() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(ACE, CLUBS));
        jassTable.addCardToTable(new JassCard(TEN, CLUBS));
        jassTable.addCardToTable(new JassCard(SEVEN, CLUBS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG);
    }

    @Test
    public void validateCard_playerHasCardsButNotSameColor() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(SEVEN, CLUBS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerHasCardAndPlayedCorrectColor() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);
        jassTable.addCardToTable(new JassCard(SEVEN, HEARTS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerIsFirstPlayer() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_trumpCardIsAllowed() {
        final JassCard cardToValidate = new JassCard(SIX, DIAMONDS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(EIGHT, CLUBS));
        jassTable.addCardToTable(new JassCard(TEN, CLUBS));
        jassTable.addCardToTable(new JassCard(SEVEN, CLUBS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_nonTrumpCardShouldNotBeAllowed() {
        final JassCard cardToValidate = new JassCard(SIX, HEARTS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(EIGHT, CLUBS));
        jassTable.addCardToTable(new JassCard(TEN, CLUBS));
        jassTable.addCardToTable(new JassCard(SEVEN, CLUBS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_DIAMONDS, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG);
    }

    @Test
    public void validateCard_trumpJackDoesNotNeedToBePlayed() {
        final JassCard cardToValidate = new JassCard(TEN, SPADES);
        final JassCard trumpJack = new JassCard(JACK, HEARTS);
        player.addCard(cardToValidate);
        player.addCard(trumpJack);
        jassTable.addCardToTable(new JassCard(TEN, HEARTS));
        jassTable.addCardToTable(new JassCard(SEVEN, HEARTS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_HEARTS, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_trumpNineNeedsToBePlayed() {
        final JassCard cardToValidate = new JassCard(TEN, SPADES);
        final JassCard trumpJack = new JassCard(NINE, HEARTS);
        player.addCard(cardToValidate);
        player.addCard(trumpJack);
        jassTable.addCardToTable(new JassCard(TEN, HEARTS));
        jassTable.addCardToTable(new JassCard(SEVEN, HEARTS));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_HEARTS, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG);
    }

    @Test
    public void validateCard_playerIsNotAllowedToPlayLowerTrumpCardWhenNonTrumpWasStarted() {
        final JassCard cardToValidate = new JassCard(TEN, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(EIGHT, DIAMONDS));
        jassTable.addCardToTable(new JassCard(TEN, HEARTS));
        jassTable.addCardToTable(new JassCard(QUEEN, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationError(validationResult, UNDERTRUMP_ERR_MSG);
    }

    @Test
    public void validateCard_playerIsAllowedToPlayHigherTrumpCard() {
        final JassCard cardToValidate = new JassCard(TEN, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(EIGHT, DIAMONDS));
        jassTable.addCardToTable(new JassCard(TEN, HEARTS));
        jassTable.addCardToTable(new JassCard(EIGHT, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerIsAllowedToPlayHigherTrumpCardIfIsNell() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(EIGHT, DIAMONDS));
        jassTable.addCardToTable(new JassCard(TEN, HEARTS));
        jassTable.addCardToTable(new JassCard(TEN, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerIsAllowedToUndertrumpWhenHasOnlyTrump() {
        final JassCard cardToValidate = new JassCard(SIX, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(JACK, SPADES));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(TEN, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerIsNotAllowedToUndertrumpJackWithNell() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(ACE, DIAMONDS));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationError(validationResult, UNDERTRUMP_ERR_MSG);
    }

    @Test
    public void validateCard_thereIsNoUndertrumpForObenAbe() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(ACE, DIAMONDS));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, OBEN_ABE, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_thereIsNoUndertrumpForUndeUfe() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(ACE, DIAMONDS));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, UNDEN_UFE, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerDoesNotHaveCardAndIsUndertrump() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(new JassCard(ACE, DIAMONDS));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_SPADES, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_HAS_CARD_ERR_MSG + "\n" + UNDERTRUMP_ERR_MSG);
    }

    @Test
    public void validateCard_noUndertrumpForNonTrumpModes() {
        final JassCard cardToValidate = new JassCard(NINE, SPADES);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(ACE, CLUBS));
        jassTable.addCardToTable(new JassCard(EIGHT, HEARTS));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, OBEN_ABE, cardToValidate, player);

        assertValidationSuccess(validationResult);
    }

    @Test
    public void validateCard_playerNeedsToPlayJackCauseItIsNotTrump() {
        final JassCard cardToValidate = new JassCard(NINE, DIAMONDS);
        player.addCard(cardToValidate);
        player.addCard(new JassCard(JACK, SPADES));
        jassTable.addCardToTable(new JassCard(EIGHT, SPADES));
        jassTable.addCardToTable(new JassCard(KING, SPADES));
        jassTable.addCardToTable(new JassCard(JACK, SPADES));

        final ValidationResult validationResult = jassCardValidationEngine.validateCard(jassTable, TRUMP_HEARTS, cardToValidate, player);

        assertValidationError(validationResult, PLAYER_NEEDS_TO_PLAY_CORRECT_COLOR_ERR_MSG);
    }

    private void assertValidationError(ValidationResult validationResult, String expectedErrMsg) {
        assertFalse(validationResult.isValid());
        assertEquals(expectedErrMsg, validationResult.getErrMsg());
    }

    private void assertValidationSuccess(ValidationResult validationResult) {
        assertTrue(validationResult.isValid());
    }


}
