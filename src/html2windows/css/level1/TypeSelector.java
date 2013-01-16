package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Selector for type as known as tag name.
 *
 * @author Southernbear
 */
class TypeSelector extends SimpleSelector{
	/**
	 * Tag name.
	 */
	String tagName;

	/**
	 * Constructor
	 */
	public TypeSelector(String tagName){
		this.tagName = tagName;
	}

	/**
	 * Return whether element's tag name equals selector's.
	 *
	 * @return Whether element's tag name equals selector's.
	 */
	protected boolean realMatch(Element element){
		return element.nodeName().equals(tagName);
	}
}
