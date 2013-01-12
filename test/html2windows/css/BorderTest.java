package html2windows.dom;

import html2windows.css.BorderProperty;
import html2windows.css.Style;
import html2windows.dom.ElementInter;

import java.awt.Graphics;
import javax.swing.JFrame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Thread.sleep;

public class BorderTest {
	private JFrame frame = new JFrame();
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Test
    public void test() throws Exception{

    	BorderProperty border=new BorderProperty();
        ElementInter div=new ElementInter("div");
        
        Style borderStyle = new Style(div);
        borderStyle.setProperty("width","70");
        borderStyle.setProperty("height","70");
        borderStyle.setProperty("top","20");
        borderStyle.setProperty("left","20");
        borderStyle.setProperty("border-width","5");
        
        
        Graphics g = null;

        border.paint(borderStyle, div, g);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(360,360);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(border);
        Thread.sleep(2000);
    }
}
