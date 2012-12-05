package html2windows.dom;
import java.util.ArrayList;

public class NodeList extends ArrayList<Node>{
    public Node item(long index){
        return get((int)index);
    }
    
    public long length(){
        return size();
    }
}
