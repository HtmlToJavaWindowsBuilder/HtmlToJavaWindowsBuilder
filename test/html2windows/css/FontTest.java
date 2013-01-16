package html2windows.css;
import java.io.File;

import html2windows.css.FontPainter;
import html2windows.css.Style;
import html2windows.dom.ElementInter;
import html2windows.dom.Element;
import html2windows.dom.Document;
import html2windows.dom.Text;

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

/**
 * test Text position, but GUI should be judged by user 
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
     * Position GUI 
     * 
     * Expect position is correct position
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
        JFrame frame = new JFrame("Custom Frame");
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
         * Create a Text and append it to element
         */
        String tagName = "h1";
        ElementInter elementInter = new ElementInter(tagName);
        elementInter.setOwnerDocument(document);

        Element elementNode = elementInter;
        //elementNode.setPreferredSize(new Dimension(100, 100));
        
        Text textValueText=document.createTextNode("222222222");
        elementNode.appendChild(textValueText);
        
        Style style = elementNode.getStyle();
            

        /**
         * Action
         * 
         * setting Font Type :  test Case
         * font-size    : 30
         * family       : Arial , Book Corsiva , 21111
         * font-weight  : bold  , 11
         * font-variant : small-caps
         * font-style   : italic    
         * color        : #182bbc , #111111 , black
         */
        style.setProperty("family","Arial");

        style.setProperty("font-size","12px");
        style.setProperty("font-weight","bold");
        style.setProperty("font-style","italic");
        style.setProperty("font-variant","small-caps");
        style.setProperty("text-decoration","line-through");
        style.setProperty("color","#ff7b00");
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
        
        
    }
}
