package html2windows.dom;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.w3c.dom.DOMException;

/**
 * This class are used to represent collections of nodes that can be accessed by name.
 * 
 * @author CFWei
 */
@SuppressWarnings("serial")
public class NamedNodeMap extends LinkedHashMap<String, Node> {

	/**
	 * Retrieves a node specified by name.
	 * 
	 * @param name The nodeName of a node to retrieve.
	 * @return A Node with the specified nodeName, or null if it does not identify any node in this map
	 */
	public Node getNamedItem(String name) {
		return get(name);
	}

	/**
	 * Adds a node using its nodeName attribute
	 * 
	 * @param arg A node to store in this map. 
	 * @return If the new Node replaces an existing node the replaced Node is returned, otherwise null is returned.
	 */
	public Node setNamedItem(Node arg) throws DOMException {
		Node returnNode = null;
		String nodeName = arg.nodeName();
		if (containsKey(nodeName))
			returnNode = get(nodeName);
		put(nodeName, arg);

		return returnNode;
	}

	/**
	 * Removes a node specified by name.
	 * 
	 * 
	 * @param name The nodeName of the node to remove.
	 * @return The node removed from this map if a node with such a name exists
	 */
	public Node removeNamedItem(String name) throws DOMException {
		if (containsKey(name) == false) {
			throw new DOMException(DOMException.NOT_FOUND_ERR,
					"There is no node named name in this map.");
		} else {
			Node returnNode = get(name);
			remove(name);
			return returnNode;
		}
	}
	
	/**
	 * Returns the indexth item in the map. 
	 * If index is greater than or equal to the number of nodes in this map, this returns null.
	 * 
	 * @param index Index into this map.
	 * @return The node at the indexth position in the map, or null if that is not a valid index.
	 */
	public Node item(long index) {
		Collection<Node> collection = values();
		Iterator<Node> iterator = collection.iterator();
		int i = 0;
		Node returnNode = null;
		while (iterator.hasNext()) {
			if (i == (int) index)
				returnNode = iterator.next();
			else
				iterator.next();
		}
		return returnNode;
	}

	/**
	 * The number of nodes in this map.
	 * The range of valid child node indices is 0 to length-1 inclusive.
	 * 
	 * @return The number of nodes in this map.
	 */
	public long length() {
		return size();
	}
}
