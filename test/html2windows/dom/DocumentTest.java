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
    public void testAppendNullChild() throws Exception{
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
        Node node=body.appendChild(div);
        
        //append body to document
        doc.appendChild(body);
        
        //test whether nodeList with div equals get element with tag name
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
    
    @Test (expected=DOMException.class)
    public void testInsertChildToDocument() throws Exception{
        
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);
        
        Element div=doc.createElement("div");
        doc.insertBefore(div, body);
    }
    
    @Test (expected=NullPointerException.class)
    public void testInsertNullBefore() throws Exception{
        
    	Document doc = new Document();
        Element body = doc.createElement("body");
        doc.appendChild(body);
        
        doc.insertBefore(null, body);
    }
}

