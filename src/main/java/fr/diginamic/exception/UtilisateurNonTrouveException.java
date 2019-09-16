package fr.diginamic.exception;

/**
 * exception levée lorsqu’un utilisateur n’est pas trouvée en base de données
 */
public class UtilisateurNonTrouveException extends RuntimeException {

    public UtilisateurNonTrouveException() {
    }

    public UtilisateurNonTrouveException(String message) {
        super(message);
    }

    public UtilisateurNonTrouveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurNonTrouveException(Throwable cause) {
        super(cause);
    }

    public UtilisateurNonTrouveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
