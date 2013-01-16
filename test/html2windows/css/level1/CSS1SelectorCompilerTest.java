package html2windows.css.level1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashMap;

public class CSS1SelectorCompilerTest{
	CSS1SelectorCompiler compiler;
	
	@Before
	public void before(){
		compiler = new CSS1SelectorCompiler();
	}
	
	@Test
	public void testNull(){
		assertNull("Null string should return null", compiler.compile(null));
	}

	@Test
	public void testEmpty(){
		assertNull("Empty string should return null", compiler.compile(""));
	}
	
	@Test
	public void testTypeSelector(){
		assertTrue("'div' should return TypeSelector", compiler.compile("div") instanceof TypeSelector);
	}
	
	@Test
	public void testClassSelector(){
		assertTrue("'.class' should return ClassSelector", compiler.compile(".class") instanceof ClassSelector);
	}
	
	@Test
	public void testIDSelector(){
		assertTrue("'#id' should return IDSelector", compiler.compile("#id") instanceof IDSelector);
	}
	
	@Test
	public void testTypeAndClassSelector(){
		Selector selector = compiler.compile("div.class");
		assertTrue("'.class' should return ClassSelector", selector instanceof ClassSelector);
		assertTrue("'div' should return TypeSelector", selector.prev() instanceof TypeSelector);
	}
	
	@Test
	public void testTypeAndIDAndClassSelector(){
		Selector selector = compiler.compile("div#id.class");
		assertTrue("'.class' should return ClassSelector", selector instanceof ClassSelector);
		assertTrue("'#id' should return IDSelector", selector.prev() instanceof IDSelector);
		assertTrue("'div' should return TypeSelector", selector.prev().prev() instanceof TypeSelector);
	}
	
	@Test
	public void testDescendantSelector(){
		Selector selector = compiler.compile("div div");
		assertTrue("'div' should return TypeSelector", selector instanceof TypeSelector);
		assertTrue("' ' should return DescendantSelector", selector.prev() instanceof DescendantSelector);
		assertTrue("'div' should return TypeSelector", selector.prev().prev() instanceof TypeSelector);
	}
	
	@Test
	public void testDoubleDescendantSelector(){
		Selector selector = compiler.compile("#id div .class");
		assertTrue("'.class' should return ClassSelector", selector instanceof ClassSelector);
		assertTrue("' ' should return DescendantSelector", selector.prev() instanceof DescendantSelector);
		assertTrue("'div' should return TypeSelector", selector.prev().prev() instanceof TypeSelector);
		assertTrue("' ' should return DescendantSelector", selector.prev().prev().prev() instanceof DescendantSelector);
		assertTrue("'#id' should return IDSelector", selector.prev().prev().prev().prev() instanceof IDSelector);
	}
}
