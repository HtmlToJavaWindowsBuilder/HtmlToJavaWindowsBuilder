package html2windows.dom;
import java.io.File;

import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;

public class DocumentTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }
    
    @Test
    public void testAppendChild() throws Exception{
        Document doc = new Document();
        Element body = doc.createElement("body");
        assertNotNull(body);
        
        doc.appendChild(body);
        assertNotNull(doc.getContentPane().getComponent(0));
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
}

