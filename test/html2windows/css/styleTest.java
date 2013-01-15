package html2windows.dom;
import java.io.File;

import html2windows.css.CSSRuleSet;
import html2windows.css.Style;
import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;

public class styleTest {
    private static final int priority = 3;
    private static ElementInter element;
    private static Style styleTemp;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        element = new ElementInter("test");
        styleTemp = new Style(element);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }
    
    @Test
    public void propertyTest() throws Exception{
        assert(true);
        
        CSSRuleSet ruleSet = new CSSRuleSet(priority);
        ruleSet.setProperty("margin-top", "30px");
        ruleSet.setProperty("position", "absolute");
        ruleSet.setProperty("float", "left");
        
        styleTemp.addCSSRuleSet(ruleSet);
        styleTemp.setProperty("background-color", "blue");
        styleTemp.setProperty("border-color", "red");
        styleTemp.setProperty("font-size", "30px");
        
        assertEquals("margin-top should be <30px>", "30px", styleTemp.getProperty("margin-top"));
        assertEquals("position should be <absolute>", "absolute", styleTemp.getProperty("position"));
        assertEquals("float should be <left>", "left", styleTemp.getProperty("float"));
        assertEquals("background-color should be <blue>", "blue", styleTemp.getProperty("background-color"));
        assertEquals("border-color should be <red>", "red", styleTemp.getProperty("border-color"));
        assertEquals("font-size should be <30px>", "30px", styleTemp.getProperty("font-size"));
        assertEquals("font-color should be <null>", null, styleTemp.getProperty("font-color"));
    }
    
    @Test
    public void elementTest() throws Exception{
        assert(true);
        
        assertEquals("getElement should be <element>", element, styleTemp.getElement());
    }
}
