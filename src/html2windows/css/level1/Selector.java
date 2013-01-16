package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

interface Selector{
	public boolean match(Element element);
	
	public void setPrev(Selector selector);
	public Selector prev();
}
