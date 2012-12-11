package html2windows.css;

import java.util.ArrayList;

import html2windows.dom.Element;

public class Style {
	Element element;
	ArrayList<CSSRuleSet> ruleSetList=new ArrayList<CSSRuleSet>();
	
	/*
	 * �غc�l
	 * �إ�style�å[�Jinline property��CSSRuleSet
	 * arg1 parent node
	 */
	public Style(Element element){
		this.element=element;
		CSSRuleSet ruleSet=new CSSRuleSet(5);
		ruleSetList.add(ruleSet);
	}
	
	/*
	 * �]css rule set��property
	 * inline�]�wproperty 
	 * arg1 priority�W�� 
	 * arg2 priority value
	 * arg3 CSSRuleSet��priority
	 */
    public void setProperty(String propertyName, String value){
        ruleSetList.get(0).setProperty(propertyName,value);
    }
    
    /*
     * ���oproperty
     * �̷�arrayList������(property)���oproperty����
     * arg1 property���W��
     * return property value
     */
    public String getProperty(String propertyName){
    	
    	String value=null;
    	for(CSSRuleSet ruleSet : ruleSetList){
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
    public void addCSSRuleSet(CSSRuleSet cssRuleSet,int priority){
    	//arrayList�Ѥj��p�Ƨ�
    	//�Q��priority�j�p�M�w�n���J�����
    	for(int i=0;i<ruleSetList.size();i++){
    		if(ruleSetList.get(i).priority<=cssRuleSet.priority){
    			ruleSetList.add(i,cssRuleSet);
    			return;
    		}
    	}
    	//��Ū��άOpriority�̤p���ɭԥ[�J
    	ruleSetList.add(cssRuleSet);
    }
    
    /*
     * ���oparent node
     * return prarent node
     */
    public Element getElement(){
        return element;
    }
}
