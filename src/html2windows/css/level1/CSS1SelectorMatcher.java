package html2windows.css.level1;

import java.util.ArrayList;

import html2windows.dom.Node;
import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Match and list elements in given document matching a specified selector.
 *
 * @author Southernbear
 */
public class CSS1SelectorMatcher implements html2windows.css.SelectorMatcher{
	/**
	 * Compiler used to compile selector string.
	 * The order of returned selector is inverse of given selector string.
	 */
	CSS1SelectorCompiler compiler = new CSS1SelectorCompiler();
	
	/**
	 * Match and list elements that matches selector.
	 * It is done by scan each element in document and validate the path from 
	 * the right of selector string to left.
	 *
	 * @param selectorGroupText A list of selectors given to search elements
	 *                          seperated by comma.
	 *
	 * @return List of matched elements.
	 */
	public ArrayList<Element> getElementBySelector(String selectorGroupText, Document document){
		ArrayList<Element> result = new ArrayList<Element>();
		
		// Split group by comma symbol and spaces
		String[] selectorTexts = selectorGroupText.split("\\s*,\\s*");
		for(String selectorText : selectorTexts){
			Selector selector = compiler.compile(selectorText);
			ArrayList<Element> elements = select(selector, document);
			result.addAll(elements);
		}
		
		return result;
	}
	
	/**
	 * Starting of traversing document.
	 *
	 * @param selector Target selector.
	 * @param document Document to be scanned.
	 * @return List of matched elements.
	 */
	private ArrayList<Element> select(Selector selector, Document document){
		ArrayList<Element> result = new ArrayList<Element>();
		
		selectTraverse(selector, document.documentElement(), result);
		
		return result;
	}

	/**
	 * Traversing document recursively.
	 *
	 * @param selector Target selector.
	 * @param parent The root of the subtree.
	 * @param document Document to be scanned.
	 * @return List of matched elements.
	 */
	private void selectTraverse(Selector selector, Element parent, ArrayList<Element> result){
		if (selector.match(parent))
			result.add(parent);
		
		for(Node child : parent.childNodes()){
			if (child instanceof Element){
				selectTraverse(selector, (Element)child, result);
			}
		}
	}
}
