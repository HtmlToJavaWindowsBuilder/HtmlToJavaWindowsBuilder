package html2windows.dom;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.FileReader;

import java.lang.String;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.UnsupportedEncodingException;

/**
 * It will read file or String ,then building the document 
 *
 * @author bee040811
 */
public class UIParser {

    /**
     * parsing the css struct will construct the Document by recursive
     *
     * @param element   mean element will save nodeType and nodeValue
     * @param document  mean the document
     *
     * @return element  
     */
	private Element parseElement(org.w3c.dom.Element element, Document document){
		
		Element outputElement = document.createElement(element.getTagName());

		org.w3c.dom.NamedNodeMap attributeMap = element.getAttributes();
		for(int i = 0 ; i < attributeMap.getLength() ; i++){
			org.w3c.dom.Node attribute  = attributeMap.item(i);	
			String name = attribute.getNodeName();
			String value = attribute.getNodeValue();

			Attr outputAttribute = document.createAttribute(name);
			outputAttribute.setValue(value);
			outputElement.setAttributeNode(outputAttribute);
		}
		
		org.w3c.dom.NodeList childNodes = element.getChildNodes();
		for(int i = 0 ; i < childNodes.getLength(); i++){
			org.w3c.dom.Node node = childNodes.item(i);
			short type = node.getNodeType();
			switch (type){
				case org.w3c.dom.Node.TEXT_NODE:{
					org.w3c.dom.Text text = (org.w3c.dom.Text) node;
					Text outputText = document.createTextNode(text.getData());
					
					outputElement.appendChild(outputText);
				}
					break;
				case org.w3c.dom.Node.ELEMENT_NODE:{
					org.w3c.dom.Element childElement = (org.w3c.dom.Element) node;
					Element outputChildElement = parseElement(childElement, document);

					outputElement.appendChild(outputChildElement);
				}
					break;
				default:
					break;
			}
		}

		return outputElement;
	}

    /**
     * Function will parse the file into document
     *
     * @param input     String 
     *
     * @return Document
     */
	public Document parse(String input) {
        input = input.replaceAll(">[ \t\n\r]*<", "><");
        InputStream inputStream = new ByteArrayInputStream( input.getBytes());
        Document outputDocument = parse(inputStream);
        
        return outputDocument;
	}

    /**
     * Function will read file and convert into string and parse into document
     *
     * @param input
     *
     * @return document  
     */
	public Document parse(File input) {
		try{
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            String inputString = "";
            while( (line = reader.readLine() ) != null ) {
                inputString += line;    
            }
			Document outputDocument = parse(inputString);

			return outputDocument;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

    /**
     * others input will transform into InputStream type, then it will parse
     *
     * @param input
     *
     * @return document   
     */
	private Document parse(InputStream input){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(input);
			Document outputDocument = new Document();
			Element outputElement = parseElement(doc.getDocumentElement(),outputDocument);
			outputDocument.appendChild(outputElement);

			return outputDocument;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;	
	}
}
