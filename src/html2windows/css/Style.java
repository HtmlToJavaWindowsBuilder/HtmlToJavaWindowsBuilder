package html2windows.css;

import java.util.Comparator;
import java.util.TreeSet;

import html2windows.dom.Element;

/**
 * style simulate CSS's style. It contains a set of CSSRuleSets.
 * You can add CSSRuleSet to Style or set property to CSSRuleSet in Style.
 * You can also get properties from CSSRuleSet in Style in the order of priority.
 * 
 * @author 		Jason Kuo
 */

public class Style {

	/**
	 * define CSSRuleSet's max priority as 5
	 */
	private static final int MAX_PRIORITY=5;
	
	/**
	 * element that own this style
	 */
	private Element element;
	
	/**
	 * comparator that compare CSSRuleSet with priority
	 */
	private Comparator<CSSRuleSet> comparator = new CssRuleSetComparator();
	
	/**
	 * TreeSet of CSSRuleSet
	 */
	private TreeSet <CSSRuleSet> set = new TreeSet<CSSRuleSet>(comparator);
	
	/**
	 * create style and add it to CSSRuleSet
	 * @param element 			parent node
	 */
	public Style(Element element){
		this.element=element;
		CSSRuleSet ruleSet=new CSSRuleSet(MAX_PRIORITY);
		set.add(ruleSet);
	}
	
    /**
     * set CssRuleSet's property to first of CSSRuleSet
     * @param propertyName 			inserted property name
     * @param value		 		inserted property value
     */
    public void setProperty(String propertyName, String value){
        set.first().setProperty(propertyName,value);
    }
    
    /**
     * get property value according to the order of treeSet(property)
     * @param propertyName			property name
     * @return					property's value, return null if property value is null
     */
    public String getProperty(String propertyName){
    	String value=null;
    	for(CSSRuleSet ruleSet : set){
    		value=ruleSet.getProperty(propertyName);
    		if(value!=null)
    			return value;
    	}
        return null;	
    }
  
    /**
     * add new CSSRuleSet to Style
     * @param cssRuleSet		CSSRuleSet to be added
     */
    public void addCSSRuleSet(CSSRuleSet cssRuleSet){
    	set.add(cssRuleSet);
    }
    
    /**
     * get element that own this style
     * @return			owner of the Style 
     */
    public Element getElement(){
        return element;
    }
    
    /**
     * CSSRuleSet's comparator
     * compare CSSRuleSets with their priority
     */
    public class CssRuleSetComparator implements Comparator<CSSRuleSet>{
		
    	/**
    	 *	compare two CSSRuleSets with their priority
    	 *	@return		return 1 if o1>o2, otherwise -1 
    	 */
    	@Override
		public int compare(CSSRuleSet o1, CSSRuleSet o2) {
			return (o1.getPriority()>o2.getPriority())?1:-1;
		}
	}
}
