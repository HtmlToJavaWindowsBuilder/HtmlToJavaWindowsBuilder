package html2windows.dom;
import java.util.HashMap;

import org.w3c.dom.DOMException;

public class NamedNodeMap extends HashMap<String, Node> {
    public Node getNamedItem(String name){
        return get(name);
    }
    
    public Node setNamedItem(Node arg) throws DOMException{
    	Node returnNode=null;
    	String nodeName=arg.nodeName();
    	if(containsKey(nodeName))
    		returnNode=get(nodeName);
    	put(nodeName, arg);

    	return returnNode;
    }
    
    public Node removeNamedItem(String name) throws DOMException{
        if(containsKey(name)==false){
        	throw new DOMException(DOMException.NOT_FOUND_ERR, "There is no node named name in this map.");
        }
        else{
        	remove(name);
        	return get(name);
        }
    }
    
    public Node item(long index){
    	
        return null;
    }
    
    public long length(){
        return size();
    }
}
