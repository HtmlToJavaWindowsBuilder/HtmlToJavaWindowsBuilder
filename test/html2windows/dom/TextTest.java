package html2windows.dom;
import java.io.File;

import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;
import html2windows.dom.TextInter;

public class TextTest{

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }
    @Test
    public void testNodeValue() throws Exception{
        Text text= new TextInter("abc");
        assertEquals("value of text should be abc", "abc", text.nodeValue());
    }
}

