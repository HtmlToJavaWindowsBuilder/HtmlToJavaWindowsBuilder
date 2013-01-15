package html2windows.dom;
import java.io.File;

import html2windows.css.CSSRuleSet;
import html2windows.dom.UIParser;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import html2windows.dom.Document;
import html2windows.dom.Element;

public class CSSRuleSetTest {
    private static final int priority = 3;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }
    
    @Test
    public void test() throws Exception{
        assert(true);
        
        CSSRuleSet ruleSetTest = new CSSRuleSet(priority);
        ruleSetTest.setProperty("background-color", "blue");
        ruleSetTest.setProperty("border-color", "red");
        ruleSetTest.setProperty("font-size", "30px");
        assertEquals("priority should be <3>", 3, ruleSetTest.getPriority());
        assertEquals("background-color should be <blue>", "blue", ruleSetTest.getProperty("background-color"));
        assertEquals("border-color should be <blue>", "blue", ruleSetTest.getProperty("background-color"));
        assertEquals("font-size should be <30px>", "30px", ruleSetTest.getProperty("font-size"));
        assertEquals("margin-top should be <null>", "null", ruleSetTest.getProperty("margin-top"));
        assertEquals("font-color should be <null>", "null", ruleSetTest.getProperty("font-color"));
    }
}
