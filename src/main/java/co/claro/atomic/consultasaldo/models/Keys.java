package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;
import java.util.List;


/**
 * atomic-consulta-saldo
 * Keys.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class Keys implements Serializable {


	private static final long serialVersionUID = 1724083858859234133L;
	
	private List<StringBuilder> keys;
	
	public Keys(List<StringBuilder> keys) {
		super();
		this.keys = keys;
	}
	
	public Keys() {
	}
	
	

	public List<StringBuilder> getKeys() {
		return keys;
	}

	public void setKeys(List<StringBuilder> keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keys [keys=");
		builder.append(keys);
		builder.append("]");
		return builder.toString();
	}

}
