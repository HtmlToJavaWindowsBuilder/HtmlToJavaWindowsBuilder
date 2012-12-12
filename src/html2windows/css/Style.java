package html2windows.css;

import java.util.TreeSet;

import html2windows.dom.Element;

public class Style {
	Element element;
	//ArrayList<CSSRuleSet> ruleSetList=new ArrayList<CSSRuleSet>();
	TreeSet <CSSRuleSet> set = new TreeSet<CSSRuleSet>(CSSRuleSet.CssComparator);
	
	/*
	 * 建構子
	 * 建立style並加入inline property的CSSRuleSet
	 * arg1 parent node
	 */
	public Style(Element element){
		this.element=element;
		CSSRuleSet ruleSet=new CSSRuleSet(5);
		//ruleSetList.add(ruleSet);
		set.add(ruleSet);
	}
	
	/*
	 * 設css rule set的property
	 * inline設定property 
	 * arg1 priority名稱 
	 * arg2 priority value
	 * arg3 CSSRuleSet的priority
	 */
    public void setProperty(String propertyName, String value){
        //ruleSetList.get(0).setProperty(propertyName,value);
        set.first().setProperty(propertyName,value);
    }
    
    /*
     * 取得property
     * 依照arrayList的順序(property)取得property的值
     * arg1 property的名稱
     * return property value
     */
    public String getProperty(String propertyName){
    	
    	String value=null;
    	for(CSSRuleSet ruleSet : set){
    		value=ruleSet.getProperty(propertyName);
    		if(value!=null)
    			return value;
    	}
    	//找不到property value
        return null;
    }
  
    /*
     * 加入Css ruleset
     * 把新的ruleset 加入css ruleset的arraylist
     * arg1 ruleset
     * arg2 ruleset的priority
     */
    public void addCSSRuleSet(CSSRuleSet cssRuleSet){
    	//arrayList由大到小排序
    	//利用priority大小決定要插入到哪裡
    	/*for(int i=0;i<ruleSetList.size();i++){
    		if(ruleSetList.get(i).priority<=cssRuleSet.priority){
    			ruleSetList.add(i,cssRuleSet);
    			return;
    		}
    	}*/
    	//當空的或是priority最小的時候加入
    	set.add(cssRuleSet);
    }
    
    /*
     * 取得parent node
     * return prarent node
     */
    public Element getElement(){
        return element;
    }
}
