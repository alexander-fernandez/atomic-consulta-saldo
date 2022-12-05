package co.claro.atomic.consultasaldo.processors;

import static co.claro.atomic.consultasaldo.commons.AtomicConstants.ORIGINAL_REQUEST;
import static co.claro.atomic.consultasaldo.commons.ErrorCodesEnum.OSP1002;
import static co.claro.atomic.consultasaldo.commons.TransactionStatusEnum.SUCCESS;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.w3c.dom.Document;

import co.claro.atomic.consultasaldo.commons.ReadXMLValueUtil;
import co.claro.atomic.consultasaldo.configuration.RestConfiguration;
import co.claro.atomic.consultasaldo.exceptions.UnsuccessfullException;
import co.claro.atomic.consultasaldo.models.AdditionalData;
import co.claro.atomic.consultasaldo.models.EhfInfo;
import co.claro.atomic.consultasaldo.models.Item;
import co.claro.atomic.consultasaldo.models.RequestWrapper;
import co.claro.atomic.consultasaldo.models.ResponseHeader;
import co.claro.atomic.consultasaldo.models.ResponsePayload;
import co.claro.atomic.consultasaldo.models.ResponseWrapper;


/**
 * atomic-consulta-saldo
 * ResponseHandlerProcessor.java
 * Nov 18, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
@ApplicationScoped
@Named("responseHandlerProcessor")
public class ResponseHandlerProcessor implements Processor {

	@ConfigProperty(name = "be.xpath.success")
    protected String successXpath;
	
	@ConfigProperty(name = "be.xpath.expected-value")
	protected String expectedValue;
	
	@ConfigProperty(name = "be.namespaces.prefix")
	protected String namespacesPrefix;
	
	@ConfigProperty(name = "be.namespaces.type")
	protected String type;

    private DocumentBuilder documentBuilder;
    private XPath xPath;

    @PostConstruct
    public void createNewDocumentBuilder() throws ParserConfigurationException {
        this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.xPath = XPathFactory.newInstance().newXPath();
    }
    
    private static final Logger LOG = Logger.getLogger(RestConfiguration.class);


    @Override
    public void process(Exchange exchange) throws Exception {

        Document document = this.documentBuilder.parse(new ByteArrayInputStream(exchange.getIn().getBody(String.class).getBytes()));
        Boolean successfulResponse = expectedValue.equalsIgnoreCase(xPath.compile(successXpath).evaluate(document, XPathConstants.STRING).toString());

        
        LOG.info("successfulResponse "+successfulResponse);
        if (successfulResponse) {
            RequestWrapper request = exchange.getProperty(ORIGINAL_REQUEST, RequestWrapper.class);
            LOG.info("request "+request.toString());

    		ResponsePayload responsePayload = new ResponsePayload();
    		responsePayload.setPrimaryData(request.getRequestPayload().getPrimaryData());

    		AdditionalData additionalData = new AdditionalData();
    		additionalData.setAccountNumber(ReadXMLValueUtil.getTagValue(document, namespacesPrefix + ":" + "AccountNo"));
    		additionalData.setUniqueRef(request.getRequestPayload().getAdditionalData().getUniqueRef());
    		additionalData.setSessionID(request.getRequestPayload().getAdditionalData().getSessionID());
    		additionalData.setLedgerBalance(ReadXMLValueUtil.getTagValue(document, namespacesPrefix + ":" + "LedgerBalance"));
    		additionalData.setNetBalance(ReadXMLValueUtil.getTagValue(document, namespacesPrefix + ":" + "NetBalance"));
    		additionalData.setRrn(request.getRequestPayload().getAdditionalData().getRrn());
    		responsePayload.setAdditionalData(additionalData);

    		Item entry = null;
    		List<Item> listofItems = new ArrayList<>();
    		entry = new Item();
    		entry.setEhfDesc(OSP1002.getMessage());
    		entry.setEhfRef(OSP1002.getCode());
    		listofItems.add(entry);

    		EhfInfo ehfInfo = new EhfInfo();
    		ehfInfo.setItem(listofItems);
    		
    		ResponseHeader header = new ResponseHeader();
    		header.setEhfInfo(ehfInfo);
    		header.setMessageID(request.getHeader().getMessageID());
    		header.setTargetSystemID("NotAvailable");
    		header.setChannelCode(request.getHeader().getChannelCode());
    		header.setChannelName(request.getHeader().getChannelName());
    		header.setRouteCode(request.getHeader().getRouteCode());
    		header.setRouteName(request.getHeader().getRouteName());
    		header.setStatusCode(SUCCESS.getCode());
    		header.setStatusDescription(SUCCESS.getDescription());
    		header.setServiceCode(request.getHeader().getServiceCode());
    		header.setProcessingCode(request.getHeader().getProcessingCode());

    		ResponseWrapper response = new ResponseWrapper();
    		response.setHeader(header);
    		response.setResponsePayload(responsePayload);

    		exchange.getIn().setBody(response, ResponseWrapper.class);
        } else {
            throw new UnsuccessfullException();
        }
    }
}
