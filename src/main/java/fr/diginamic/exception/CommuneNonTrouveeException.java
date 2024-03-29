package fr.diginamic.exception;

/**
 * Exception levée lorsqu’une commune n’est pas trouvée en base de données
 */
public class CommuneNonTrouveeException extends RuntimeException {

    public CommuneNonTrouveeException() {
    }

    public CommuneNonTrouveeException(String message) {
        super(message);
    }

    public CommuneNonTrouveeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommuneNonTrouveeException(Throwable cause) {
        super(cause);
    }

    public CommuneNonTrouveeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
