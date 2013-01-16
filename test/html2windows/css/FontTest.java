package html2windows.css;
import java.io.File;

import html2windows.css.FontPainter;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Element;
import html2windows.dom.Document;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;

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

        JFrame frame = new JFrame("Button Frame");
        JPanel customPanel = new JPanel( new CustomLayoutManager());

        Document document = new Document(){
            @Override
            public CSSPainter getPainter(){
                return new FontPainter();
            }
        };

        String tagName = "h1";
        ElementInter elementInter = new ElementInter(tagName);
        elementInter.setOwnerDocument(document);

        Element elementNode = elementInter;
        elementNode.setPreferredSize(new Dimension(100, 100));

        Style style = elementNode.getStyle();


        style.setProperty("family","Arial");
        String fontFamily = style.getProperty("family");
        assertEquals("family should be Arial", "Arial", fontFamily );

        style.setProperty("font-size","30");
        style.setProperty("font-weight","bold");
        style.setProperty("font-style","italic");
        style.setProperty("font-variant","small-caps");
        JButton btn = new JButton("OK");

        //customPanel.add(btn);
        customPanel.add(elementNode);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
        while(true){}
    }

    @Test
    public void test2(){
        assert(true);
    }
}
