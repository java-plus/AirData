package fr.diginamic.exception;

public class CreationFavoriDtoImpossibleException extends RuntimeException {
    public CreationFavoriDtoImpossibleException() {
    }

    public CreationFavoriDtoImpossibleException(String message) {
        super(message);
    }

    public CreationFavoriDtoImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreationFavoriDtoImpossibleException(Throwable cause) {
        super(cause);
    }

    public CreationFavoriDtoImpossibleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
