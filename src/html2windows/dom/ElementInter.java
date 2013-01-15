package html2windows.dom;

import html2windows.css.Style;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

@SuppressWarnings(value = { "serial" })
public class ElementInter extends Element implements NodeInter {

    private ArrayList<AttrInter> attributeList = new ArrayList<AttrInter>();
    private String tagNameValue = null;
    private Document ownerDocument;
    private Style elementStyle;
    
    public ElementInter(String tagName) {
    	 tagNameValue = tagName;
    	 setStyle(null);
    }
    
    public ElementInter(String tagName,Style style) {
        tagNameValue = tagName;
        setStyle(style);
    }



    public String tagName() {
        return tagNameValue;
    }



    public String getAttribute(String name) {
        for (int i = 0; i < attributeList.size(); i++) {
            if (attributeList.get(i).name().equals(name))
                return attributeList.get(i).value();

        }
        return null;
    }


    public void setAttribute(String name, String value) throws DOMException {
    	Document document=ownerDocument();
    	AttrInter newAttr=(AttrInter)document.createAttribute(name);
		newAttr.setValue(value);
		int index=attributeList.indexOf(name);
    	if(index==-1)	
    		attributeList.add(newAttr);
    	else
    		attributeList.set(index, newAttr);

    		
    }



    public void removeAttribute(String name) throws DOMException {
        for (int i = 0; i < attributeList.size(); i++) {
            if (attributeList.get(i).name().equals(name)) {
                attributeList.remove(i);
            }
        }

    }



    public Attr getAttributeNode(String name) {
        for (int i = 0; i < attributeList.size(); i++) {
            Attr AttributeNode = attributeList.get(i);
            if (AttributeNode.name().equals(name))
                return AttributeNode;
        }
        return null;
    }



