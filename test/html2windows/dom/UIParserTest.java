package html2windows.dom;
import java.io.File;

import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;

/**
 * Test the method in UIParser
 * 
 * @author	CFWei
 *
 */


public class UIParserTest {
	
    /**
     * Test parsing string by UIParser
     * 
     */
    @Test
    public void testParseString() {
    	/**	Create a UIParser and string to test
    	 * 
    	 */
        UIParser uiParserTest = new UIParser();
        String input = "<html><head></head><body>test message</body></html>";
        
        /**Use UIParser to parse string and test test that it is not null.
         * 
         * 
         */
        Document doc = uiParserTest.parse(input);
        assertNotNull(doc);
        
        /**Use doc to get documentElement and test that it is not null.
         * 
         */
        Element html = doc.documentElement();
        assertNotNull(html);
        
        /** Test whether the tag Name is correct
         * 
         */
        assertEquals("tagName of root element should be <html>", "html", html.tagName());
        
        /**Get the child list of documentElement and test whether the child count correct 
         * 
         */
        NodeList list = html.childNodes();
        assertEquals("list length should be one",list.length(),2);
        
        /**Get the first child and test whether the node name is correct.
         * 
         */
        Node head = list.item(0);
        assertEquals("first child of <html> should be <head>", "head", head.nodeName());
        
        /**Get the second child and test whether the node name is correct.
         * 
         */
        Node body = list.item(1);
        assertEquals("second child of <html> should be <body>", "body", body.nodeName()); 
        
        /**Get the childlist of second component and test whether the count of list is correct.
         * 
         */
        NodeList bodyChildList = body.childNodes();
        assertEquals("list length should be one",bodyChildList.length(),1);
        
        /**Get the grandchild of second child and test whether the nodeType and node Value are correct. 
         * 
         */
        Node bodyChild = bodyChildList.item(0);
        assertEquals("type of first child of <body> should be TEXT_NODE", Text.TEXT_NODE, bodyChild.nodeType());
        assertEquals("value of first child of <employ> should be test message", "test message", bodyChild.nodeValue());
    }
    
    
    
    
    /**
     * Test parsing file by UIParser
     * 
     * 
     */
    @Test
    public void testParseFile(){
    	
    	
    	/**	Create a UIParser and set the file path
    	 * 
    	 */
    	UIParser uiParserTest = new UIParser();
    	File input = new File("test-file/test.html");
    	
        /**Use UIParser to parse string and test test that it is not null.
         * 
         * 
         */
        Document doc = uiParserTest.parse(input);
        assertNotNull(doc);
        
        /**Use doc to get documentElement and test that it is not null.
         * 
         */
        Element html = doc.documentElement();
        assertNotNull(html);
        
        /** Test whether the tag Name is correct
         * 
         */
        assertEquals("tagName of root element should be <html>", "html", html.tagName());
        
        /**Get the child list of documentElement and test whether the child count correct 
         * 
         */
        NodeList list = html.childNodes();
        assertEquals("list length should be one",list.length(),2);
        
        /**Get the first child and test whether the node name is correct.
         * 
         */
        Node head = list.item(0);
        assertEquals("first child of <html> should be <head>", "head", head.nodeName());
        
        /**Get the second child and test whether the node name is correct.
         * 
         */
        Node body = list.item(1);
        assertEquals("second child of <html> should be <body>", "body", body.nodeName()); 
        
        /**Get the childlist of second component and test whether the count of list is correct.
         * 
         */
        NodeList bodyChildList = body.childNodes();
        assertEquals("list length should be one",bodyChildList.length(),1);
        
        
        /**Get the grandchild of second child and test whether the nodeType and node Value are correct. 
         * 
         */
        Node bodyChild = bodyChildList.item(0);
        assertEquals("type of first child of <body> should be TEXT_NODE", Text.TEXT_NODE, bodyChild.nodeType());
        assertEquals("value of first child of <employ> should be test message", "test message", bodyChild.nodeValue());
    }
    
    
   
    
    
}

