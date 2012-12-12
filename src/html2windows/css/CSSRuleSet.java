package html2windows.css;

import java.util.HashMap;

public class CSSRuleSet{
	HashMap<String,String> cssRule=new HashMap<String,String>();
	private int priority;
	public CSSRuleSet(int priority){
		this.priority=priority;
	}
	public int getProperty(){
		return priority;
	}
    public String getProperty(String propertyName){
        return cssRule.get(propertyName);
    }
    public void setProperty(String propertyName, String value){
    	cssRule.put(propertyName,value);
    }
    public int compareTo(CSSRuleSet o) {
		return (this.priority<((CSSRuleSet) o).getProperty())?1:-1;
	}
    
}
