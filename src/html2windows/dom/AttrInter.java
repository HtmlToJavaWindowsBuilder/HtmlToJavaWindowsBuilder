package html2windows.dom;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

import java.util.List;
import java.util.ArrayList;

class AttrInter implements Attr, NodeInter{
	private String name;
	private boolean specified = false;
	private String value;
	
	private Document ownerDocument;
	
	private List<Node> childNodes = new ArrayList<Node>();
	
	public String name(){
		return name;
	}
	
	public boolean specified(){
		return specified;
	}

	public String value(){
		return value;
	}

	public AttrInter(String name){
		this.name = name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public Element ownerElement(){
		return null;
	}

	@Override
	public String nodeName() {
		return name();
	}

	@Override
	public String nodeValue() {
		return value();
	}

	@Override
	public short nodeType() {
		return ATTRIBUTE_NODE;
	}

	@Override
	public Node parentNode() {
		return null;
	}

	@Override
	public void setParentNode(Node newParent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeList childNodes() {
		return new NodeList();
	}

	@Override
	public Node firstChild() {
		return null;
	}

	@Override
	public Node lastChild() {
		return null;
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
		return ownerDocument;
	}

	@Override
	public void setOwnerDocument(Document newOwnerDocument) {
		ownerDocument = newOwnerDocument;
	}

	@Override
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		return null;
	}

	@Override
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		return null;
	}

	@Override
	public Node removeChild(Node oldChild) throws DOMException {
		return null;
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		return null;
	}

	@Override
	public boolean hasChildNodes() {
		return false;
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
}
