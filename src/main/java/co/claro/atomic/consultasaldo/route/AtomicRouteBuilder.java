package co.claro.atomic.consultasaldo.route;

import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ACCEPT;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.CODE_201_MESSAGE;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.CODE_500_MESSAGE;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.CONTENT_TYPE;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.MEDIA_TYPE_APP_JSON;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.TEXT_PLAIN_VALUE;
import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ORIGINAL_REQUEST;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

import co.claro.atomic.consultasaldo.exceptions.UnsuccessfullException;
import co.claro.atomic.consultasaldo.models.RequestWrapper;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;


/**
 * atomic-consulta-saldo
 * AtomicRouteBuilder.java
 * Nov 18, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@ApplicationScoped
public class AtomicRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
    	
        onException(Exception.class)
	        .handled(true)
			.removeHeaders("*", "messageID")
	        .process("errorHandlingProcessor")
	        .to("direct:response");

        rest("/api")
	    .post("/be/consultaSaldoAtomic")
	        .id("atomic")
	        .bindingMode(RestBindingMode.json)
	        .description("REST API in charge of consuming BE service ")
	        .type(RequestWrapper.class)
	        .consumes(MEDIA_TYPE_APP_JSON)
	        .produces(MEDIA_TYPE_APP_JSON)
	        .outType(ResponseWrapper.class)
	        .responseMessage().code(201).message(CODE_201_MESSAGE).endResponseMessage()
	        .responseMessage().code(500).message(CODE_500_MESSAGE).endResponseMessage()
	        .to("seda:dispatchRequest");
        
        
		/* Routes Configuration */
		from("seda:dispatchRequest").routeId("co.claro.atomic.request.dispatchRequest")
				.noStreamCaching().noMessageHistory().noTracing()
				.log(LoggingLevel.INFO, "Consulta Saldo Atomic::MessageID [${headers.messageID}]::Payload [${body}]")
				.setProperty(ORIGINAL_REQUEST, simple("${body}"))
				.to("direct:callBackEnd")
				.to("direct:response");		

		from("direct:callBackEnd").routeId("co.claro.atomic.request.callBackEnd")
			.noStreamCaching().noMessageHistory().noTracing()
            .to("freemarker:BCRequest.ftl?contentCache=true")
            .removeHeaders("*", "messageID")
			.setHeader(ACCEPT, constant(TEXT_PLAIN_VALUE))
			.setHeader(CONTENT_TYPE, constant(TEXT_PLAIN_VALUE))
			.log(LoggingLevel.INFO, "Consulta Saldo Atomic::{{be.uri}}::Request::MessageID [${headers.messageID}]::Payload [${body}]")
			.enrich().simple("{{be.uri}}").id("callBEService")
			.convertBodyTo(String.class)
			.removeHeaders("*", "messageID")
			.log(LoggingLevel.INFO, "Consulta Saldo Atomic::Response::MessageID [${headers.messageID}]::Payload [${body}]")
			.doTry()
				.process("responseHandlerProcessor")
			.doCatch(UnsuccessfullException.class)
				.process("ehfResponseHandlerProcessor")
			.end();
	
		from("direct:response").routeId("co.claro.atomic.response")
			.noStreamCaching().noMessageHistory().noTracing()
			.log(LoggingLevel.INFO, "Atomic::Response Adapter::MessageID [${headers.messageID}]::Payload [${body}]")
			.wireTap("seda:testKafka").id("wiretapKafka");
		
		/* Routes Configuration */
		from("seda:testKafka").routeId("co.claro.atomic.response.sendTopic")
			.noStreamCaching().noMessageHistory().noTracing()
			.marshal().json(JsonLibrary.Gson, RequestWrapper.class)
			.log(LoggingLevel.INFO, " -------------------------------> Send Message to Topic")
            .removeHeaders("*", "messageID")
		    .to("{{kafka.uri}}").id("sendMessageKafka");

    }   
}