package html2windows.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

class TextInter extends Text implements NodeInter {

	private NodeList childNodeList = new NodeList();
	private Node parentNode = null;
	private String dataValue = null;
	private Document ownerDocument=null;

	/*
	 * 目的:constructor,設定此Text的dataValue
	 * 
	 * 參數:data=>要給予此Text的Data
	 * */
	public TextInter(String data) {
		dataValue = data;
	}

	/*
	 * 目的:將TextData以offset為分界,切割成兩個新的Text並將其接回parent的ChildNodeList,使其二成為sibling
	 * 
	 * 方法:
	 * 1.先檢查offset是否 大於零 或 在dataValue範圍內 若否則throw DOMException
	 * 2.New兩個Text newText1及newText2
	 * 3.以offset為分界將Text切割並存入兩個新的Text裡面
	 * 4.找出parent的ChildNodeList先將舊的Text移除 最後加入兩個新的Text
	 * 
	 * 參數:offset=>要從多少偏移量處開始切割,從0開始算
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

	// 目的:回傳此Text的dataValue
	public String data() {
		return dataValue;
	}

	/*
	 * 目的:將此Text的dataValue設置為參數的data
	 * 
	 * 參數:data=>要重新給予此Text的字串
	 * 
	 * */
	public void setData(String data) {
		dataValue = data;
	}

	// 目的:回傳此Text的dataValue的長度
	public long length() {
		return dataValue.length();
	}

	/* 
	 * 目的:擷取此dataValue的部份片段,傳入兩個參數: offset:以此為start position count:要擷取的數目
	 * 回傳即為以start position為基準,往後算count個長度的字串
	 * 
	 * 參數:
	 * offset=>Start offset of substring to extract
	 * count=>The number to extract.
	 */

	public String substringData(long offset, long count) throws DOMException {
		return dataValue.substring((int) offset, (int) offset + (int) count);
	}

	/* 
	 * 目的:將參數字串arg串接到dataValue後
	 * 
	 * 參數:arg=>要append的字串
	 * 
	 * */
	public void appendData(String arg) throws DOMException {
		dataValue.concat("arg");
	}

	/*
	 * 目的:將參數字串arg串接到dataValue中的offset處
	 * 
	 * 參數:
	 * offset => The character offset at which to insert
	 * arg => The String to insert
	 * */
	public void insertData(long offset, String arg) throws DOMException {
		String newData = null;
		newData.concat(dataValue.substring(0, (int) offset));
		newData.concat(arg);
		newData.concat(dataValue.substring((int) offset + 1));
		dataValue = newData;
	}

	/*
	 * 目的:刪除dataValue裡面部份內容 offset:以此為start position count:要刪除的數目
	 * 結果即為以offset為基準,往後刪除長度為count的內容
	 * 
	 * 參數:
	 * offset => The offset from which to start removing
	 * count => The number to delete. If the sum of offset and count exceeds length then all from offset to the end of the data are deleted.
	 * 
	 */
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

	/*
	 * 目的:以新字串arg取代dataValue中以offset為基準長度為count的部份
	 * 
	 * 參數:
	 * offset => The offset from which to start replacing
	 * count => The number to replace
	 * arg => The String with which the range must be replaced.
	 * 
	 * */
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
		int size = childNodeList.size();
		if (size > 0)
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
