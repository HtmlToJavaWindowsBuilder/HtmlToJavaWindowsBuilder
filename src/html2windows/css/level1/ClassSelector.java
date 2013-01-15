package html2windows.css.level1;

import html2windows.dom.Element;
import html2windows.dom.Document;

class ClassSelector extends SimpleSelector{
	String className;

	public ClassSelector(String className){
		this.className = className;
	}

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
