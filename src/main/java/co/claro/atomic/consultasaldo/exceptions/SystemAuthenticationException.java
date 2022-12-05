package co.claro.atomic.consultasaldo.exceptions;


/**
 * atomic-consulta-saldo
 * SystemAuthenticationException.java
 * Nov 10, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public class SystemAuthenticationException extends Exception {
	

	private static final long serialVersionUID = 2093350576247606722L;

	public SystemAuthenticationException() {
		super();
	}

	public SystemAuthenticationException(String message) {
		super(message);
	}
}
