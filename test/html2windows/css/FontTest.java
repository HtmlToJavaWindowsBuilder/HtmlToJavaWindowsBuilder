package html2windows.dom;
import java.io.File;

import html2windows.css.FontProperty;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Document;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class FontTest { 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Test
    public void test() throws Exception{

        FontProperty font = new FontProperty("family");

        JFrame frame = new JFrame("height");
        String tagName = "h1";
        ElementInter fontNode = new ElementInter(tagName);
        Style fontType = new Style(fontNode);
        Graphics g = null;
        fontType.setProperty("font-size","30");
        String fontSize = fontType.getProperty("font-size");
        assertEquals("font-size should be 30", "30", fontSize);

        fontType.setProperty("family","Arial");
        String fontFamily = fontType.getProperty("family");
        assertEquals("family should be Arial", "Arial", fontFamily );

        fontType.setProperty("font-weight","bold");
        fontType.setProperty("font-style","italic");
        fontType.setProperty("font-variant","small-caps");

        font.paint(fontType, fontNode, g);
        assertEquals("frame should have no window listener", 0, frame.getWindowListeners().length);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        font.setText("aaa");
        frame.add(font);
        //font.setText("asdf");
    }

    @Test
    public void test2(){
        assert(true);
        
    }
}
