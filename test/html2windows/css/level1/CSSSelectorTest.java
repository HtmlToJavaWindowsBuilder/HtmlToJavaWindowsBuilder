package html2windows.css.level1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import html2windows.dom.*;

public class CSSSelectorTest{
	
	@Before
	public void before(){
	}
	
	@Test
	public void testTypeSelector(){
		Document document = new Document();
		Element element = document.createElement("div");
		
		Selector selector = new TypeSelector("div");
		assertTrue("'div' should match div element", selector.match(element));
	}
	
	@Test
	public void testIDSelector(){
		Document document = new Document();
		Element element = document.createElement("div");
		element.setAttribute("id", "id");
		
		Selector selector = new IDSelector("id");
		assertTrue("'#id' should match element 'id'", selector.match(element));
	}

	@Test
	public void testClassSelector(){
		Document document = new Document();
		Element element = document.createElement("div");
		element.setAttribute("class", "container big");

		Selector selector1 = new ClassSelector("container");
		assertTrue("'.container' should match element whose class has 'container'", selector1.match(element));

		Selector selector2 = new ClassSelector("big");
		assertTrue("'.big' should match element whose class has 'big'", selector2.match(element));

		selector1.setPrev(selector2);
		assertTrue("'.big.container' should match element whose class is 'container big'", selector1.match(element));
	}

	@Test
	public void testDescendantSelector(){
		Document document = new Document();
		final Element div = document.createElement("div");
		final Element p = document.createElement("p");
		final Element span = document.createElement("span");

		p.appendChild(span);
		assertNotNull("span should has parent", span.parentNode());
		assertEquals("parent of span is p", p, span.parentNode());
		div.appendChild(p);

		Selector divSelector = new SimpleSelector(){
			public boolean realMatch(Element element){
				return element == div;
			}
		};

		Selector descendantSelector = new DescendantSelector();

		Selector spanSelector = new SimpleSelector(){
			public boolean realMatch(Element element){
				return element == span;
			}
		};

		spanSelector.setPrev(descendantSelector);
		descendantSelector.setPrev(divSelector);

		assertTrue("'div span' should match 'span' in '<div><p><span></span></p></div>'", spanSelector.match(span));
	}
}
