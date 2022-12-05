package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;


/**
 * atomic-consulta-saldo
 * Item.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class Item implements Serializable {

	private static final long serialVersionUID = 7273566685172729424L;
	
	public String ehfRef;
	public String ehfDesc;
 

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public String getEhfRef() {
		return ehfRef;
	}



	public void setEhfRef(String ehfRef) {
		this.ehfRef = ehfRef;
	}



	public String getEhfDesc() {
		return ehfDesc;
	}



	public void setEhfDesc(String ehfDesc) {
		this.ehfDesc = ehfDesc;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [ehfRef=");
		builder.append(ehfRef);
		builder.append(", ehfDesc=");
		builder.append(ehfDesc);
		builder.append("]");
		return builder.toString();
	}

	public Item(String ehfRef, String ehfDesc) {
		super();
		this.ehfRef = ehfRef;
		this.ehfDesc = ehfDesc;
	}
}
