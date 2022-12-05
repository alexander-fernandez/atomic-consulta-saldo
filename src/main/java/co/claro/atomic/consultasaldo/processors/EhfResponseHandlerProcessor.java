package co.claro.atomic.consultasaldo.processors;

import static co.claro.atomic.consultasaldo.commons.AtomicConstants.MESSAGE_ID_HEADER;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ORIGINAL_REQUEST;
import static co.claro.atomic.consultasaldo.commons.ErrorCodesEnum.OSP06;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import co.claro.atomic.consultasaldo.models.EhfInfo;
import co.claro.atomic.consultasaldo.models.EhfObject;
import co.claro.atomic.consultasaldo.models.EhfResponse;
import co.claro.atomic.consultasaldo.models.Item;
import co.claro.atomic.consultasaldo.models.RequestWrapper;
import co.claro.atomic.consultasaldo.models.ResponseHeader;
import co.claro.atomic.consultasaldo.models.ResponsePayload;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;

/**
 * atomic-consulta-saldo
 * EhfResponseHandlerProcessor.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@ApplicationScoped
@Named("ehfResponseHandlerProcessor")
public class EhfResponseHandlerProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		RequestWrapper requestWrapper = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);
		EhfResponse ehFResponse = exchange.getIn().getBody(EhfResponse.class);

		ResponsePayload responsePayload = new ResponsePayload();
		responsePayload.setPrimaryData(requestWrapper.getRequestPayload().getPrimaryData()); 

		Item ehfItem = null;
		List<Item> listOfEhfItems = new ArrayList<>();
		for (EhfObject result : ehFResponse.getRecords()) {
			ehfItem = new Item();
			ehfItem.setEhfRef(result.getEhfRecord().getEhfRef());
			ehfItem.setEhfDesc(result.getEhfRecord().getEhfDesc());
			listOfEhfItems.add(ehfItem);
		}
		EhfInfo ehfInfo = new EhfInfo();
		ehfInfo.setItem(listOfEhfItems);

		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setEhfInfo(ehfInfo);
		responseHeader.setMessageID(exchange.getIn().getHeader(MESSAGE_ID_HEADER, String.class));
		responseHeader.setTargetSystemID("NotAvailable");
		responseHeader.setChannelCode(requestWrapper.getHeader().getChannelCode());
		responseHeader.setChannelName(requestWrapper.getHeader().getChannelName());
		responseHeader.setRouteCode(requestWrapper.getHeader().getRouteCode());
		responseHeader.setRouteName(requestWrapper.getHeader().getRouteName());
		responseHeader.setStatusCode(OSP06.getCode());
		responseHeader.setStatusDescription(OSP06.getMessage());

		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setResponsePayload(responsePayload);
		responseWrapper.setHeader(responseHeader);
				
		exchange.getIn().setBody(responseWrapper, ResponseWrapper.class);
	}

}
