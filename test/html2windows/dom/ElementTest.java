package html2windows.dom;

import html2windows.dom.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.*;

public class ElementTest{
	Document document;
	ElementInter element;

	@Before
	public void before(){
		document = new Document(){
			public static final long serialVersionUID = 1L;
			public Element createElement(String tagName){
				ElementInter element = new ElementInter(tagName);
				element.setOwnerDocument(this);
				return element;
			}
		};
		element = (ElementInter)document.createElement("div");
	}

	@Test
	public void testOwnerDocument(){
		element.setOwnerDocument(document);
		assertEquals(null, document, element.ownerDocument());
	}

	@Test
	public void testTagName(){
		assertEquals(null, "div", element.tagName());
	}

	@Test
	public void testGetElementsByTagName(){
		Element element1 = document.createElement("div");
		Element element2 = document.createElement("h1");
		Element element1_1 = document.createElement("p");
		Element element1_2 = document.createElement("div");
		Element element2_1 = document.createElement("div");
		Element element2_1_1 = document.createElement("div");

		element.appendChild(element1);
		element.appendChild(element2);
		element1.appendChild(element1_1);
		element1.appendChild(element1_2);
		element2.appendChild(element2_1);
		element2_1.appendChild(element2_1_1);

		NodeList list = element.getElementsByTagName("div");
		
		assertEquals("list should have 4 elements", 4, list.length());
		assertTrue("element 1 should in list", list.contains(element1));
		assertFalse("element 1 1 should in list", list.contains(element1_1));
		assertTrue("element 1 2 should in list", list.contains(element1_2));
		assertFalse("element 2 should in list", list.contains(element2));
		assertTrue("element 2 1 should in list", list.contains(element2_1));
		assertTrue("element 2 1 1 should in list", list.contains(element2_1_1));
	}

	@Test
	public void attribute(){
		HashMap<String, String> attr = new HashMap<String, String>();
		attr.put("id", "container");
		attr.put("class", "red-box");

		assertNull("Not existing name should return null", element.getAttribute("id"));

		assertNull("Pass null should return null", element.getAttribute(null));

		for(String name : attr.keySet()){
			String value = attr.get(name);
			element.setAttribute(name, value);
			assertEquals("'" + name + "' should get value '" + value + "'", value, element.getAttribute(name));
		}

		for(String name : attr.keySet()){
			String value = attr.get(name);
			element.removeAttribute(name);
			assertNull("'" + name + "' should get null", element.getAttribute(name));
		}
	}

	@Test
	public void testNodeName(){
		assertEquals(null, "div", element.nodeName());
	}

	@Test
	public void testNodeValue(){
		assertNull(null, element.nodeValue());
	}

	@Test
	public void testNodeType(){
		assertEquals("Expect ELEMENT_NODE", Node.ELEMENT_NODE, element.nodeType());
	}

	@Test
	public void testparentNode(){
		Element parent = document.createElement("div");
		parent.appendChild(element);
		assertEquals(null, parent, element.parentNode());
	}
	
	@Test
	public void appendChildNodes(){
		ArrayList<Node> children = new ArrayList<Node>();
		
		Node childNodeElement = document.createElement("h1");
		children.add(childNodeElement);
		
		
		for(Node child : children){
			element.appendChild(child);
		}
		
		NodeList childNodes = element.childNodes();
		for(int i = 0; i < children.size(); i++){
			assertEquals(children.get(i), childNodes.get(i));
		}
	}

	@Test
	public void testFirstChildAndLastChild(){
		assertNull("Expect no child(null)", element.firstChild());
		assertNull("Expect no child(null)", element.lastChild());

		Node child1 = document.createElement("h1");
		Node child2 = document.createElement("h1");
		element.appendChild(child1);
		element.appendChild(child2);
		
		assertEquals(null, child1, element.firstChild());
		assertEquals(null, child2, element.lastChild());
	}

	@Test
	public void testSibling(){
		assertNull(null, element.previousSibling());
		assertNull(null, element.nextSibling());

		Node prevSibling = document.createElement("h1");
		Node nextSibling = document.createElement("h1");
		Element parent = document.createElement("div");

		parent.appendChild(prevSibling);
		parent.appendChild(element);
		parent.appendChild(nextSibling);

		assertEquals(null, prevSibling, element.previousSibling());
		assertEquals(null, nextSibling, element.nextSibling());
	}

	@Test
	public void testInsertBefore(){
		Element child1 = document.createElement("div");
		Element child2 = document.createElement("div");
		Element child3 = document.createElement("div");

		element.insertBefore(child3, null);
		element.insertBefore(child2, child3);
		element.insertBefore(child1, child2);
		
		
		NodeList list1 = element.childNodes();
		assertEquals(null, child1, list1.item(0));
		assertEquals(null, child2, list1.item(1));
		assertEquals(null, child3, list1.item(2));
		
		element.insertBefore(child3, child1);

		NodeList list2 = element.childNodes();
		assertEquals(null, child1, list2.item(1));
		assertEquals(null, child2, list2.item(2));
		assertEquals(null, child3, list2.item(0));

		element.insertBefore(child1, child1);

		NodeList list3 = element.childNodes();
		assertEquals(null, child1, list3.item(1));
		assertEquals(null, child2, list3.item(2));
		assertEquals(null, child3, list3.item(0));
		
	}

	@Test
	public void testReplaceChild(){
		Element child1 = document.createElement("div");
		Element child2 = document.createElement("div");

		element.appendChild(child1);
		element.replaceChild(child2, child1);

		assertEquals("Excepted one child", 1, element.childNodes().length());
		assertEquals(null, child2, element.firstChild());

		element.replaceChild(child2, child2);

		assertEquals("Excepted one child", 1, element.childNodes().length());
		assertEquals(null, child2, element.firstChild());
	}

	@Test
	public void testRemoveChild(){
		Element child1 = document.createElement("div");
		Element child2 = document.createElement("div");
		Element child3 = document.createElement("div");

		element.appendChild(child1);
		element.appendChild(child2);
		element.appendChild(child3);

		element.removeChild(child2);
		
		NodeList list1 = element.childNodes();
		assertEquals("list should have only two child", 2, list1.length());
		assertEquals(null, child1, list1.item(0));
		assertEquals(null, child3, list1.item(1));
	
		element.removeChild(child1);
		
		NodeList list2 = element.childNodes();
		assertEquals("list should have only one child", 1, list2.length());
		assertEquals(null, child3, list2.item(0));
		
		element.removeChild(child3);
		
		NodeList list3 = element.childNodes();
		assertEquals("list should have no child", 0, list3.length());
	}

	@Test
	public void testHasChildNodes(){
		Element child = document.createElement("div");

		assertFalse(null, element.hasChildNodes());

		element.appendChild(child);

		assertTrue(null, element.hasChildNodes());
	}
}
