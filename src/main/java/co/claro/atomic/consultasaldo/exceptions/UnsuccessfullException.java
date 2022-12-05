package co.claro.atomic.consultasaldo.exceptions;

/**
 * adapter-consulta-saldo
 * UnsuccessfullException.java
 * Nov 1, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public class UnsuccessfullException extends Exception {


	private static final long serialVersionUID = 4013296828610068532L;

	public UnsuccessfullException() {
		super();
	}

	public UnsuccessfullException(String message) {
		super(message);
	}
}
