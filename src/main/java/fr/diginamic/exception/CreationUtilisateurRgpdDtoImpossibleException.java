package fr.diginamic.exception;

/**
 * Exception levée lorsque la creation d’un dto des données rgpd d’un utilisateur est impossible à créer
 */
public class CreationUtilisateurRgpdDtoImpossibleException extends RuntimeException {
    public CreationUtilisateurRgpdDtoImpossibleException() {
    }

    public CreationUtilisateurRgpdDtoImpossibleException(String message) {
        super(message);
    }

    public CreationUtilisateurRgpdDtoImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreationUtilisateurRgpdDtoImpossibleException(Throwable cause) {
        super(cause);
    }

    public CreationUtilisateurRgpdDtoImpossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
