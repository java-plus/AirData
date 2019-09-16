package fr.diginamic.exception;

/**
 * exception levée lorsque le cookie de connexion n’est pas trouvé
 */
public class CookieIntrouvableException extends RuntimeException {
    public CookieIntrouvableException() {
    }

    public CookieIntrouvableException(String message) {
        super(message);
    }

    public CookieIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }

    public CookieIntrouvableException(Throwable cause) {
        super(cause);
    }

    public CookieIntrouvableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
