package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public class UndertrumpValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player) {
        if(aCardHasAlreadyBeenPlayed(jassTable) || !jassMode.isTrumpMode()) {
            final CardColor trump = jassMode.getTrump();
            if(player.hasOnlyTrump(trump)) {
                return true;
            }
            if (trumpHasBeenPlayed(jassTable, trump)) {
                return true;
            }
            if(jassTable.getHighestTrumpOrderPlayedSoFar(trump).isPresent()) {
                return playerPlaysHigherTrump(jassTable, trump, cardToValidate);
            }
        }
        return true;
    }

    private boolean playerPlaysHigherTrump(JassTable jassTable, CardColor trump, JassCard cardToValidate) {
        return jassTable.getHighestTrumpOrderPlayedSoFar(trump).get() < cardToValidate.getCardValue().getTrumpOrder();
    }

    private boolean trumpHasBeenPlayed(JassTable jassTable, CardColor trump) {
        return jassTable.getFirstCard().get().getColor().equals(trump);
    }

    private boolean aCardHasAlreadyBeenPlayed(JassTable jassTable) {
        return jassTable.getFirstCard().isPresent();
    }
}
