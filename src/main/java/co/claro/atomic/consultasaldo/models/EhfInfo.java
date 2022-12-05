package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;
import java.util.List;

/**
 * Error Detail Model (for Error Handling Framework)
 * atomic-consulta-saldo
 * EhfInfo.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class EhfInfo implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = -8647643527147121819L;
	private List<Item> item;
	
	

	public List<Item> getItem() {
		return item;
	}



	public void setItem(List<Item> item) {
		this.item = item;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EhfInfo [item=");
		builder.append(item);
		builder.append("]");
		return builder.toString();
	}
}