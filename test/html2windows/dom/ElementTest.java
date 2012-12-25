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
}
