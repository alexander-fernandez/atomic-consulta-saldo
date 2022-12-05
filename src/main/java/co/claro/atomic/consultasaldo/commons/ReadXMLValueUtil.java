package co.claro.atomic.consultasaldo.commons;

import org.w3c.dom.Document;

/**
 * atomic-consulta-saldo
 * ReadXMLValueUtil.java
 * Nov 9, 2022
 *
 * @author avazquez | Claro
 * @version  1.0.0
 * 
 */
public abstract class ReadXMLValueUtil {
	
	public static String getTagValue(Document doc , String tagName) {
		return doc.getElementsByTagName(tagName).item(0) != null ? doc.getElementsByTagName(tagName).item(0).getTextContent() : "";         
	}

	public static String getTagValueByIndex(Document doc , String tagName, int index) {
		return doc.getElementsByTagName(tagName).item(index) != null ? doc.getElementsByTagName(tagName).item(index).getTextContent() : "";         
	}

}
