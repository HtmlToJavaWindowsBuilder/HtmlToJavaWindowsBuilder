package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

/**
 * Selector used to check class name.
 *
 * @author Southernbear
 */
class ClassSelector extends SimpleSelector{
	/**
	 * Class name.
	 */
	String className;

	/**
	 * Constructor.
	 */
	public ClassSelector(String className){
		this.className = className;
	}

	/**
	 * Return whether element's class name contains selector's.
	 * 
	 * @return Whether element's class name contains selector's.
	 */
	protected boolean realMatch(Element element){
		String className = element.getAttribute("class");
		String[] classNames = className != null ? className.split("\\s+") : new String[0];
		for (String klass : classNames) {
			if (klass.equals(this.className)) {
				return true;
			}
		}
		return false;
	}
}
