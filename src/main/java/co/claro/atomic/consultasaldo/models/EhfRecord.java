package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * EhfRecord.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class EhfRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1861674636155705682L;
	private String statusCode;
    private String statusDescription;
    private String businessDescription;
    private String ehfRef;
    private String ehfDesc;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getBusinessDescription() {
		return businessDescription;
	}
	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
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
		builder.append("EhfRecord [statusCode=");
		builder.append(statusCode);
		builder.append(", statusDescription=");
		builder.append(statusDescription);
		builder.append(", businessDescription=");
		builder.append(businessDescription);
		builder.append(", ehfRef=");
		builder.append(ehfRef);
		builder.append(", ehfDesc=");
		builder.append(ehfDesc);
		builder.append("]");
		return builder.toString();
	}
}
