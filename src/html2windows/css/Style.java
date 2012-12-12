package html2windows.css;

import java.util.Comparator;
import java.util.TreeSet;

import html2windows.dom.Element;

public class Style {
	Element element;
	Comparator<CSSRuleSet> comparator = new CssRuleSetComparator<CSSRuleSet>();
	TreeSet <CSSRuleSet> set = new TreeSet<CSSRuleSet>(comparator);
	
	/*
	 * �غc�l
	 * �إ�style�å[�Jinline property��CSSRuleSet
	 * arg1 parent node
	 */
	public Style(Element element){
		this.element=element;
		CSSRuleSet ruleSet=new CSSRuleSet(5);
		set.add(ruleSet);
	}
	
	/*
	 * �]css rule set��property
	 * inline�]�wproperty 
	 * arg1 priority�W�� 
	 * arg2 priority value
	 * arg3 CSSRuleSet��priority
	 */
    public void setProperty(String propertyName, String value){
        //ruleSetList.get(0).setProperty(propertyName,value);
        set.first().setProperty(propertyName,value);
    }
    
    /*
     * ���oproperty
     * �̷�arrayList������(property)���oproperty����
     * arg1 property���W��
     * return property value
     */
    public String getProperty(String propertyName){
    	
    	String value=null;
    	for(CSSRuleSet ruleSet : set){
    		value=ruleSet.getProperty(propertyName);
    		if(value!=null)
    			return value;
    	}
    	//�䤣��property value
        return null;
    }
  
    /*
     * �[�JCss ruleset
     * ��s��ruleset �[�Jcss ruleset��arraylist
     * arg1 ruleset
     * arg2 ruleset��priority
     */
    public void addCSSRuleSet(CSSRuleSet cssRuleSet){
    	//��Ū��άOpriority�̤p���ɭԥ[�J
    	set.add(cssRuleSet);
    }
    
    /*
     * ���oparent node
     * return prarent node
     */
    public Element getElement(){
        return element;
    }
    
    public class CssRuleSetComparator<T> implements Comparator<T>{  
		@Override
		public int compare(T o1, T o2) {
			return ((Comparable<T>) o1).compareTo(o2);
		}  
	}
}
