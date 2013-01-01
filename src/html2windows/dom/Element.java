package html2windows.dom;
import javax.swing.JComponent;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


public abstract class Element extends JComponent implements Node{
    
	public abstract String tagName();
	
    public abstract String getAttribute(String name);
    
    public abstract void setAttribute(String name, String value) throws DOMException;
    
    public abstract void removeAttribute(String name) throws DOMException;
    
    public abstract Attr getAttributeNode(String name);
    
    public abstract Attr setAttributeNode(Attr newAttr) throws DOMException;
    
    public abstract Attr removeAttributeNode(Attr oldAttr) throws DOMException;
    
    public abstract NodeList getElementsByTagName(String name);
    
    public abstract boolean hasAttribute(String name);

    @Override
    public abstract String nodeName() ;

    @Override
    public abstract String nodeValue() ;

    @Override
    public abstract short nodeType() ;

    @Override
    public abstract Node parentNode() ;

    @Override
    public abstract NodeList childNodes() ;

    @Override
    public abstract Node firstChild() ;

    @Override
    public abstract Node lastChild() ;

    @Override
    public abstract Node previousSibling() ;

    @Override
    public abstract Node nextSibling() ;

    @Override
    public abstract NamedNodeMap attributes() ;

    @Override
    public abstract Document ownerDocument() ;

    @Override
    public abstract Node insertBefore(Node newChild, Node refChild) throws DOMException ;

    @Override
    public abstract Node replaceChild(Node newChilde, Node oldChild) throws DOMException ;

    @Override
    public abstract Node removeChild(Node oldChild) throws DOMException ;

    @Override
    public abstract Node appendChild(Node newChild) throws DOMException ;

    @Override
    public abstract boolean hasChildNodes() ;

    @Override
    public abstract boolean hasAttributes() ;

    @Override
    public abstract void addEventListener(String type, EventListener listener,
            boolean useCapture) ;

    @Override
    public abstract void removeEventListener(String type, EventListener listener,
            boolean useCapture) ;

    @Override
    public abstract boolean dispatchEvent(Event evt) throws EventException ;
}
