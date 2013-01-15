package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

class IDSelector extends SimpleSelector{
	String id;

	public IDSelector(String id){
		this.id = id;
	}

	protected boolean realMatch(Element element){
		return id.equals(element.getAttribute("id"));
	}
}
