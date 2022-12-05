package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * EhfObject.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class EhfObject implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5835693757465794878L;
	private String key;
    private EhfRecord ehfRecord;
    
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public EhfRecord getEhfRecord() {
		return ehfRecord;
	}
	public void setEhfRecord(EhfRecord ehfRecord) {
		this.ehfRecord = ehfRecord;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EhfObject [key=");
		builder.append(key);
		builder.append(", ehfRecord=");
		builder.append(ehfRecord);
		builder.append("]");
		return builder.toString();
	}
}
