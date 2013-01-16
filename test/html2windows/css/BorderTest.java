package html2windows.dom;

import html2windows.css.Style;
import html2windows.dom.ElementInter;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Thread.sleep;
import java.awt.Container;
import java.awt.GridLayout;

public class BorderTest {
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

    	BorderProperty border=new BorderProperty();
        
    	ElementInter div=new ElementInter("div");
        Style borderStyle = new Style(div);
        
        borderStyle.setProperty("width","70");
        borderStyle.setProperty("height","70");
        borderStyle.setProperty("top","20");
        borderStyle.setProperty("left","20");
        borderStyle.setProperty("border-width","5");
        
        border.paint(borderStyle, div, g);
        
        JPanel panel=new JPanel();
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(360,360);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        panel.setLayout(new GridLayout(3,4));
        panel.add(border);
        
        frame.setVisible(true);
        Thread.sleep(2000);
    }
}
