package ch.prinzbach.jass.domain;

/**
 * ValidationResult for jassCard validation.
 *
 * Validity indicates whether player is allowed to play the card.
 * ErrorMsg contains the reason why player must not play a card.
 */
public class ValidationResult {

    private boolean isValid;
    private String errMsg;

    private ValidationResult(boolean isValid, String errMsg) {
        this.isValid = isValid;
        this.errMsg = errMsg;
    }

    public static ValidationResult validationSuccess() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult validationFailed(String reason) {
        return new ValidationResult(false, reason);
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
