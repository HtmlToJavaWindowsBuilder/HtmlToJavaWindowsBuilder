package html2windows.css;

import java.util.HashMap;

/**
 * CSSRuleSet contains several CSS property in it, such as height, width and their value.
 * You can get or set priority to CSSRuleSet.
 * 
 * @author 		Jason Kuo
 */

public class CSSRuleSet{

	/**
	 * A HashMap that maps property name to its value.
	 */
	private HashMap<String,String> cssRule=new HashMap<String,String>();
	
	/**
	 * This CSS rule set's priority.
	 */
	private int priority;
	
	
	/**
	 * Construct CSSRuleSet and set priority.
	 * 
	 * @param priority		CSSRuleSet's priority
	 */
	public CSSRuleSet(int priority){
		this.priority=priority;
	}
	
	/**
	 * Get CSSRuleSet's Priority.
	 * 
	 * @return				CSSRuleSet's Priority
	 */
	public int getPriority(){
		return priority;
	}
	
    /**
     * Get CSS rule by given property name.
     * 
     * @param propertyName			property to get
     * @return					property's value
     */
    public String getProperty(String propertyName){
        return cssRule.get(propertyName);
    }
    
    /**
     * Set new CSS rule to CSSRuleSet.
     * 
     * @param propertyName			property's name
     * @param value				property's value
     */
    public void setProperty(String propertyName, String value){
    	cssRule.put(propertyName,value);
    }
}
