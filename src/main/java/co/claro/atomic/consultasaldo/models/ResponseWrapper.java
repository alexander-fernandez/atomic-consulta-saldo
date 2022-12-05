package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;


/**
 * atomic-consulta-saldo
 * ResponseWrapper.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class ResponseWrapper implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = 3547641363206949943L;

	private ResponseHeader header;
	private ResponsePayload responsePayload;

	public ResponseWrapper() {
	}
	
	

	public ResponseHeader getHeader() {
		return header;
	}



	public void setHeader(ResponseHeader header) {
		this.header = header;
	}



	public ResponsePayload getResponsePayload() {
		return responsePayload;
	}



	public void setResponsePayload(ResponsePayload responsePayload) {
		this.responsePayload = responsePayload;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseWrapper [header=");
		builder.append(header);
		builder.append(", responsePayload=");
		builder.append(responsePayload);
		builder.append("]");
		return builder.toString();
	}
}