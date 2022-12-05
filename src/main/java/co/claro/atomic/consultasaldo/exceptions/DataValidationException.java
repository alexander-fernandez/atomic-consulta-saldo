package co.claro.atomic.consultasaldo.exceptions;


/**
 * atomic-consulta-saldo
 * DataValidationException.java
 * Nov 10, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public class DataValidationException extends Exception {
	
	/** Serial version */
	private static final long serialVersionUID = 5984136394820807294L;

	public DataValidationException() {
		super();
	}

	public DataValidationException(String message) {
		super(message);
	}
	
}
