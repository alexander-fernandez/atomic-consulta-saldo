package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

/**
 * atomic-consulta-saldo
 * ResponseHeader.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class ResponseHeader implements Serializable {
    
    /** Serial version UID */
    private static final long serialVersionUID = -7857624541088572239L;

    // This comes from http header for adapter layer and from jms header on atomic layer
    protected String messageID;
    
    // This information comes from channel as part of the full payload, we just need to populate it back
    protected String routeCode;
    protected String routeName;
    
    protected String channelCode;
    protected String channelName;
    
    // This information comes from the atomic service
    protected String targetSystemID;
    protected String statusCode;
    protected String statusDescription;
    protected String serviceCode;
    protected String processingCode;
    protected String callBackURL;
    protected String messageCode;
    protected EhfInfo ehfInfo;
    
   

	public String getMessageID() {
		return messageID;
	}



	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}



	public String getRouteCode() {
		return routeCode;
	}



	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}



	public String getRouteName() {
		return routeName;
	}



	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}



	public String getChannelCode() {
		return channelCode;
	}



	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}



	public String getChannelName() {
		return channelName;
	}



	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}



	public String getTargetSystemID() {
		return targetSystemID;
	}



	public void setTargetSystemID(String targetSystemID) {
		this.targetSystemID = targetSystemID;
	}



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



	public String getServiceCode() {
		return serviceCode;
	}



	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}



	public String getProcessingCode() {
		return processingCode;
	}



	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}



	public String getCallBackURL() {
		return callBackURL;
	}



	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}



	public String getMessageCode() {
		return messageCode;
	}



	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}



	public EhfInfo getEhfInfo() {
		return ehfInfo;
	}



	public void setEhfInfo(EhfInfo ehfInfo) {
		this.ehfInfo = ehfInfo;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseHeader [messageID=");
		builder.append(messageID);
		builder.append(", routeCode=");
		builder.append(routeCode);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", channelCode=");
		builder.append(channelCode);
		builder.append(", channelName=");
		builder.append(channelName);
		builder.append(", targetSystemID=");
		builder.append(targetSystemID);
		builder.append(", statusCode=");
		builder.append(statusCode);
		builder.append(", statusDescription=");
		builder.append(statusDescription);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", processingCode=");
		builder.append(processingCode);
		builder.append(", callBackURL=");
		builder.append(callBackURL);
		builder.append(", messageCode=");
		builder.append(messageCode);
		builder.append(", ehfInfo=");
		builder.append(ehfInfo);
		builder.append("]");
		return builder.toString();
	}	
	
}
