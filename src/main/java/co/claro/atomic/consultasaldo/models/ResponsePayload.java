package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * ResponsePayload.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class ResponsePayload implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 353652223581858774L;

	private PrimaryData primaryData; 
	private AdditionalData additionalData;

	public ResponsePayload() {
		super();
	}
	
	

	public PrimaryData getPrimaryData() {
		return primaryData;
	}



	public void setPrimaryData(PrimaryData primaryData) {
		this.primaryData = primaryData;
	}



	public AdditionalData getAdditionalData() {
		return additionalData;
	}



	public void setAdditionalData(AdditionalData additionalData) {
		this.additionalData = additionalData;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponsePayload [primaryData=");
		builder.append(primaryData);
		builder.append(", additionalData=");
		builder.append(additionalData);
		builder.append("]");
		return builder.toString();
	}
}
