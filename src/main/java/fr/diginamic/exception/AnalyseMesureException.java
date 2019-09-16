/**
 * 
 */
package fr.diginamic.exception;

/**
 * Exception levée lorsque les parametres de recherche d’une analyse sont incorrect
 * @author Eloi
 *
 */
public class AnalyseMesureException extends RuntimeException {

	/**
	 * Constructor
	 * 
	 */
	public AnalyseMesureException() {
		super();

	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public AnalyseMesureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public AnalyseMesureException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public AnalyseMesureException(String message) {
		super(message);

	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public AnalyseMesureException(Throwable cause) {
		super(cause);

	}

}
