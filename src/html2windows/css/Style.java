package html2windows.css;

import java.util.Comparator;
import java.util.TreeSet;

import html2windows.dom.Element;

public class Style {
	private static final int MAX_PRIORITY=5;
	Element element;
	Comparator<CSSRuleSet> comparator = new CssRuleSetComparator();
	TreeSet <CSSRuleSet> set = new TreeSet<CSSRuleSet>(comparator);
	
	/*
	 * constructor of style
	 * create style and add inline property CSSRuleSet
	 * arg1 parent node
	 */
	public Style(Element element){
		this.element=element;
		CSSRuleSet ruleSet=new CSSRuleSet(MAX_PRIORITY);
		set.add(ruleSet);
	}
	
	/*
	 * set CssRuleSet's property
	 * inline property set CssRuleSet's property 
	 * arg1 priority name
	 * arg2 priority value
	 * arg3 CSSRuleSet priority
	 */
    public void setProperty(String propertyName, String value){
        set.first().setProperty(propertyName,value);
    }
    
    /*
     * get property
     * get the property value according to the order of treeSet(property)
     * arg1 property name
     * return property value
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
  
    /*
     * add Css ruleset
     * add new ruleset into css ruleset's treeSet
     * arg1 ruleset
     * arg2 ruleset priority
     */
    public void addCSSRuleSet(CSSRuleSet cssRuleSet){
    	set.add(cssRuleSet);
    }
    
    /*
     * get parent node
     * return prarent node
     */
    public Element getElement(){
        return element;
    }
    
    public class CssRuleSetComparator implements Comparator<CSSRuleSet>{
		@Override
		public int compare(CSSRuleSet o1, CSSRuleSet o2) {
			return (o1.getPriority()>o2.getPriority())?1:-1;
		}
	}
}
