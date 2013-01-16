package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Selector used to check ID.
 *
 * @author Southernbear
 */
class IDSelector extends SimpleSelector{
	/**
	 * ID
	 */
	String id;

	/**
	 * Constructor.
	 */
	public IDSelector(String id){
		this.id = id;
	}

	/**
	 * Return whether element's id equals selector's. 
	 *
	 * @return Whether element's id equals selector's. 
	 */
	protected boolean realMatch(Element element){
		return id.equals(element.getAttribute("id"));
	}
}