    public Attr setAttributeNode(Attr newAttr) throws DOMException {

        Attr returnAttr = null;
        String newAttrName = newAttr.name();
        for (int i = 0; i < attributeList.size(); i++) {
            Attr attributeNode = attributeList.get(i);
            if (attributeNode.name().equals(newAttrName)) {
                returnAttr = attributeNode;
                attributeList.remove(i);
                break;
            }
        }
        attributeList.add((AttrInter)newAttr);
        return returnAttr;

    }


    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        if(!attributeList.contains(oldAttr))
            throw new DOMException(DOMException.NOT_FOUND_ERR, "oldAttr is not an attribute of the element.");
        attributeList.remove(oldAttr);
        return oldAttr;
    }



    public NodeList getElementsByTagName(String name) {

        NodeList elementList = new NodeList();
        NodeList childNodeList=childNodes();
        
        for(int i=0;i<childNodeList.length();i++){
            Node child=childNodeList.get(i);
            if(child instanceof Element){
                NodeList childGetElementsByTagName=((ElementInter) child).getElementsByTagName(name);
                if(!childGetElementsByTagName.isEmpty())
                    elementList.addAll(childGetElementsByTagName);

                String childTagName=((ElementInter) child).tagName();
                if(childTagName.equals(name)||name.equals("*"))
                    elementList.add(child);
            }

        }
        return elementList;
    }


    public boolean hasAttribute(String name) {

        for (int i = 0; i < attributeList.size(); i++) {
            if (attributeList.get(i).name().equals(name))
                return true;
        }

        return false;
    }

    
	private void setStyle(Style style) {
		if(style==null){
			elementStyle=new Style(this);
		}
		else {
			elementStyle=style;
		}
		
	
	}


	public Style getStyle() {
		
		return elementStyle;
	}
    
    
    @Override
    public String nodeName() {
        
        return tagName();
    }

    @Override
    public String nodeValue() {
        
        return null;
    }

    @Override
    public short nodeType() {
        
        return ELEMENT_NODE;
    }

    @Override
    public Node parentNode() {
        
    	Component parent = getParent();
    	if(parent instanceof Node)
    		return (Node)parent;
    	else
    		return null;
    }

    @Override
    public void setParentNode(Node newParent){
    	Node oldParentNode = parentNode();
    	oldParentNode.removeChild(this);
 
    	newParent.appendChild(this);
    		
    }


    @Override
    public NodeList childNodes(){
    	Component[] components=getComponents();
    	NodeList childNodeList=new NodeList(); 
        for(Component eachComponent:components){
        	if(eachComponent instanceof Node)
        		childNodeList.add((Node)eachComponent);
        }
           
        return childNodeList;
           
    }

    @Override
    public Node firstChild(){
    	Component component=getComponent(0);
    	if(component instanceof Node)
    		return (Node)getComponent(0);
    	else
    		return null;

   }

    @Override
    public Node lastChild() {
    	int count=getComponentCount();
    	Component component = getComponent(count-1);
    	if(component instanceof Node)
    		return (Node)component;
    	else
    		return null;
    	
    }
    
    public int findIndexInParent(){
    	
    	Node parent = parentNode();
    	NodeList parentChildNodeList = parent.childNodes();
    	for(int i=0;i<parentChildNodeList.size();i++){
    		Node node = parentChildNodeList.item(i);
    		if(node.equals(this))
    			return i;
    	}
    	
    	return -1;
    }
    
    
    @Override
    public Node previousSibling() {
    	int thisIndex=findIndexInParent();
    	
    	if(thisIndex!=-1){
    		Component previousSiblingComponent = getComponent(thisIndex-1);
    		if(previousSiblingComponent instanceof Node)
    			return (Node)previousSiblingComponent;
    	}

    	return null;
    	
    }

    @Override
    public Node nextSibling() {
    	int thisIndex=findIndexInParent();
    	
    	if(thisIndex!=-1){
    		Component previousSiblingComponent = getComponent(thisIndex+1);
    		if(previousSiblingComponent instanceof Node)
    			return (Node)previousSiblingComponent;
    	}

    	return null;


    }
    @Override
    public NamedNodeMap attributes() {
        
        if(attributeList.size()==0)
            return null;

        NamedNodeMap returNamedNodeMap=new NamedNodeMap();
        for(int i=0;i<attributeList.size();i++)
            returNamedNodeMap.setNamedItem(attributeList.get(i));

        return returNamedNodeMap;

    }

    @Override
    public Document ownerDocument() {
        
        return ownerDocument;

    }


    @Override
    public void setOwnerDocument(Document newOwnerDocument) {
        
        ownerDocument=newOwnerDocument;


    }
    
    public int findChildIndex(Node refChild){
    	
    	Component refChildComponent=(Component)refChild;
    	
    	Component[] childComponentList=getComponents();
    	for(int i=0;i<childComponentList.length;i++){
    		Component eachComponent =childComponentList[0];
    		if(eachComponent.equals(childComponentList))
    			return i;
    		
    	}
    	
    	return -1;
    }
    
    
    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        int index=findChildIndex(refChild);
        if(index!=-1){
        	if(newChild instanceof Component){
        		add((Component)newChild, index);
        
        		return refChild;
        	}
        	
        	
        }

        return null;

    }
    
    @Override
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        
    	 int index=findChildIndex(oldChild);
         if(index!=-1){
         	if(newChild instanceof Component){
         		remove(index);
         		add((Component)newChild, index);
         		return oldChild;
         	}
         	
         	
         }

         return null;

    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        
        return null;

    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
    	if(newChild instanceof Component){
    		add((Component)newChild);
        	return newChild;
    	}
    	return null;

    }

    @Override
    public boolean hasChildNodes() {
        
    	int childCount=getComponentCount();
    	
        if (childCount== 0)
            return false;
        else
            return true;

    }

    @Override
    public boolean hasAttributes() {
        
        if (attributeList.size() > 0)
            return true;
        else
            return false;

    }
    
    
    
    
    @Override
    public void addEventListener(String type, EventListener listener,
            boolean useCapture) {
        


    }

    @Override
    public void removeEventListener(String type, EventListener listener,
            boolean useCapture) {
        
    }

    @Override
    public boolean dispatchEvent(Event evt) throws EventException {
        
        return false;

    }



	
}
