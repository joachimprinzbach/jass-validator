package ch.prinzbach.jass.validation.validator;

import ch.prinzbach.jass.domain.*;

public interface JassCardValidator {

    ValidationResult validate(JassTable jassTable, JassMode jassMode, JassCard cardToValidate, Player player);
}
