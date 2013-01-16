package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Interface of Selector to validate element path.
 * Can be combined as a chain and the validation algorithm is to check 
 * the selector from last to first.
 *
 * @author Southernbear
 */
interface Selector{
	/**
	 * Return whether the element matches the selector.
	 *
	 * @param element Element to be validated.
	 * @return Whether the element matches the selector.
	 */
	public boolean match(Element element);
	
	/**
	 * Set previous selector.
	 *
	 * @param selector The previous selector.
	 */
	public void setPrev(Selector selector);

	/**
	 * Return previous selector.
	 *
	 * @return Previous selector.
	 */
	public Selector prev();
}
