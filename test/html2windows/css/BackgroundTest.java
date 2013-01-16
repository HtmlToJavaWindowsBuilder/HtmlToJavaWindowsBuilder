package html2windows.css;

import html2windows.css.BackgroundPainter;
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
import static java.lang.Thread.sleep;


public class BackgroundTest {

	private JFrame frame = new JFrame();
	private JPanel panel=new JPanel();

	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    /**
     * Test custom usage of BorderPainter
     */
    @Test
    public void testCustomBorder(){
    	
    	/**
    	 * Setup
    	 * 
    	 * Create a JFrame frame and JPanel and create document
    	 * with defined getPainter() with BorderPainter(). Create
    	 * element with custom style setting and add to frame.  
    	 */
    	
    	//Create JFrame and JPanel 
    	JFrame frame = new JFrame("Button Frame");
        JPanel customPanel = new JPanel( new CustomLayoutManager());

        //Create document with BorderPainter()
        Document document = new Document(){
            @Override
            public CSSPainter getPainter(){
                return new BackgroundPainter();
            }
        };

        //Create element with defined test properties
        String tagName = "div";
        ElementInter elementInter = new ElementInter(tagName);
        elementInter.setOwnerDocument(document);

        Element elementNode = elementInter;
        elementNode.setPreferredSize(new Dimension(100, 100));

        Style style = elementNode.getStyle();
        style.setProperty("border-width","2");
        
        /**
         * Test
         * 
         * Add panel to Jframe and show
         * 
         * expect border with defined feature
         */
        customPanel.add(elementNode);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.add(customPanel);
        frame.setVisible(true);
        
        try{
        	Thread.sleep(2000);
        }catch(Exception e){}
    }

}
