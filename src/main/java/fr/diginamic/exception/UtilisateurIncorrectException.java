package fr.diginamic.exception;

/**
 * Exception levée lorsque les informations d’un utilisateur sont incorrects
 */
public class UtilisateurIncorrectException extends RuntimeException {
    public UtilisateurIncorrectException() {
    }

    public UtilisateurIncorrectException(String message) {
        super(message);
    }

    public UtilisateurIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurIncorrectException(Throwable cause) {
        super(cause);
    }

    public UtilisateurIncorrectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
