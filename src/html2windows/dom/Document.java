package html2windows.dom;
import javax.swing.JFrame;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

public class Document extends JFrame implements Node{
    public Element documentElement(){
        return null;
    }
    
    public Element createElement(String tagName) throws DOMException{
        return null;
    }
    
    public DocumentFragment createDocumentFragment(){
        return null;
    }
    
    public Text createTextNode(String data){
        return null;
    }
    
    public Attr createAttribute(String name) throws DOMException{
        return null;
    }
    
    public NodeList getElementsByTagName(String tagname){
        return null;
    }
    
    public Node importNode(Node importedNode, boolean deep) throws DOMException{
        return importedNode;
    }
    
    public Element getElementById(String elementId){
        return null;
    }

    @Override
    public String nodeName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String nodeValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public short nodeType() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Node parentNode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NodeList childNodes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node firstChild() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node lastChild() {
        // TODO Auto-generated method stub
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
        return null;
    }

    @Override
    public boolean hasChildNodes() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasAttributes() {
        // TODO Auto-generated method stub
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
