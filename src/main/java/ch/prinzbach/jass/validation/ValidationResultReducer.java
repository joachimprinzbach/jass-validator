package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.ValidationResult;

import java.util.function.BinaryOperator;

public class ValidationResultReducer implements BinaryOperator<ValidationResult> {

    @Override
    public ValidationResult apply(ValidationResult validationResult, ValidationResult validationResult2) {
        if(validationResult.isValid() && validationResult2.isValid()) {
            return ValidationResult.validationSuccess();
        }
        return ValidationResult.validationFailed(validationResult.getErrMsg() + "\n" + validationResult2.getErrMsg());
    }
}
