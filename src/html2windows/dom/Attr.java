package html2windows.dom;


public interface Attr extends Node{
    public String name();
    
    public boolean specified();
    
    public String value();

    public void setValue(String value);
    
    public Element ownerElement();
}
