package co.claro.atomic.consultasaldo;

import static co.claro.atomic.consultasaldo.commons.Examples.RESPONSE_OK;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.claro.atomic.consultasaldo.component.CamelRouterTestSupport;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;
import io.quarkus.test.junit.QuarkusTest;


/**
 * Request Mock Class (for Testing Purposes)
 * Dec 9, 2020
 * AuthenticationValidationTest.java
 *
 * @author Roy Chela | Bring global - KCB
 * @version 1.0.0
 */

@QuarkusTest
public class ResponseTest extends CamelRouterTestSupport{

	private static final Logger logger = LoggerFactory.getLogger(ResponseTest.class);
	
	/**
	 * Test to validate channel authentication against vault credentials -  success
	 * @throws Exception
	 */
	@Test
	public void atomicResponseSuccessTest() throws Exception {
		

		logger.info("atomicResponseSuccessTest");

		Long messageID = System.currentTimeMillis();
		
		ResponseWrapper responseWrapper = CamelRouterTestSupport.payloadObjectMapper.readValue(RESPONSE_OK.replaceAll("KCB_MESSAGEID", messageID.toString()), ResponseWrapper.class);
		
		Map<String, Object> header1 = new HashMap<String, Object>();

		header1.put("messageID", messageID);

		String case1 = this.sendMessage("direct:response", responseWrapper, header1);
				
		// Case 1: no DataValidationException
		assertNotNull(case1);
		assertTrue(case1.contains("Processed Successfully"));
	}
}
