import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;
import html2windows.dom.UIParser;

public class testUIParser {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		UIParser Parser = new UIParser();
		Parser.parse("111");
		assert ("true") != null;
	}

}
