package html2windows.dom;
import java.io.File;

import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;

public class UIParserTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }
    @Test
    public void UIParser() throws Exception{
        UIParser uiParserTest = new UIParser();
        String input = "<html><head></head><body>test message</body></html>";
        
        
        //File input = new File("/home/cfwei-nb/workspace/HtmlToJavaWindowsBuilder/test/html2windows/dom/testfile/test.html");
        
        
        Document doc = uiParserTest.parse(input);
        assertNotNull(doc);
        
        
        Element html = doc.documentElement();
        assertNotNull(html);
        assertEquals("tagName of root element should be <html>", "html", html.tagName());
        
        NodeList list = html.childNodes();
        assertEquals("list length should be one",list.length(),2);

        Node head = list.item(0);
        assertEquals("first child of <html> should be <head>", "head", head.nodeName());
        
        Node body = list.item(1);
        assertEquals("second child of <html> should be <body>", "body", body.nodeName()); 
        
        NodeList bodyChildList = body.childNodes();
        assertEquals("list length should be one",bodyChildList.length(),1);
        
        Node bodyChild = bodyChildList.item(0);
        assertEquals("type of first child of <body> should be TEXT_NODE", Text.TEXT_NODE, bodyChild.nodeType());
        assertEquals("value of first child of <employ> should be test message", "test message", bodyChild.nodeValue());
    }
}

