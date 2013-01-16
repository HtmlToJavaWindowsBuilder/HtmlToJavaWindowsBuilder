package html2windows.dom;
import java.util.ArrayList;

/**
 * This class provides the abstraction of an ordered collection of nodes
 *  without defining or constraining how this collection is implemente.
 * 
 * 
 * @author CFWei
 */
@SuppressWarnings(value = { "serial" })
public class NodeList extends ArrayList<Node>{
	
	/**
	 * Returns the indexth item in the collection. 
     * If index is greater than or equal to the number of nodes in the list, this returns null.
	 * 
	 * @param index Index into the collection.
	 * @return The node at the indexth position in the NodeList, or null if that is not a valid index.
	 */
    public Node item(long index){
        return get((int)index);
    }
    
    /** 
     * The number of nodes in the list.
     * @return Return the number of nodes in the list.
     */
    public long length(){
        return size();
    }
}
