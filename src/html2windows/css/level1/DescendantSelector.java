package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Node;

/**
 * Selector used to check each ancestors whether match previous selector.
 *
 * @author Southernbear
 */
class DescendantSelector extends SelectorAdapter{
	/**
	 * Traceback ancestors and use previous selector to validate 
	 * each of them consequently.
	 * 
	 * @param element Element to be validated.
	 * @return If one of ancestors matches, return true.
	 */
	public boolean match(Element element){
		if (prev() == null)
			return false;

		Element ancestor = parentElement(element);
		for (; ancestor != null; ancestor = parentElement(ancestor)) {
			if (prev().match(ancestor))
				return true;
		}
		return false;
	}
	
	/**
	 * Return parent of element if parent is Element.
	 *
	 * @return Parent of element.if parent is Element.
	 */
	private Element parentElement(Element element){
		Node parent = element.parentNode();
		if (parent instanceof Element){
			return (Element)parent;
		}
		else {
			return null;
		}
	}
}
