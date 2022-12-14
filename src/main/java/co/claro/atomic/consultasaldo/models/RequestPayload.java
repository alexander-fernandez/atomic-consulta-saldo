package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * RequestPayload.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class RequestPayload implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7868310611900741033L;

    private PrimaryData primaryData;  
    private AdditionalData additionalData;
    
    public RequestPayload() {
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
		builder.append("RequestPayload [primaryData=");
		builder.append(primaryData);
		builder.append(", additionalData=");
		builder.append(additionalData);
		builder.append("]");
		return builder.toString();
	}
}
