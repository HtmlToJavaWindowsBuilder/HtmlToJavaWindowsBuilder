package html2windows.css.level1;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class CSS1SelectorCompilerTest{
	CSS1SelectorCompiler compiler;
	
	public CSS1SelectorCompilerTest(){
		compiler = new CSS1SelectorCompiler();
	}

	@Test
	public void compileTest(){
		
		assertNull(null, compiler.compile(""));
		
		HashMap<String, Class<?>[]> cases = new HashMap<String, Class<?>[]>();
		
		cases.put("div", new Class<?>[]{TypeSelector.class});
		cases.put(".class", new Class<?>[]{ClassSelector.class});
		cases.put("#id", new Class<?>[]{IDSelector.class});
		cases.put("div.class", new Class<?>[]{ClassSelector.class, TypeSelector.class});
		cases.put("div#id.class", new Class<?>[]{ClassSelector.class, IDSelector.class, TypeSelector.class});
		cases.put("div div", new Class<?>[]{TypeSelector.class, DescendantSelector.class, TypeSelector.class});
		
		for (String selectorText : cases.keySet()) {
			Selector selector = compiler.compile(selectorText);
			Class<?>[] classes = cases.get(selectorText);
			int i = classes.length;
			for (Class<?> klass : classes){
				assertNotNull("", selector);
				
				String errorMessage = 
					String.format("In case '%s', class of selector %d/%d should be '%s', however got '%s",
									selectorText, i, classes.length, klass, selector.getClass());
				assertTrue(errorMessage, klass.isInstance(selector));
				
				selector = selector.prev();
				i--;
			}
		}
	}
}
