package co.claro.atomic.consultasaldo.models;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * atomic-consulta-saldo
 * RequestHeader.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */

public class RequestHeader implements Serializable {
	/** Serial version */
	private static final long serialVersionUID = 7656371320496443722L;
 
	// All this information comes from channel as part of the full payload
	@NotBlank(message = "messageID cannot be null, empty or blank")
    protected String messageID;
    protected String conversationID;
	@Pattern(message="featureCode must be 3 digits", regexp="\\d{3}")
	protected String featureCode;
	@NotBlank(message = "featureName cannot be null, empty or blank")
    protected String featureName;
    protected String serviceCode;
    protected String processingCode;
	@NotBlank(message = "serviceName cannot be null, empty or blank")
    protected String serviceName;
	@Pattern(message="serviceSubCategory must contain only uppercase letters", regexp="[A-Z]+")
    protected String serviceSubCategory;
	@Pattern(message="minorServiceVersion must be digits dot digit format", regexp="\\d\\.\\d")
    protected String minorServiceVersion;
	@Pattern(message="channelCode must be 3 digits", regexp="\\d{3}")
    protected String channelCode;
	@NotBlank(message = "channelName cannot be null, empty or blank")
    protected String channelName;
	@Pattern(message="routeCode must be 3 digits", regexp="\\d{3}")
    protected String routeCode;
	@NotBlank(message = "routeName cannot be null, empty or blank")
    protected String routeName;
	@NotBlank(message = "timeStamp cannot be null, empty or blank")
    protected String timeStamp;
	@Pattern(message="serviceMode must be Sync or Async", regexp="Sync|Async")
    protected String serviceMode;
	@Pattern(message="subscribeEvents must be 0 or 1", regexp="0|1")
    protected String subscribeEvents;
    protected String callBackURL;
    
    
    

	public String getMessageID() {
		return messageID;
	}




	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}




	public String getConversationID() {
		return conversationID;
	}




	public void setConversationID(String conversationID) {
		this.conversationID = conversationID;
	}




	public String getFeatureCode() {
		return featureCode;
	}




	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}




	public String getFeatureName() {
		return featureName;
	}




	public void setFeatureName(String featureName) {
		this.featureName = featureName;
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




	public String getServiceName() {
		return serviceName;
	}




	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}




	public String getServiceSubCategory() {
		return serviceSubCategory;
	}




	public void setServiceSubCategory(String serviceSubCategory) {
		this.serviceSubCategory = serviceSubCategory;
	}




	public String getMinorServiceVersion() {
		return minorServiceVersion;
	}




	public void setMinorServiceVersion(String minorServiceVersion) {
		this.minorServiceVersion = minorServiceVersion;
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




	public String getTimeStamp() {
		return timeStamp;
	}




	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}




	public String getServiceMode() {
		return serviceMode;
	}




	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}




	public String getSubscribeEvents() {
		return subscribeEvents;
	}




	public void setSubscribeEvents(String subscribeEvents) {
		this.subscribeEvents = subscribeEvents;
	}




	public String getCallBackURL() {
		return callBackURL;
	}




	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestHeader [messageID=");
		builder.append(messageID);
		builder.append(", featureCode=");
		builder.append(featureCode);
		builder.append(", featureName=");
		builder.append(featureName);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", processingCode=");
		builder.append(processingCode);
		builder.append(", serviceName=");
		builder.append(serviceName);
		builder.append(", serviceSubCategory=");
		builder.append(serviceSubCategory);
		builder.append(", minorServiceVersion=");
		builder.append(minorServiceVersion);
		builder.append(", channelCode=");
		builder.append(channelCode);
		builder.append(", channelName=");
		builder.append(channelName);
		builder.append(", routeCode=");
		builder.append(routeCode);
		builder.append(", routeName=");
		builder.append(routeName);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", serviceMode=");
		builder.append(serviceMode);
		builder.append(", subscribeEvents=");
		builder.append(subscribeEvents);
		builder.append(", callBackURL=");
		builder.append(callBackURL);
		builder.append("]");
		return builder.toString();
	}
}
