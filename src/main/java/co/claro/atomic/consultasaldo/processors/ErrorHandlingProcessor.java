package co.claro.atomic.consultasaldo.processors;

import static co.claro.atomic.consultasaldo.commons.AtomicConstants.MEDIA_TYPE_APP_JSON;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ORIGINAL_REQUEST;
import static co.claro.atomic.consultasaldo.commons.ErrorCodesEnum.OSP1004;
import static co.claro.atomic.consultasaldo.commons.ErrorCodesEnum.OSP1999;
import static co.claro.atomic.consultasaldo.commons.TransactionStatusEnum.FAILURE;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangeTimedOutException;
import org.apache.camel.Processor;
import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.HttpHostConnectException;
import org.jboss.logging.Logger;

import co.claro.atomic.consultasaldo.configuration.RestConfiguration;
import co.claro.atomic.consultasaldo.models.EhfInfo;
import co.claro.atomic.consultasaldo.models.Item;
import co.claro.atomic.consultasaldo.models.RequestWrapper;
import co.claro.atomic.consultasaldo.models.ResponseHeader;
import co.claro.atomic.consultasaldo.models.ResponsePayload;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;


/**
 * atomic-consulta-saldo
 * ErrorHandlingProcessor.java
 * Nov 18, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@ApplicationScoped
@Named("errorHandlingProcessor")
public class ErrorHandlingProcessor implements Processor {
	
    private static final Logger LOG = Logger.getLogger(RestConfiguration.class);


	@Override
	public void process(Exchange exchange) throws Exception {

		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
		LOG.info(exception.getClass());
        RequestWrapper originalRequest = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);

		String routeCode = "";
		String routeName = "";
		String channelCode = "";
		String channelName = "";
		String messageID = "";
		
		ResponseWrapper responseWrapper = new ResponseWrapper();

		if (null != originalRequest) {
			ResponsePayload responsePayload = new ResponsePayload();
			responsePayload.setPrimaryData(originalRequest.getRequestPayload().getPrimaryData());
			responseWrapper.setResponsePayload(responsePayload);
			
			routeCode = originalRequest.getHeader().getRouteCode();
			routeName = originalRequest.getHeader().getRouteName();
			channelCode = originalRequest.getHeader().getChannelCode();
			channelName = originalRequest.getHeader().getChannelName();
			messageID = originalRequest.getHeader().getMessageID();
		}

		Item entry = null;
		List<Item> listofItems = new ArrayList<>();

		EhfInfo ehfInfo = new EhfInfo();

		if (exception instanceof ExchangeTimedOutException ||
				exception instanceof SocketTimeoutException ||
				exception instanceof NoHttpResponseException || 
				exception instanceof HttpHostConnectException) {			
			entry = new Item();
			entry.setEhfDesc(OSP1004.getMessage());
			entry.setEhfRef(OSP1004.getCode());
			listofItems.add(entry);
		} else {
			entry = new Item();
			entry.setEhfDesc(OSP1999.getMessage());
			entry.setEhfRef(OSP1999.getCode());
			listofItems.add(entry);
		}
		
		ehfInfo.setItem(listofItems);
		ResponseHeader header = new ResponseHeader();
		header.setEhfInfo(ehfInfo);

		header.setMessageID(messageID);
		header.setTargetSystemID("NotAvailable");
		header.setChannelCode(channelCode);
		header.setChannelName(channelName);
		header.setRouteCode(routeCode);
		header.setRouteName(routeName);
		header.setStatusCode(FAILURE.getCode());
		header.setStatusDescription(FAILURE.getDescription());

		responseWrapper.setHeader(header);
		exchange.getIn().setBody(responseWrapper, ResponseWrapper.class);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MEDIA_TYPE_APP_JSON);
        
        LOG.info("Atomic::Error::MessageID ["+ messageID +"]"+null == exception.getMessage()?exception.toString():exception.getMessage());
	}
}
