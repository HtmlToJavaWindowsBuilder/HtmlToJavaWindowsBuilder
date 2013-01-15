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
    public void test() throws Exception{
        UIParser UItest = new UIParser();
        String input = "<company><employ>kobe</employ></company>";
        
        /*
        File input = new File("/home/bee040811/workspace/HtmlToJavaWindowsBuilder/src/html2windows/dom/Stocks.xml");
        */
        
        Document doc = UItest.parse(input);
        assertNotNull(doc);
        
        
        Element company = doc.documentElement();
        assertNotNull(company);
        assertEquals("tagName of root element should be <company>", "company", company.tagName());

        NodeList list = company.childNodes();
        assertEquals("list length should be one",list.length(),1);

        Node employ = list.item(0);
        assertEquals("first child of <company> should be <employ>", "employ", employ.nodeName());
        
        NodeList employChild = employ.childNodes();
        assertEquals("list length should be one",employChild.length(),1);
        Node kobe = employChild.item(0);
        assertEquals("type of first child of <employ> should be TEXT_NODE", Text.TEXT_NODE, kobe.nodeType());
        assertEquals("value of first child of <employ> should be kobe", "kobe", kobe.nodeValue());
    }
    @Test
    public void test2(){
        assert(true);
        
    }
}

