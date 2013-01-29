import html2windows.css.CSSParser;
import html2windows.css.FontPainter;
import html2windows.dom.Document;
import html2windows.dom.UIParser;
import html2windows.css.CSS2Painter;
import html2windows.css.CustomLayoutManager;
import html2windows.css.DocumentAtRuleHandler;

import javax.swing.JFrame;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class simple {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Document document = new UIParser().parse(new File("ui.html"));
        document.setPainter(new CSS2Painter());
        document.setAtRuleHandler("document", new DocumentAtRuleHandler());
        
        try{
		    FileInputStream fin = new FileInputStream("style.css");
		    FileChannel fch = fin.getChannel();

		    // map the contents of the file into ByteBuffer
		    ByteBuffer byteBuff = fch.map(FileChannel.MapMode.READ_ONLY, 0, fch.size());

		    // convert ByteBuffer to CharBuffer
		    // CharBuffer chBuff = Charset.defaultCharset().decode(byteBuff);
		    CharBuffer chBuff = Charset.forName("UTF-8").decode(byteBuff);
		    String cssString = chBuff.toString();

		    new CSSParser().parse(cssString, document);
		    }
		catch(Exception ex){
		    ex.printStackTrace();
		}

        document.setDefaultCloseOperation(Document.EXIT_ON_CLOSE);
        document.setVisible(true);
	}
}
