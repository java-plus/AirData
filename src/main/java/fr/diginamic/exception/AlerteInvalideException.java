package fr.diginamic.exception;

/**
 * Alerte levée lorsque une Alerte invalide essaye d’etre créer
 */
public class AlerteInvalideException extends RuntimeException {
    public AlerteInvalideException() {
    }

    public AlerteInvalideException(String message) {
        super(message);
    }

    public AlerteInvalideException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlerteInvalideException(Throwable cause) {
        super(cause);
    }

    public AlerteInvalideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
