package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * RequestWrapper.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class RequestWrapper implements Serializable {
	/** Serial version UID */
	private static final long serialVersionUID = 6464903968440189419L;

	private RequestHeader header;
	private RequestPayload requestPayload;

	public RequestWrapper() {
	}


	public RequestHeader getHeader() {
		return header;
	}


	public void setHeader(RequestHeader header) {
		this.header = header;
	}


	public RequestPayload getRequestPayload() {
		return requestPayload;
	}


	public void setRequestPayload(RequestPayload requestPayload) {
		this.requestPayload = requestPayload;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestWrapper [header=");
		builder.append(header);
		builder.append(", requestPayload=");
		builder.append(requestPayload);
		builder.append("]");
		return builder.toString();
	}
}
