package simulator.util;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;

/**
 * A general XML parser using the DOM model (see http://en.wikipedia.org/wiki/Document_Object_Model).
 * Author: Stephane Ploix
 * Date: 19 février. 08 / 09:30:01
 */
public class XMLparser {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";


    /**
     * parse an XML string
     * @param xmlString the string to be parsed
     * @return the root XML element
     */
    static public Element getRootElement(String xmlString) throws ParserConfigurationException, IOException, SAXException {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document document = builder.parse(new InputSource(new StringReader(xmlString)));
    	return document.getDocumentElement();
    }

    /**
     * Provide sub-elements of the specified XML element according to the specified element name (character '*' means all the sub-elements)
     * @param parentElement the parent element
     * @param subElementName the specified element name
     * @return sub-elements of the parent XML element
     */
    static public Element[] getSubElements(Element parentElement, String subElementName) {
        NodeList nodeList = parentElement.getElementsByTagName(subElementName);
        Element[] subElements = new Element[nodeList.getLength()];
        for(int i=0;i<nodeList.getLength();i++) {
            subElements[i]=(Element)nodeList.item(i);
        }
        return subElements;
    }

    /**
     * Provide data associated to a element (for example "<test>first test</test>" leads to "first test")
     * @param element the specified element
     * @return the text corresponding to the element if it is a terminal element, null otherwise
     */
    static public String getText(Element element) {
        NodeList children = element.getChildNodes();
        if(children.getLength()==1 && Text.class.isInstance(children.item(0)))
            return children.item(0).getTextContent();
        else
            return null;
    }

    /**
     * Test if the Element is terminal i.e. it does contain only text.
     * @param element element to be tested
     * @return true if it is a terminal element
     */
    static public boolean isTerminalElement(Element element) {
        NodeList children = element.getChildNodes();
        return children.getLength()==1 && Text.class.isInstance(children.item(0));
    }
    
    public static void main(String[] args) {
    	String xmlString = "<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>";
    	try {
			Element rootElement = XMLparser.getRootElement(xmlString);
			System.out.println(rootElement);
			System.out.println(XMLparser.getText(XMLparser.getSubElements(rootElement, "to")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
}
