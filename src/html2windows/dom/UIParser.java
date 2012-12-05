package html2windows.dom;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UIParser {
	public Document createDocument() {
		return null;
	}
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
					
					outputElement.appendChild(text);
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

	public Document parse(String input) {
		try {
			InputStream inputStream = new ByteArrayInputStream( input.getBytes(""));
			Document outputDocument = parse(inputStream);
			
			return outputDocument;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Document parse(File input) {
		InputStream inputStream = new FileInputStream(input); 
		Document outputDocument = parse(inputStream);

		return outputDocument;
	}

	public Document parse(InputStream input){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(input);
		Document outputDocument = new Document();
		Element outputElement = parseElement(doc.getDocumentElement(),outputDocument);
		outputDocument.appendChild(outputElement);
		
		return outputDocument;
	}
}