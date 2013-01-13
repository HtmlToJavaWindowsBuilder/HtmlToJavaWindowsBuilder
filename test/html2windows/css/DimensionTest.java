package html2windows.dom;
import java.io.File;

import html2windows.css.DimensionProperty;
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
import java.awt.GridLayout;
import java.awt.FlowLayout;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class DimensionTest { 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    @Test
    public void test() throws Exception{

        DimensionProperty font = new DimensionProperty("family");

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
        fontType.setProperty("width","30");
        fontType.setProperty("height","100");
        fontType.setProperty("background-color","orange");

        font.paint(fontType, fontNode, g);
        JPanel panel = new JPanel();

        panel.add(font);
        panel.setLayout(new GridLayout(3,4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
        //font.setText("asdf");

    }

    @Test
    public void test2(){
        assert(true);
    }
}
