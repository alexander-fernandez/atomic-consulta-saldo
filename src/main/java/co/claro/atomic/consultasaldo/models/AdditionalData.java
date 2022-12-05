package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * AdditionalData.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class AdditionalData implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = -6445866824539143515L;

	private String accountNumber; 
	private String uniqueRef;
	private String sessionID; 
	private String ledgerBalance;
	private String netBalance;
	private String companyCode;
	private String rrn; 


	public AdditionalData() {
	}
	
	

	public String getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getUniqueRef() {
		return uniqueRef;
	}



	public void setUniqueRef(String uniqueRef) {
		this.uniqueRef = uniqueRef;
	}



	public String getSessionID() {
		return sessionID;
	}



	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}



	public String getLedgerBalance() {
		return ledgerBalance;
	}



	public void setLedgerBalance(String ledgerBalance) {
		this.ledgerBalance = ledgerBalance;
	}



	public String getNetBalance() {
		return netBalance;
	}



	public void setNetBalance(String netBalance) {
		this.netBalance = netBalance;
	}



	public String getCompanyCode() {
		return companyCode;
	}



	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}



	public String getRrn() {
		return rrn;
	}



	public void setRrn(String rrn) {
		this.rrn = rrn;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdditionalData [companyCode=");
		builder.append(companyCode);
		builder.append(", sessionID=");
		builder.append(sessionID);
		builder.append(", uniqueRef=");
		builder.append(uniqueRef);
		builder.append(", rrn=");
		builder.append(rrn);
		builder.append(", ledgerBalance=");
		builder.append(ledgerBalance);
		builder.append(", netBalance=");
		builder.append(netBalance);
		builder.append("]");
		return builder.toString();
	}
}