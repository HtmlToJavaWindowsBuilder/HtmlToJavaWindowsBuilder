package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

class TypeSelector extends SimpleSelector{
	String tagName;

	public TypeSelector(String tagName){
		this.tagName = tagName;
	}

	protected boolean realMatch(Element element){
		return element.nodeName().equals(tagName);
	}
}
