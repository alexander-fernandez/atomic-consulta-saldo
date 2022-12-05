package co.claro.atomic.consultasaldo.component;

import static co.claro.atomic.consultasaldo.commons.Examples.SUCCESSFUL_RESPONSE;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * adapter-consulta-saldo CamelRouterTestSupport.java Nov 4, 2022
 *
 * @author avazquez | Claro
 * @version 1.0.0
 * 
 */

@ApplicationScoped
public class CamelRouterTestSupport extends CamelQuarkusTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(CamelRouterTestSupport.class);

	@Inject
	private CamelContext camelContext;

	@Inject
	private ProducerTemplate producerTemplate;

	public static final ObjectMapper payloadObjectMapper = new ObjectMapper();

	static {
		// Add some extra configuration as needed
		payloadObjectMapper.disable(SerializationFeature.INDENT_OUTPUT);
		payloadObjectMapper.setSerializationInclusion(Include.NON_NULL);
	}

	/**
	 * Method to send a message to a proper endpoint
	 * 
	 * @param endpoint String
	 * @param <T>
	 * @param body     T
	 * @param header   Map<String, Object>[]
	 * @return String
	 */
	public <T> String sendMessage(String endpoint, T body, Map<String, Object> headers) {

		Object result = producerTemplate.sendBodyAndHeaders(endpoint, ExchangePattern.InOut, body, headers);

		return camelContext.getTypeConverter().convertTo(String.class, result);
	}

	/**
	 * @return the context
	 */
	public CamelContext getContext() {
		return camelContext;
	}

	/**
	 * @return the producerTemplate
	 */
	public ProducerTemplate getProducerTemplate() {
		return producerTemplate;
	}

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@BeforeEach
	public void doEach() throws Exception {
		
		AdviceWithRouteBuilder mockKafka = new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				
				interceptSendToEndpoint("kafka:*")
				.skipSendToOriginalEndpoint()
				.setBody(simple("Mensaje enviado mock")).log("${body}");
				
				weaveAddLast().to("mock:sendMessageKafka");
			}
		};

		AdviceWithRouteBuilder mockService = new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				
				interceptSendToEndpoint("http:*")
				.skipSendToOriginalEndpoint()
				.setBody(simple(SUCCESSFUL_RESPONSE));
				
				weaveAddLast().to("mock:callBEService");
			}
		};

		AdviceWith.adviceWith(
				this.context.adapt(ModelCamelContext.class).getRouteDefinition("co.claro.atomic.request.callBackEnd"),
				this.context, mockService);
		AdviceWith.adviceWith(
				this.context.adapt(ModelCamelContext.class).getRouteDefinition("co.claro.atomic.response.sendTopic"),
				this.context, mockKafka);
		
	}
	

	@AfterEach
	public void doAfter() throws Exception {

		this.context.resume();
	}

}
