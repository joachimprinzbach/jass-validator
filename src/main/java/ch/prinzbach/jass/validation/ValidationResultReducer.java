package ch.prinzbach.jass.validation;

import ch.prinzbach.jass.domain.ValidationResult;
import org.apache.commons.lang3.StringUtils;

import java.util.function.BinaryOperator;

/**
 * Reduces {@link ValidationResult}s to a single Result.
 */
public class ValidationResultReducer implements BinaryOperator<ValidationResult> {

    @Override
    public ValidationResult apply(ValidationResult validationResult, ValidationResult validationResult2) {
        if (validationResult.isValid() && validationResult2.isValid()) {
            return ValidationResult.validationSuccess();
        }
        return ValidationResult.validationFailed(concatValidationMessage(validationResult, validationResult2));
    }

    private String concatValidationMessage(ValidationResult validationResult, ValidationResult validationResult2) {
        final String firstValidationError = validationResult.getErrMsg();
        final String secondValidationError = validationResult2.getErrMsg();
        if (StringUtils.isBlank(firstValidationError)) {
            return secondValidationError;
        }
        if (StringUtils.isBlank(secondValidationError)) {
            return firstValidationError;
        }
        return firstValidationError + "\n" + secondValidationError;
    }
}
