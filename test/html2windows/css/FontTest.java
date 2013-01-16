package html2windows.css;

import html2windows.css.FontPainter;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Element;
import html2windows.dom.Document;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import java.io.File;

import java.lang.Thread

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

/**
 * test Font property, but GUI should be judged by user 
 *
 * @author  bee040811
 */
@SuppressWarnings(value = { "serial"  }) 
public class FontTest { 

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * Font GUI 
     * 
     * Expect Font Style equals Font setted Style
     */
    @Test
    public void FontGUITest() throws Exception{

        /**
         * Setup
         * 
         * Create a Jframe
         * Create a JPanel
         * Create a Document
         * 
         */
        JFrame frame = new JFrame("Font Frame");
        JPanel customPanel = new JPanel( new CustomLayoutManager());

        Document document = new Document(){
            @Override
                public CSSPainter getPainter(){
                    return new FontPainter();
                }
        };

        /**
         * Setup
         * 
         * Create a ElementInter
         * Create a Element
         * Create a Style
         * 
         */
        String tagName = "h1";
        ElementInter elementInter = new ElementInter(tagName);
        elementInter.setOwnerDocument(document);

        Element elementNode = elementInter;
        elementNode.setPreferredSize(new Dimension(100, 100));

        Style style = elementNode.getStyle();
            
        /**
         * Action
         * 
         * setting Font Type
         * font-size    : 30
         * family       : Arial
         * font-weight  : bold
         * font-variant : small-caps
         * font-style   : italic
         *
         */
        style.setProperty("family","Arial");
        style.setProperty("font-size","30");
        style.setProperty("font-weight","bold");
        style.setProperty("font-style","italic");
        style.setProperty("font-variant","small-caps");

        /**
         * Test
         * 
         * GUI Screen
         *
         */
        customPanel.add(elementNode);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
        Thread.sleep(300);
    }

    /**
     * Test Style set and get property
     * 
     * Expect set property equals get property
     */
    @Test 
    public void StyleTest() throws Exception{

        /**
         * Setup
         * 
         * Create an ElementInter
         * Create a Style 
         */
        String tagName = "h1";
        ElementInter elementInter = new ElementInter(tagName);
        Element elementNode = elementInter;
        Style style = elementNode.getStyle();

        /**
         * Action
         * 
         * setProperty and getProperty 
         */
        style.setProperty("family","Arial");
        String fontFamily = style.getProperty("family");

        /**
         * Test
         * 
         * family should be Arial
         */
        assertEquals("family should be Arial", "Arial", fontFamily );
    }
}
