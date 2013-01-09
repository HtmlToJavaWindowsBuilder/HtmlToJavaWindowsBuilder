import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import html2windows.css.TextProperty;
import html2windows.dom.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.DOMException;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

public class TextPropertyTest {
	TextProperty textproperty;
	JFrame jFrame;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		textproperty=new TextProperty();
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		initUl();
		
		
	}

	
	public void initUl(){
		jFrame=new JFrame();
		
		jFrame.setSize(400	,400);
		jFrame.setTitle("hello");
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jFrame.add(textproperty);
		jFrame.setVisible(true);
		
		
		jFrame.repaint();
		
		
		
		
	}
}
