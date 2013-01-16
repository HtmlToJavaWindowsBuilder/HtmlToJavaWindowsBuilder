package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

abstract class SelectorAdapter implements Selector{
	protected Selector prevSelector;
	
	public void setPrev(Selector selector){
		prevSelector = selector;
	}
	
	public Selector prev(){
		return prevSelector;
	}
}
