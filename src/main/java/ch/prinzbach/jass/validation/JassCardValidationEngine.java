package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.CardColor;
import ch.prinzbach.jass.domain.JassCard;
import ch.prinzbach.jass.domain.JassTable;
import ch.prinzbach.jass.domain.Player;
import ch.prinzbach.jass.validation.validator.JassCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerHasCardValidator;
import ch.prinzbach.jass.validation.validator.PlayerNeedsToPlayCorrectColorValidator;

import java.util.HashSet;
import java.util.Set;

public class JassCardValidationEngine {

    private final Set<JassCardValidator> validators = new HashSet<>();

    public JassCardValidationEngine() {
        validators.add(new PlayerHasCardValidator());
        validators.add(new PlayerNeedsToPlayCorrectColorValidator());
    }

    public boolean validateCard(JassTable jassTable, CardColor trump, JassCard cardToValidate, Player player) {
        return validators.stream()
                .map(validator -> validator.validate(jassTable, trump, cardToValidate, player))
                .allMatch(valid -> valid);
    }

}
