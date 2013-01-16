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
public class CustomTest { 

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
         * setting Font Type
         * font-size    : 30
         * family       : Arial
         * font-weight  : bold
         * font-variant : small-caps
         * font-style   : italic
         *
         */
        style.setProperty("family","Arial");
        style.setProperty("font-size","12px");
        style.setProperty("font-weight","bold");
        style.setProperty("font-style","italic");
        style.setProperty("font-variant","small-caps");


        /**
         * Action
         * 
         * setting Block Type
         * width        : 200
         * height       : 100
         * left         : 100
         * top          : 100
         * position     : relative
         *
         */
        style.setProperty("width","200");
        style.setProperty("height","100");
        style.setProperty("left","100");
        style.setProperty("top","100");
        style.setProperty("position","relative");
        style.setProperty("text-decoration","line-through");
        style.setProperty("color","#182bbc");
        /**
         * Test
         * 
         * GUI Screen
         *
         */
        customPanel.add(elementNode);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(360,200);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
        
        
    }
}