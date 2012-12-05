package html2windows.dom;
import javax.swing.JComponent;

import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;


public class Text extends JComponent implements Node {
	
	NodeList childNodeList=new NodeList();
	private String dataValue;
	
	public Text(String data){
		dataValue=data;
	}
	
	public String data(){
		
		return dataValue;
	}
    public Text splitText(long offset) throws DOMException{
        return null;
    }
    
    public void setData(String data){
    	dataValue=data;
    }

    public long length(){
        return dataValue.length();
    }
    
    public String substringData(long offset, long count) throws DOMException{
        return dataValue.substring((int)offset,(int)offset+(int)count);
    }
    
    public void appendData(String arg) throws DOMException{
    	dataValue.concat("arg");
    }
    
    public void insertData(long offset, String arg) throws DOMException{
    	String newData=null;
    	newData+=dataValue.substring(0, (int)offset);
    	newData+=arg;
    	newData+=dataValue.substring((int)offset+1);
    	dataValue=newData;
    }
    
    public void deleteData(long offset, long count) throws DOMException{
    	String newData=null;
    	newData.concat(dataValue.substring(0,(int)offset));
    	newData.concat(dataValue.substring((int)offset+(int)count));
    	dataValue=newData;
    }

    public void replaceData(long offset, long count, String arg) throws DOMException{
    	String newData=null;
    	newData.concat(dataValue.substring(0,(int)offset));
    	newData.concat(arg);
    	newData.concat(dataValue.substring((int)offset+(int)count));
    	dataValue=newData;
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
        return (Node)getParent();
    }

    @Override
    public NodeList childNodes() {
        // TODO Auto-generated method stub
        return childNodeList;
    }

    @Override
    public Node firstChild() {
        // TODO Auto-generated method stub
    	if(childNodeList.size()>0)
    		return childNodeList.item(0);
    	else
    		return null;
    }

    @Override
    public Node lastChild() {
        // TODO Auto-generated method stub
    	int nodeListSize=childNodeList.size();
    	if(nodeListSize>0)
    		return childNodeList.item(nodeListSize-1);
    	else
    		return null;
    }

    @Override
    public Node previousSibling() {
        // TODO Auto-generated method stub
    	NodeList siblingList=((Node)getParent()).childNodes();
    	int index=siblingList.indexOf(this);
    	if(index==0){
    		return null;
    	}
    	else{
    		return siblingList.item(index-1);
    	}

    }

    @Override
    public Node nextSibling() {
        // TODO Auto-generated method stub
    	NodeList siblingList=((Node)getParent()).childNodes();
    	int index=siblingList.indexOf(this);
    	if(index==siblingList.size()-1){
    		return null;
    	}
    	else{
    		return siblingList.item(index+1);
    	}
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
    		childNodeList.add(newChild);
    		return newChild;
    	}
    	else{
    		int index=childNodeList.indexOf(refChild);
    		childNodeList.add(index-1, newChild);
    		return newChild;
    	}
        
    }

    @Override
    public Node replaceChild(Node newChilde, Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
    	int index=childNodeList.indexOf(oldChild);
    	if(index==-1){
    		return null;
    	}
    	else{
    		childNodeList.remove(index);
    		childNodeList.add(index, newChilde);
    		return oldChild;
    	}
    }

    @Override
    public Node removeChild(Node oldChild) throws DOMException {
        // TODO Auto-generated method stub
    	int index=childNodeList.indexOf(oldChild);
    	if(index==-1)
    		return null;
    	else{
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
        return false;
    }

    @Override
    public boolean hasAttributes() {
        // TODO Auto-generated method stub
    	int size=childNodeList.size();
    	if(size>0)
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
