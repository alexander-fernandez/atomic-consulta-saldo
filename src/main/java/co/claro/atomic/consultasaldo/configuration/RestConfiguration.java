package co.claro.atomic.consultasaldo.configuration;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.eclipse.microprofile.config.inject.ConfigProperty;


/**
 * atomic-consulta-saldo
 * RestConfiguration.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@ApplicationScoped
public class RestConfiguration extends RouteBuilder {
	
	@ConfigProperty(name = "openapi.path", defaultValue="/api")
	protected String apiPath;

	@ConfigProperty(name = "openapi.enableCors", defaultValue="true")
	protected Boolean apiEnableCors;

	@ConfigProperty(name = "openapi.api-docs.path", defaultValue="/api/api-docs")
	protected String apiDocsPath;

	@ConfigProperty(name = "openapi.api-docs.version", defaultValue="0.0.1-SNAPSHOT")
	protected String apiDocsVersion;

	@ConfigProperty(name = "info.app.name", defaultValue="api")
	protected String apiDocsTitle;

    @Override
    public void configure() throws Exception {

        // Rest Configuration
        restConfiguration()
            .enableCORS(Boolean.valueOf(apiEnableCors))
            .corsHeaderProperty("Access-Control-Allow-Methods", "POST")
                .apiContextPath(apiDocsPath)
                .apiProperty("api.title", apiDocsTitle)
                .apiProperty("api.version", apiDocsVersion)
                .apiProperty("cors", apiEnableCors.toString())
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint","true")
                .apiVendorExtension(true);
    }
}