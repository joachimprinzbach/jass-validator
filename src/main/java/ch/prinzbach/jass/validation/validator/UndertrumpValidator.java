package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;

public class UndertrumpValidator implements JassCardValidator {

    @Override
    public boolean validate(JassTable jassTable, CardColor trump, JassCard cardToValidate, Player player) {
        if(aCardHasAlreadyBeenPlayed(jassTable)) {
            if(player.hasOnlyTrump(trump)) {
                return true;
            }
            if (trumpHasBeenPlayed(jassTable, trump)) {
                return true;
            }
            if(jassTable.getHighestTrumpPlayedSoFar(trump).isPresent()) {
                return playerPlaysHigherTrump(jassTable, trump, cardToValidate);
            }
        }
        return true;
    }

    private boolean playerPlaysHigherTrump(JassTable jassTable, CardColor trump, JassCard cardToValidate) {
        return jassTable.getHighestTrumpPlayedSoFar(trump).get().getCardValue().getTrumpOrder() < cardToValidate.getCardValue().getTrumpOrder();
    }

    private boolean trumpHasBeenPlayed(JassTable jassTable, CardColor trump) {
        return jassTable.getFirstCard().get().getColor().equals(trump);
    }

    private boolean aCardHasAlreadyBeenPlayed(JassTable jassTable) {
        return jassTable.getFirstCard().isPresent();
    }
}
