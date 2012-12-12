package html2windows.dom;

interface NodeInter extends Node{
	public void setParentNode(Node newParent);
	public void setOwnerDocument(Document newOwnerDocument);
}
