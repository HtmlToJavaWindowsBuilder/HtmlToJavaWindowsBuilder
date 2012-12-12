package html2windows.css;

import java.util.Comparator;
import java.util.HashMap;

public class CSSRuleSet implements Comparable<CSSRuleSet>{
	HashMap<String,String> cssRule=new HashMap<String,String>();
	public int priority;
	public CSSRuleSet(int priority){
		this.priority=priority;
	}
    public String getProperty(String propertyName){
        return cssRule.get(propertyName);
    }
    public void setProperty(String propertyName, String value){
    	cssRule.put(propertyName,value);
    }
    public int compareTo(CSSRuleSet o) {
		return (this.priority<((CSSRuleSet) o).priority)?1:-1;
	}
	static Comparator<CSSRuleSet> CssComparator = new Comparator<CSSRuleSet>() {
		public int compare(CSSRuleSet o1, CSSRuleSet o2) {
			return o1.compareTo(o2);
		}
	};
}
