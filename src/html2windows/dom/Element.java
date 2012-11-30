package html2windows.dom;
import java.util.HashMap;

import javax.swing.JComponent;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


public class Element extends JComponent implements Node{
    
	
	private HashMap<String, String> attribute=new HashMap<String, String>();
	private NodeList ChildNodeList=new NodeList();
	
	public String tagName(){
        return null;
    }
    
    public String getAttribute(String name){
    	if(attribute.containsKey(name)){
    		return attribute.get(name);
    	}
    	else{
    		return "";
    	}
    }
    
    public void setAttribute(String name, String value) throws DOMException{
    	attribute.put(name, value);
    }
    
    public void removeAttribute(String name) throws DOMException{
    	
    	attribute.remove(name);
    }
    
    public Attr getAttributeNode(String name){
        return null;
    }
    
    public Attr setAttributeNode(Attr newAttr) throws DOMException{
        return newAttr;
    }
    
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException{
        return oldAttr;
    }
    
    public NodeList getElementsByTagName(String name){
        return null;
    }
    
    public boolean hasAttribute(String name){
    	if(attribute.containsKey(name))
    		return true;
    	else
    		return false;
		
    }

    @Override
    public String nodeName() {
        // TODO Auto-generated method stub
        return attribute.get("tagname");
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
        return null;
    }

    @Override
    public NodeList childNodes() {
        // TODO Auto-generated method stub
        return ChildNodeList;
    	}

    @Override
    public Node firstChild() {
        // TODO Auto-generated method stub
    	if(ChildNodeList.length()>0)
    		return ChildNodeList.get(0);
    	else
    		return null;
    	
    }

    @Override
    public Node lastChild() {
        int NodeListLength=(int) ChildNodeList.length();
        if(NodeListLength>0)
        	return ChildNodeList.get(NodeListLength-1);
        else
        	return null;
    }

    @Override
    public Node previousSibling() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node nextSibling() {
        // TODO Auto-generated method stub
        return null;
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
    		ChildNodeList.add(newChild);
    		return newChild;
    	}
    	else{
    		
    		
    	}
        return null;
    }	

    @Override
    public Node replaceChild(Node newChilde, Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
        // TODO Auto-generated method stub
    	
    	ChildNodeList.add(newChild);
        return newChild;
    }

    @Override
    public boolean hasChildNodes() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAttributes() {
        // TODO Auto-generated method stub
    	if(attribute.size()>0)
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
