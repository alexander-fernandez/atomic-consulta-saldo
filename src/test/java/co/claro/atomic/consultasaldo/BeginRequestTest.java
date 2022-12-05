package co.claro.atomic.consultasaldo;

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
import io.quarkus.test.junit.QuarkusTest;



/**
 * atomic-consulta-saldo
 * BeginRequestTest.java
 * Nov 18, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@QuarkusTest
public class BeginRequestTest extends CamelRouterTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(BeginRequestTest.class);
	

	@Test
	public void transfortRequestTestSuccess() throws Exception {
		

		logger.info("============= BeginRequestTest====================");

		Long messageID = System.currentTimeMillis();
		
		RequestWrapper request = CamelRouterTestSupport.payloadObjectMapper.readValue(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()), RequestWrapper.class);

		// Sends the message with valid credentials header
		Exchange exchange = this.getProducerTemplate().send("seda:dispatchRequest",
				ExchangeBuilder.anExchange(this.getContext())
				.withBody(request)
				.withHeader("messageID", messageID.toString())
				.build());

		String caseCamel = exchange.getIn().getBody(String.class);
						
		// Case 1: no DataValidationException
		assertNotNull(caseCamel);
		assertTrue(caseCamel.contains(messageID.toString()));
				
	}

}