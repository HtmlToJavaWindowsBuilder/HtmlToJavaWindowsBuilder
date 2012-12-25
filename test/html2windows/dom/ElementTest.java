import html2windows.dom.Document;
import html2windows.dom.Element;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ElementTest{
	Document document = new Document();
	Element element;

	@Before
	public void before(){
		element = document.createElement("div");
	}

	@Test
	public void attribute(){
		HashMap<String, String> attr = new HashMap<String, String>();
		attr.put("id", "container");
		attr.put("class", "red-box");

		assertNull("Not existing name should return null", element.getAttribute("id"));
		assertNull("Not existing name should return null", element.getAttributeNode("id"));

		assertNull("Pass null should return null", element.getAttribute(null));

		for(String name : attr.keySet()){
			String value = attr.get(name);
			element.setAttribute(name, value);
			assertEquals("'" + name + "' should get value '" + value + "'", element.getAttribute(name), value);
		}

		for(String name : attr.keySet()){
			String value = attr.get(name);
			element.removeAttribute(name);
			assertNull("'" + name + "' should get null", element.getAttribute(name));
		}
	}
}
