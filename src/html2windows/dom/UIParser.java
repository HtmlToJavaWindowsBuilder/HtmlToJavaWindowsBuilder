package html2windows.dom;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class UIParser {
    public Document createDocument(){
        return null;
    }
    
    public Document parse(String input){
    	try {

    			File stocks = new File("Stocks.xml");
	    		
    			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    		Document doc = (Document) dBuilder.parse(stocks);
	    		//((org.w3c.dom.Node) doc.documentElement()).normalize();
/*
	    		System.out.println("root of xml file" + doc.documentElement().getName());
	    		NodeList nodes = doc.getElementsByTagName("stock");
	    		System.out.println("==========================");

	    		for (int i = 0; i < nodes.length(); i++) {
		    		Node node = nodes.item(i);
		
		    		if (node.nodeType() == Node.ELEMENT_NODE) {
			    		Element element = (Element) node;
			    		System.out.println("Stock Symbol: " + getValue("symbol", element));
			    		System.out.println("Stock Price: " + getValue("price", element));
			    		System.out.println("Stock Quantity: " + getValue("quantity", element));
		    		}
		    	}
	*/	    	
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }
	    return null;
    }
    
    public Document parse(File input){
        return null;
    }
    private static String getValue(String tag, Element element) {
    	NodeList nodes = element.getElementsByTagName(tag).item(0).childNodes();
    	Node node = (Node) nodes.item(0);
    	return node.nodeValue();
    }
    


    	
}
