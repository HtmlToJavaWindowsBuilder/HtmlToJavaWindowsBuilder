package html2windows.dom;
import javax.swing.JFrame;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


/**
 * Document is top window.
 * The Document is not standard DOM Document. It removes some methods which
 * handle namespace and prefix.
 * 
 * One Document contains only one Node, whose type is Element.
 */
public class Document extends JFrame implements Node{
	private Element documentElement = null;
	private 

    public Element documentElement(){
        return documentElement;
    }
    
    public Element createElement(String tagName) throws DOMException{
    	// TODO
        return null;
    }
    
    public DocumentFragment createDocumentFragment(){
    	// TODO
        return null;
    }
    
    public Text createTextNode(String data){
    	// TODO
        return null;
    }
    
    public Attr createAttribute(String name) throws DOMException{
    	// TODO
        return null;
    }
    
    public NodeList getElementsByTagName(String tagname){
    	// TODO
        return null;
    }
    
    public Node importNode(Node importedNode, boolean deep) throws DOMException{
    	// TODO
        return importedNode;
    }
    
    public Element getElementById(String elementId){
    	// TODO
        return null;
    }

    @Override
    public String nodeName() {
        return "#document";
    }

    @Override
    public String nodeValue() {
        return null;
    }

    @Override
    public short nodeType() {
        return DOCUMENT_NODE;
    }

    @Override
    public Node parentNode() {
        return null;
    }

    @Override
    public NodeList childNodes() {
        return null;
    }
    
    @Override
    public Node firstChild() {
        return documentElement();
    }

    @Override
    public Node lastChild() {
        return documentElement();
    }

    @Override
    public Node previousSibling() {
        return null;
    }

    @Override
    public Node nextSibling() {
        return null;
    }

    @Override
    public NamedNodeMap attributes() {
        return null;
    }

    @Override
    public Document ownerDocument() {
        return null;
    }

    @Override
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
    	if(newChild.nodeType != ELEMENT_NODE){
    		throw new DOMException(	DOMException.HIERARCHY_REQUEST_ERR,
    								"Document only accepts Element Node");
    	}
    	else if(documentElement() != null){
    		throw new DOMException(	DOMException.HIERARCHY_REQUEST_ERR,
    								"The document already has one child element");
		}
		else if(newChild.ownerDocument != this){
    		throw new DOMException(	DOMException.WRONG_DOCUMENT_ERR,
    								"The element is not created by the document");
		}
		else if(refChild != null){
			/**
			 * If refChild equals documentElement, HIERARCHY_REQUEST_ERR already throwed before
			 */
    		throw new DOMException(	DOMException.NOT_FOUND_ERR,
    								"You never find reference child in a document");
		}
		
		add((Element)newChild);
		
        return newChild;
    }

    @Override
    public Node replaceChild(Node newChilde, Node oldChild) throws DOMException {
    	if(newChild.nodeType != ELEMENT_NODE){
    		throw new DOMException(	DOMException.HIERARCHY_REQUEST_ERR,
    								"Document only accepts Element Node");
    	}
		else if(newChild.ownerDocument != this){
    		throw new DOMException(	DOMException.WRONG_DOCUMENT_ERR,
    								"The element is not created by the document");
		}
		else if(oldChild != documentElement()){
    		throw new DOMException(	DOMException.NOT_FOUND_ERR,
    								documentElement() == null
    									? "The document is empty"
    									: "NOT_FOUND_ERR");
		}
		
		/* Remove the old one */
		remove(0);
		
		/* Append new one */
		add((Element)newChild);
		
        return newChild;
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
    	if(oldChild != documentElement){
    		throw new DOMException(	DOMException.NOT_FOUND_ERR,
    								"NOT_FOUND_ERR");
		}
		
		/* Remove the old one */
		remove(0);
		
        return null;
    }

    @Override
    public Node appendChild(Node newChild) throws DOMException {
    	if(newChild.nodeType != ELEMENT_NODE){
    		throw new DOMException(	DOMException.HIERARCHY_REQUEST_ERR,
    								"Document only accepts Element Node");
    	}
    	else if(documentElement() != null){
    		throw new DOMException(	DOMException.HIERARCHY_REQUEST_ERR,
    								"The document already has one child element");
		}
		else if(newChild.ownerDocument != this){
    		throw new DOMException(	DOMException.WRONG_DOCUMENT_ERR,
    								"The element is not created by the document");
		}
		
		add((Element)newChild);
		
        return newChild;
    }

    @Override
    public boolean hasChildNodes() {
        return documentElement() != null;
    }

    @Override
    public boolean hasAttributes() {
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
    
    /**
     * Override the method to make childNodes list live
     */
    @Override
    protected void addImpl(Component comp, Object constraints, int index){
    	super.addImpl(comp, constraints, index);
    }
    
    /**
     * Override the method to make childNodes list live
     */
    @Override
    public void remove(int index){
    	super.remove(index);
    }
}
