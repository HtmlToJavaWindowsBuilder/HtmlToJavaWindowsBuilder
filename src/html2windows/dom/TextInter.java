package html2windows.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


/**
 * This is the class that implements all abstract method in Text and the Interface NodeInter.
 * All method is defined in http://www.w3.org/TR/DOM-Level-2-Core/core.html
 * If you want to see detail how this function work.
 * See above website.
 * 
 * @author cfwei
 */


class TextInter extends Text implements NodeInter {

	private NodeList childNodeList = new NodeList();
	private Node parentNode = null;
	private String dataValue = null;
	private Document ownerDocument=null;

	/**
	 * Constructor of this class
	 * 
	 * @param data The data you want to set to this text. 
	 */
	public TextInter(String data) {
		dataValue = data;
	}

	
	/**
	 * Breaks this node into two nodes at the specified offset.
	 * 
	 * @param offset The offset at which to split, starting from 0.
	 * @return The new node, of the same type as this node.
	 * @throws DOMException INDEX_SIZE_ERR: Raised if the specified offset is negative or greater than the length of data of this text.
	 */
	public Text splitText(long offset) throws DOMException {
		if (offset < 0 || offset >= dataValue.length())
			throw new DOMException(
					DOMException.INDEX_SIZE_ERR,
					"The specified offset is negative or greater than the number of 16-bit units in data");

		String newString1 = dataValue.substring(0, (int) offset);
		String newString2 = dataValue.substring((int) offset + 1);
		TextInter newTextNode = new TextInter(newString2);
		
		if (parentNode != null) {
			newTextNode.setParentNode(parentNode);
			NodeList parentsChildNodeList = parentNode.childNodes();
			int index = parentsChildNodeList.indexOf(this);
			parentsChildNodeList.add(index + 1, newTextNode);
		}
		return newTextNode;
	}

	
	/**
	 * Return the data value of this Text
	 * 
	 * @return The data value of this Text
	 */
	public String data() {
		return dataValue;
	}
	
	/**
	 * Set the data value of this text
	 * 
	 * @param data The data value you want to set.
	 */
	public void setData(String data) {
		dataValue = data;
	}
	
	/**
	 * Return the length of this data value.
	 * 
	 * @return The length of data value of this text.
	 */

	public long length() {
		return dataValue.length();
	}

	/**
	 * Extracts a range of data from the text node.
	 * 
	 * @param offset Start offset of substring to extract.
	 * @param count The number of to extract.
	 * 
	 * @return The specified substring. 
	 */
	public String substringData(long offset, long count) throws DOMException {
		return dataValue.substring((int) offset, (int) offset + (int) count);
	}
	
	/**
	 * Append the string to the end of the character data of the node.
	 * 
	 * 
	 * 
	 */
	public void appendData(String arg) throws DOMException {
		dataValue=dataValue.concat("arg");
	}

	public void insertData(long offset, String arg) throws DOMException {
		String newData = null;
		newData.concat(dataValue.substring(0, (int) offset));
		newData.concat(arg);
		newData.concat(dataValue.substring((int) offset + 1));
		dataValue = newData;
	}

	public void deleteData(long offset, long count) throws DOMException {
		if (offset < 0 || count < 0 || offset >= dataValue.length())
			throw new DOMException(
					DOMException.INDEX_SIZE_ERR,
					" The specified offset is negative or greater than the number of 16-bit units in data, or if the specified count is negative");
		String newData = null;
		newData.concat(dataValue.substring(0, (int) offset));
		newData.concat(dataValue.substring((int) offset + (int) count));
		dataValue = newData;
	}

	
	public void replaceData(long offset, long count, String arg)
			throws DOMException {
		if (offset < 0 || count < 0 || offset >= dataValue.length())
			throw new DOMException(
					DOMException.INDEX_SIZE_ERR,
					" The specified offset is negative or greater than the number of 16-bit units in data, or if the specified count is negative");

		String newData = null;
		newData.concat(dataValue.substring(0, (int) offset));
		newData.concat(arg);
		newData.concat(dataValue.substring((int) offset + (int) count));
		dataValue = newData;
	}
	
	
	@Override
	public String nodeName() {
		// TODO Auto-generated method stub
		return "#text";
	}

	@Override
	public String nodeValue() {
		// TODO Auto-generated method stub
		return dataValue;
	}

	@Override
	public short nodeType() {
		// TODO Auto-generated method stub
		return TEXT_NODE;
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
		if (childNodeList.size() > 0)
			return childNodeList.item(0);
		else
			return null;
	}

	@Override
	public Node lastChild() {
		// TODO Auto-generated method stub
		int nodeListSize = childNodeList.size();
		if (nodeListSize > 0)
			return childNodeList.item(nodeListSize - 1);
		else
			return null;
	}

	@Override
	public Node previousSibling() {
		// TODO Auto-generated method stubs
		if(parentNode==null)
			return null;
		
		NodeList siblingList = parentNode.childNodes();
		int index = siblingList.indexOf(this);
		if (index == 0)
			return null;

		else
			return siblingList.item(index - 1);

	}

	@Override
	public Node nextSibling() {
		// TODO Auto-generated method stub
		if(parentNode==null)
			return null;
		
		NodeList siblingList = parentNode.childNodes();
		int index = siblingList.indexOf(this);
		if (index == siblingList.size() - 1)
			return null;

		else
			return siblingList.item(index + 1);

	}

	@Override
	public NamedNodeMap attributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document ownerDocument() {
		// TODO Auto-generated method stub
		return this.ownerDocument;
	}

	@Override
	public void setOwnerDocument(Document newOwnerDocument) {
		// TODO Auto-generated method stub
		this.ownerDocument=newOwnerDocument;

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
			return newChild;
		}
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
		int index = childNodeList.indexOf(oldChild);
		if (index == -1)
			return null;
		else {
			childNodeList.remove(index);
			return oldChild;
		}
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
		
		if (!childNodeList.isEmpty())
			return true;
		else
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
