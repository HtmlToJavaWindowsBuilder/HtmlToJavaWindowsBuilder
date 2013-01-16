package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Template of simple selector
 *
 * @author Southernbear
 */
abstract class SimpleSelector extends SelectorAdapter{
	/**
	 * {@inheritDoc}
	 *
	 * Match from last selector to first in the selector chain
	 * and will be broken and fail once a seletor is not match.
	 * Call realMatch to match.
	 */
	public boolean match(Element element){
		if (realMatch(element)) {
			if (prev() == null || (prev() != null && prev().match(element))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Selector match function.
	 *
	 * @param element Element to be validated.
	 * @return Whether the element matches the selector.
	 */
	protected abstract boolean realMatch(Element element);
}
