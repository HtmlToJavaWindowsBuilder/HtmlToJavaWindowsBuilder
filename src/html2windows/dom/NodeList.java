package html2windows.dom;
import java.util.ArrayList;

public class NodeList extends ArrayList<Node>{
    public Node item(long index){
        return null;
    }
    
    public long length(){
        return modCount;
    }
}
