package html2windows.dom;
import java.io.File;

import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.DocumentFragmentInter;
import html2windows.dom.Element;
import html2windows.dom.ElementInter;
import html2windows.dom.TextInter;
import org.w3c.dom.DOMException;

public class DocumentTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    /**
     * test document's createElement and ownerDocument function
     * 
     * 
     */
    @Test 
    public void testCreateElement() throws Exception{
    	Document doc = new Document();
        Element body = doc.createElement("body");
        assertNotNull(body);
        assertEquals("body's owner should be document",doc,body.ownerDocument());
    }
    
    @Test
    public void testAppendChild() throws Exception{
        Document doc = new Document();
        Element body = doc.createElement("body");
        assertNotNull(body);
        
        doc.appendChild(body);
        assertNotNull(doc.getContentPane().getComponent(0));
    }
    
    @Test (expected=NullPointerException.class)
    public void testAppendNullChild(){
    	Document doc = new Document();
    	doc.appendChild(null);
    }
    
    @Test 
    public void testCreateTextNode() throws Exception{
        Document doc = new Document();
        Text content= doc.createTextNode("asdf");
        assertNotNull(content);
        assertEquals("value of content should be asdf", "asdf", content.data());
        assertEquals("value of content should be asdf", "asdf", content.nodeValue());
    }

    @Test
    public void testDocumentElement() throws Exception{
        Document doc = new Document();
        Element body = doc.createElement("body");
        assertNotNull(body);

        doc.appendChild(body);
        assertEquals("documentElement should be body", body, doc.documentElement());
    }
    
    @Test 
    public void testGetElementsByTagName() throws Exception{
        
    	//test when there is no element in document
    	Document doc = new Document();
        NodeList nodeList = new NodeList();
        assertEquals("get element by tag name should return nodeList when there is no element", nodeList, doc.getElementsByTagName("div"));
        
        //create body
        Element body = doc.createElement("body");
        assertNotNull(body);
        
        //append div to body
        ElementInter div=new ElementInter("div");
        ElementInter span=new ElementInter("span");
        body.appendChild(span);
        body.appendChild(div);
        body.appendChild(div);
        
        //append body to document
        doc.appendChild(body);
        
        //test whether nodeList with div equals get element with tag name
        nodeList.add(div);
        nodeList.add(div);
        assertEquals("get element by tag name should return nodelist with element", nodeList, doc.getElementsByTagName("div"));
    }
    
    @Test 
    public void testChildNodes() throws Exception{
        
    	//test when there is no element in document
    	Document doc = new Document();
        NodeList nodeList = new NodeList();
        assertEquals("childNodes should return nodeList when there is no child", nodeList, doc.childNodes());
        
        //create body
        Element body = doc.createElement("body");
        doc.appendChild(body);
        
        
        //test whether nodeList with div equals get element with tag name
        nodeList.add(body);
        assertEquals("childNodes should return nodeList when there is a child", nodeList, doc.childNodes());
    }
    
    @Test 
    public void testInsertBefore() throws Exception{
        
    	//test when there is no element in document
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.insertBefore(body,null);
        assertEquals("node should be document element when insert before null", body, doc.documentElement());
    }
    
    /**
     * test insert before child that is not in document
     * 
     * expect DOMException
     */
    @Test (expected=DOMException.class)
    public void testInsertChildToDocument() {
        
    	/**setup
    	 * create a document doc
    	 * create a element body and append it to doc
    	 * create a element div 
    	 */
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);
        Element div=doc.createElement("div");
        
        /**test
         * insert body before div that is not in document
         * 
         * expect DOMException
         */
        doc.insertBefore(div, body);
    }
    
    /**
     * test insert new child before null
     * 
     * expect NullPointerException
     */
    @Test (expected=NullPointerException.class)
    public void testInsertNullBefore() {
        
    	/**setup
    	 * create a document doc
    	 * create a element body and append it to document
    	 */
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);
        
        /**test
         * insert body before null
         * 
         * expect NullPointerException
         */
        doc.insertBefore(null, body);
    }
    
    /**
     * test replaceChild
     * 
     * expect old child replaced by new child
     */
    @Test
    public void testReplaceChild(){
    	
    	/**setup
    	 * create a document doc
    	 * create a body element body and append it to document
    	 * create a div element
    	 */
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);
        Element div = doc.createElement("div");
        
        /**action
         * replace old child body by new child div
         */
        doc.replaceChild(div, body);
        
        /**test
         * document's first child should be new child div
         */
        assertEquals("document's firstChild should be div",div,doc.firstChild());
    }
    
    /**
     * test replace null child by null child
     * 
     * expect NullPointerException
     */
    @Test (expected=NullPointerException.class)
    public void testReplaceChildByNull(){
    	
    	/**setup
    	 * create a document doc
    	 * create a body element and append it to document
    	 */
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);

        /**test
         * replace body by null
         * 
         * expect NullPointerException
         */
        doc.replaceChild(null, body);
    }
    
    /**
     * test document replaceChild element when old child parameter is null
     * 
     * expect DOMException
     */
    @Test (expected=DOMException.class)
    public void testReplaceNullChild(){
    	
    	/**setup
    	 * create a document doc
    	 * create a element body and append it to document
    	 * create a element div and append it to body
    	 */
    	Document doc = new Document();
        Element body = doc.createElement("body");
        Element div = doc.createElement("div");
        body.appendChild(div);
        doc.appendChild(body);
        
        /**test
         * replace null child in document
         * 
         * expect DOMException
         */
        doc.replaceChild(div, null);
    }
    
    /**
     * test document's replaceChild element when child is not in document
     * 
     * expect DOMException
     */
    @Test (expected=DOMException.class)
    public void testReplaceChildNotExist(){
    	
    	/**setup
    	 * create a body element and append it to document
    	 * create a element div
    	 * create a element span
    	 */
    	Document doc = new Document();
    	Element body = doc.createElement("body");
        doc.appendChild(body);
        Element div = doc.createElement("div");
        Element span = doc.createElement("span");
        
        /**test
         * replace element when element is not in document
         * 
         * expect DOMException when replace element by iteself
         */
        doc.replaceChild(div, span);
    }
}

