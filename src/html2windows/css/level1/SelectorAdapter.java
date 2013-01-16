package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Provide default method implementation of Selector.
 *
 * @author Southernbear
 */
abstract class SelectorAdapter implements Selector{
	/**
	 * Previous selector in chain.
	 */
	protected Selector prevSelector;
	
	/**
	 * {@inheritDoc}
	 */
	public void setPrev(Selector selector){
		prevSelector = selector;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Selector prev(){
		return prevSelector;
	}
}
