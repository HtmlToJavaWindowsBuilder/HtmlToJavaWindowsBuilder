package html2windows.css.level1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import html2windows.dom.*;

import java.util.ArrayList;

public class CSS1SelectorMatcherTest{
	
	@Before
	public void before(){
	}

	@Test
	public void testGetElementsBySelector(){
		CSS1SelectorMatcher matcher = new CSS1SelectorMatcher();
		Document document = new Document();
		Element html = document.createElement("html");

		document.appendChild(html);
		
		{
			ArrayList<Element> list = matcher.getElementBySelector("html", document);
		
			assertEquals(null, 1, list.size());
			assertEquals(null, html, list.get(0));
		}

		Element body = document.createElement("body");
		html.appendChild(body);

		{
			ArrayList<Element> list = matcher.getElementBySelector("body", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, body, list.get(0));
		}
		
		{
			ArrayList<Element> list = matcher.getElementBySelector("html body", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, body, list.get(0));
		}

		Element container = document.createElement("div");
		container.setAttribute("class", "container");
		body.appendChild(container);

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, container, list.get(0));
		}

		Element header = document.createElement("h1");
		header.setAttribute("class", "header");
		container.appendChild(header);

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container h1", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, header, list.get(0));
		}

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container .header", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, header, list.get(0));
		}

		Element menu = document.createElement("ul");
		menu.setAttribute("class", "menu");
		container.appendChild(menu);

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container .menu", document);

			assertEquals(null, 1, list.size());
			assertEquals(null, menu, list.get(0));
		}


		int menuNum = 5;
		Element[] menuItems = new Element[5];
		for (int i = 0; i < menuNum; i++) {
			menuItems[i] = document.createElement("li");
			menuItems[i].setAttribute("class", "menuitem");
			menu.appendChild(menuItems[i]);
		}

		{
			ArrayList<Element> list = matcher.getElementBySelector(".menu li", document);

			assertEquals(null, menuNum, list.size());
			for (int i = 0; i < menuNum; i++){
				assertTrue(list.contains(menuItems[i]));
			}
		}

		{
			ArrayList<Element> list = matcher.getElementBySelector(".menu .menuitem", document);

			assertEquals(null, menuNum, list.size());
			for (int i = 0; i < menuNum; i++){
				assertTrue(list.contains(menuItems[i]));
			}
		}

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container .menuitem", document);

			assertEquals(null, menuNum, list.size());
			for (int i = 0; i < menuNum; i++){
				assertTrue(list.contains(menuItems[i]));
			}
		}

		{
			ArrayList<Element> list = matcher.getElementBySelector(".container .menu .menuitem", document);

			assertEquals(null, menuNum, list.size());
			for (int i = 0; i < menuNum; i++){
				assertTrue(list.contains(menuItems[i]));
			}
		}

	}
}
