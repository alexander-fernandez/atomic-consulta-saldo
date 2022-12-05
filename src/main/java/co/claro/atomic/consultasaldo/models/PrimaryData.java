package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;


/**
 * atomic-consulta-saldo
 * PrimaryData.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class PrimaryData implements Serializable {

    /** Serial version UID */
    private static final long serialVersionUID = -8478984558316046120L;

    private String businessKey;
    private String businessKeyType;

    public PrimaryData() {}
    
    

    public String getBusinessKey() {
		return businessKey;
	}



	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}



	public String getBusinessKeyType() {
		return businessKeyType;
	}



	public void setBusinessKeyType(String businessKeyType) {
		this.businessKeyType = businessKeyType;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrimaryData [businessKey=");
		builder.append(businessKey);
		builder.append(", businessKeyType=");
		builder.append(businessKeyType);
		builder.append("]");
		return builder.toString();
	}
}
