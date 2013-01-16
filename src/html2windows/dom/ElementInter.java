package html2windows.dom;

import html2windows.css.Style;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

/** This is the class that implements all abstract method in Element and the Interface NodeInter.
 *  All method is defined in http://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-745549614
 *  If you want to see detail how this function work.
 *  See above website.
 * 
 *  @author CFWei
 */
@SuppressWarnings(value = { "serial" })
public class ElementInter extends Element implements NodeInter {

    private ArrayList<AttrInter> attributeList = new ArrayList<AttrInter>();
    private NodeList childNodeList = new NodeList();
    private String tagNameValue = null;
    private Node parentNode=null;
    private Document ownerDocument;
    private Style elementStyle;
    
    /**
     * This is one of the constructor of this class.
     * You must input one parameter tagName to set this element's tagName.
     * And it will also new its style automatically.
     * 
     * @param tagName this element's tagName
     */
    public ElementInter(String tagName) {
    	 tagNameValue = tagName;
    	 setStyle(null);
    }
    
    /**
     * This is one of the constructor of this class.
     * You must input two parameter.
     * One is this Element's tagName.
     * One is this Element's style.
     * And the constructor will set this two for you.
     * 
     * @param tagName this element's tagName
     * @param style this element's style
     */
    public ElementInter(String tagName,Style style) {
        tagNameValue = tagName;
        setStyle(style);
    }
    
    /**
     * Get this element's tagName.
     * 
     * @return retrun this element's tagName
     * 
     */
    public String tagName() {
        return tagNameValue;
    }
    
    /**
     * Retrieves an attribute value by name.
     * @param name What attribute you want to get.
     * 
     * @return Attribute's value as a string. Null will be returned if this element doesn't have this attribute.
     */
    public String getAttribute(String name) {
        for (int i = 0; i < attributeList.size(); i++) {
            if (attributeList.get(i).name() == name)
                return attributeList.get(i).value();

        }
        return null;
    }
    
    /**
     * Set one attribute of this element.You send what attribute you want to set and its value.
     * @param name attribute
     * @param value attribute's value
     * 
     * @exception DOMException 
     */
    public void setAttribute(String name, String value) throws DOMException {
    	Document document=ownerDocument();
    	AttrInter newAttr=(AttrInter)document.createAttribute(name);
        newAttr.setValue(value);

        attributeList.add(newAttr);
    }

    /** 
     * Removes an attribute by name. 
     * 
     * @param The name of the attribute to remove.
     */
    public void removeAttribute(String name) throws DOMException {
        for (int i = 0; i < attributeList.size(); i++) {
            if (attributeList.get(i).name() == name) {
                attributeList.remove(i);
            }
        }

    }

    /**
     * Retrieves an attribute node by name.
     * 
     * @param name The name of the attribute to retrieve.
     * @return The Attr node with the specified name (nodeName) or null if there is no such attribute
     */
    public Attr getAttributeNode(String name) {
        for (int i = 0; i < attributeList.size(); i++) {
            Attr AttributeNode = attributeList.get(i);
            if (AttributeNode.name() == name)
                return AttributeNode;
        }
        return null;
    }



    public Attr setAttributeNode(Attr newAttr) throws DOMException {

        Attr returnAttr = null;
        String newAttrName = newAttr.name();
        for (int i = 0; i < attributeList.size(); i++) {
            Attr attributeNode = attributeList.get(i);
            if (attributeNode.name() == newAttrName) {
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
            if (attributeList.get(i).name() == name)
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
        return parentNode;
    }

    @Override
        public void setParentNode(Node newParent) {
            // TODO Auto-generated method stub
            parentNode=newParent;
        }


    @Override
        public NodeList childNodes() {
            // TODO Auto-generated method stub
            return childNodeList;
        }

    @Override
        public Node firstChild() {
            // TODO Auto-generated method stub
            if (childNodeList.length() > 0)
                return childNodeList.get(0);
            else
                return null;

        }

    @Override
    public Node lastChild() {
        int NodeListLength = (int) childNodeList.length();
        if (NodeListLength > 0)
            return childNodeList.get(NodeListLength - 1);
        else
            return null;
    }

    @Override
    public Node previousSibling() {
        // TODO Auto-generated method stub
        if(parentNode == null)
            return null;

        NodeList siblingList = parentNode.childNodes();
        int index = siblingList.indexOf(this);
        if (index == -1) {
            return siblingList.item(index - 1);
        } else {
            return null;
        }
    }

    @Override
    public Node nextSibling() {
        // TODO Auto-generated method stub

        if(parentNode == null)
            return null;

        NodeList siblingList = parentNode.childNodes();
        int index = siblingList.indexOf(this);
        if (index == siblingList.size() - 1) {
            return null;
        } else {
            return siblingList.item(index + 1);
        }
    }
    @Override
    public NamedNodeMap attributes() {
        // TODO Auto-generated method stub
        if(attributeList.size()==0)
            return null;

        NamedNodeMap returNamedNodeMap=new NamedNodeMap();
        for(int i=0;i<attributeList.size();i++)
            returNamedNodeMap.setNamedItem(attributeList.get(i));

        return returNamedNodeMap;

    }

    @Override
    public Document ownerDocument() {
        // TODO Auto-generated method stub
        return ownerDocument;

    }


    @Override
    public void setOwnerDocument(Document newOwnerDocument) {
        // TODO Auto-generated method stub
        ownerDocument=newOwnerDocument;


    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        // TODO Auto-generated method stub
        if (newChild == null)
            return null;
        else if (refChild == null) {
            childNodeList.add(newChild);
            return newChild;

        } else {
            int index = childNodeList.indexOf(refChild);
            childNodeList.add(index - 1, newChild);


        }
        return null;

    }
    
    @Override
    public Node replaceChild(Node newChilde, Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
        int index = childNodeList.indexOf(oldChild);
        if (index == -1) {
            return null;

        } else {
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
        if (childNodeList.size() == 0)
            return false;
        else
            return true;


    }

    @Override
    public boolean hasAttributes() {
        // TODO Auto-generated method stub
        if (attributeList.size() > 0)
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
