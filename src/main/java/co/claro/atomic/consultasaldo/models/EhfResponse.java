package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;
import java.util.List;

/**
 * atomic-consulta-saldo
 * EhfResponse.java
 * Nov 1, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class EhfResponse implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4848590694588065814L;
	private Boolean success;
    private String detail;
    private List<EhfObject> records;
    
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<EhfObject> getRecords() {
		return records;
	}
	public void setRecords(List<EhfObject> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EhfResponse [success=");
		builder.append(success);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", records=");
		builder.append(records);
		builder.append("]");
		return builder.toString();
	}
    
    

}