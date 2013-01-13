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
}
