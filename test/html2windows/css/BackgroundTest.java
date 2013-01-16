package html2windows.dom;

import html2windows.css.BackgroundProperty;
import html2windows.css.Style;
import html2windows.dom.ElementInter;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Thread.sleep;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BackgroundTest {

	private JFrame frame = new JFrame();
	private JPanel panel=new JPanel();
	private Container c;
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Test
    public void test() throws Exception{
    	Graphics g = null;

    	BackgroundProperty background=new BackgroundProperty();
        
        ElementInter body=new ElementInter("body");
        Style bodyStyle = new Style(body);

        bodyStyle.setProperty("background-color","red");
        
        background.paint(bodyStyle, body, g);
        
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(360,360);
        frame.setLocationRelativeTo(null);
        frame.add(background);
        frame.setVisible(true);
        
        
        Thread.sleep(2000);
    }

}
