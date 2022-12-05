package co.claro.atomic.consultasaldo;

import static co.claro.atomic.consultasaldo.commons.Examples.REQUEST_EXAMPLE;
import static io.restassured.RestAssured.given;

import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;

import co.claro.atomic.consultasaldo.component.CamelRouterTestSupport;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class RouteTest extends CamelRouterTestSupport {

    @Test
    public void test() throws Exception  {
    	
		Long messageID = System.currentTimeMillis();

        given()
            .contentType(ContentType.APPLICATION_JSON.toString())
            .header("messageID", messageID.toString())
            .body(REQUEST_EXAMPLE.replaceAll("KCB_MESSAGEID", messageID.toString()))
        .when()
            .post("/api/be/consultaSaldoAtomic")
        .then()
            .statusCode(200)
            .header("messageID", messageID.toString());
        
    }
}
