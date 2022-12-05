package co.claro.atomic.consultasaldo;

import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ORIGINAL_REQUEST;
import static co.claro.atomic.consultasaldo.commons.Examples.REQUEST_EXAMPLE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.claro.atomic.consultasaldo.component.CamelRouterTestSupport;
import co.claro.atomic.consultasaldo.models.RequestWrapper;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;
import io.quarkus.test.junit.QuarkusTest;



/**
 * atomic-consulta-saldo
 * CallBackEndTest.java
 * Nov 18, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@QuarkusTest
public class CallBackEndTest extends CamelRouterTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(CallBackEndTest.class);

	/**
	 * Test to validate channel authentication against vault credentials - success
	 * 
	 * @throws Exception
	 */
	@Test
	public void callServiceSuccessTest() throws Exception {
		

		logger.info("callTServiceSuccessTest");

		Long messageID = System.currentTimeMillis();
		
		RequestWrapper requestWrapper = CallBackEndTest.payloadObjectMapper.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);
		
		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:callBackEnd",
				ExchangeBuilder.anExchange(this.getContext())
				.withBody(requestWrapper)
				.withProperty(ORIGINAL_REQUEST, requestWrapper).build());


		String responseWrapper = exchange.getIn().getBody(String.class);

		// Case 1: no DataValidationException
		assertNotNull(exchange);
		assertTrue(responseWrapper.contains("OSP-1002"));
	}
	
	@Test
	public void freeMarketNoUserPasswordFailureTest()  throws Exception {

		logger.info("callServiceFailureTest");

		Long messageID = System.currentTimeMillis();

		RequestWrapper requestWrapper = CallBackEndTest.payloadObjectMapper.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);
		
		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("direct:callBackEnd",
				ExchangeBuilder.anExchange(this.getContext())
				.withProperty(ORIGINAL_REQUEST, requestWrapper).build());

		ResponseWrapper responseWrapper = exchange.getIn().getBody(ResponseWrapper.class);

		// Case 1: no DataValidationException
		assertNotNull(exchange);
		assertTrue(responseWrapper.getHeader().getStatusCode().equals("1"));
	}
}