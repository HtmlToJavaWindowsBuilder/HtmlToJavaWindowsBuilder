package html2windows.dom;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


public class Element extends JComponent implements Node{
    
	
	private ArrayList<Attr> attributeList=new ArrayList<>();
	private NodeList childNodeList=new NodeList();
	private String tagNameValue;
	
	public String tagName(){
		
		return tagNameValue;
	}
	
	
	public Element(String tagName){
		tagNameValue=tagName;
	}

    public String getAttribute(String name){
    	for(int i=0;i<attributeList.size();i++){
    		if(attributeList.get(i).name()==name){
    			
    			return attributeList.get(i).value();
    		}
    	}
    	
    	
    	return null;
    }
    
    public void setAttribute(String name, String value) throws DOMException{
    	Attr newAttr=new Attr(name);
    	newAttr.setValue(value);
    	attributeList.add(newAttr);
    	
    }
    
    public void removeAttribute(String name) throws DOMException{
    	for(int i=0;i<attributeList.size();i++){
    		if(attributeList.get(i).name()==name){
    			
    		}
    	}
    	
    }
    
    public Attr getAttributeNode(String name){
    	for(int i=0;i<attributeList.size();i++){
    		Attr AttributeNode=attributeList.get(i);
    		if(AttributeNode.name()==name)
    			return AttributeNode;
    	}
    	return null;
    }
    
    public Attr setAttributeNode(Attr newAttr) throws DOMException{

    	Attr returnAttr=null;
    	String newAttrName=newAttr.name();
    	for(int i=0;i<attributeList.size();i++){
    		Attr attributeNode=attributeList.get(i);
    		if(attributeNode.name()==newAttrName){
    			returnAttr=attributeNode;
    			attributeList.remove(i);
    			break;
    		}
    	}
    	attributeList.add(newAttr);
        return returnAttr;

    }
    
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException{
        return oldAttr;
    }
    
    public NodeList getElementsByTagName(String name){
    	
    	NodeList elementList=new NodeList();

        return elementList;
    }
    
    public boolean hasAttribute(String name){
    	
    	for(int i=0;i<attributeList.size();i++){
    		if(attributeList.get(i).name()==name)
    			return true;
    		
    	}
    	
    	return false;
    }

    @Override
    public String nodeName() {
        // TODO Auto-generated method stub
        return tagName();
    }

    @Override
    public String nodeValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public short nodeType() {
        // TODO Auto-generated method stub
        return ELEMENT_NODE;
    }

    @Override
    public Node parentNode() {
        // TODO Auto-generated method stub
    	if(getParent() instanceof Node){
    		return null;	
    	}
    	else{
        	return (Node)getParent();
        	
        }
    }

    @Override
    public NodeList childNodes() {
        // TODO Auto-generated method stub

        return childNodeList;
    	}

    @Override
    public Node firstChild() {
        // TODO Auto-generated method stub
    	if(childNodeList.length()>0)
    		return childNodeList.get(0);
    	else
    		return null;
    	
    }

    @Override
    public Node lastChild() {
        int NodeListLength=(int) childNodeList.length();
        if(NodeListLength>0)
        	return childNodeList.get(NodeListLength-1);
        else
        	return null;
    }

    @Override
    public Node previousSibling() {
        // TODO Auto-generated method stub
    	NodeList siblingList=((Node)getParent()).childNodes();
    	
    	int index=siblingList.indexOf(this);
    	if(index==-1){
    		return siblingList.item(index-1);
    	}
    	else{
    		return null;
    	}
    }

    @Override
    public Node nextSibling() {
        // TODO Auto-generated method stub
    	NodeList siblingList=((Node)getParent()).childNodes();
    	int index=siblingList.indexOf(this);
    	if(index==siblingList.size()-1){
    		return null;
    	}
    	else{
    		return siblingList.item(index+1);
    	}
    }

    @Override
    public NamedNodeMap attributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document ownerDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        // TODO Auto-generated method stub
    	if(newChild==null)
    		return null;
    	else if(refChild==null){
    		childNodeList.add(newChild);
    		return newChild;
    	}
    	else{
    		int index=childNodeList.indexOf(refChild);
    		childNodeList.add(index-1, newChild);
    		
    	}
        return null;
    }	

    @Override
    public Node replaceChild(Node newChilde, Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
    	int index=childNodeList.indexOf(oldChild);
    	if(index==-1){
    		return null;
    	}
    	else{
    		
    		childNodeList.remove(index);
    		childNodeList.add(index, newChilde);
    		return oldChild;
    	}
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        // TODO Auto-generated method stub
    	
    	childNodeList.add(newChild);
        return newChild;
    }

    @Override
    public boolean hasChildNodes() {
        // TODO Auto-generated method stub
    	if(childNodeList.size()==0)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    @Override
    public boolean hasAttributes() {
        // TODO Auto-generated method stub
    	if(attributeList.size()>0)
    		return true;
    	else
    		return false;
    }

    @Override
    public void addEventListener(String type, EventListener listener,
            boolean useCapture) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeEventListener(String type, EventListener listener,
            boolean useCapture) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean dispatchEvent(Event evt) throws EventException {
        // TODO Auto-generated method stub
        return false;
    }
}
