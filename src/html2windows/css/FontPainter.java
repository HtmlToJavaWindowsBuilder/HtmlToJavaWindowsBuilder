package html2windows.css;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.AttributedString;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;

import html2windows.dom.Document;
import html2windows.dom.Element;
import html2windows.dom.Node;
import html2windows.dom.NodeList;
import html2windows.dom.Text;
import html2windows.css.Style;

import javax.swing.text.StyleConstants; 

/**
 * Font painter, it will get element style and paint 
 * @author bee040811
 */


@SuppressWarnings(value= { "serial" })
public class FontPainter extends JPanel implements CSSPainter {
    
    /**
     * g2d          mean Graphic
     */
    private Graphics2D g2d;

    /**
     * text         mean the font text
     */
    private String text;

    /**
     * font         mean the font style
     */
    private Font font;

    /**
     * fontVariant  mean the font variant is used
     */
    private Font fontVariant;

    /**
     * property     mean param will save the style property   
     */
    HashMap<String, String> property = new HashMap<String, String>();

    /**
     * Construct FontPainter
     */
    public FontPainter() { 
        initial();
        this.text = "test";
    }

    /**
     * initial some property ,ex: font-size is 12 ...
     */
    private void initial() {
        this.property.put("family", "Arial");
        this.property.put("font-size", "12");
        this.property.put("font-variant", "normal");
        this.property.put("font-weight", "plain");
        this.property.put("font-style", "normal");
		this.property.put("color","black");
		this.property.put("text-align","default");
		this.property.put("text-decoration","default");
		this.property.put("text-indent","default");
		this.property.put("line-height","default");
    }

    /**
     * It will paint ,when frame add or setVisible , Our Manager will start paint
     *
     * @param style     style is css style
     * @param element   element also can get his style
     * @param g         Graphic is painted by Font Painter
     *
     */
    public void paint(Style style, Element element, Graphics g) {
        this.g2d = (Graphics2D) g;
        getFontStyle(style, "family");
        getFontStyle(style, "font-size");
        getFontStyle(style, "font-weight");
        getFontStyle(style, "font-variant");
		getFontStyle(style, "color");
		getFontStyle(style, "text-align");
		getFontStyle(style, "text-decoration");
		getFontStyle(style, "text-indent");
		getFontStyle(style, "line-height");
        getFontText(element);
        setFont();
        setColor();

        AttributedString attributedString = new AttributedString(this.text);
        if( setFontVariant() != 0 ) {
            attributedString.addAttribute(TextAttribute.FONT,this.fontVariant, 0, 1);
            attributedString.addAttribute(TextAttribute.FONT,this.font, 1, this.text.length());
        }
        else {
            attributedString.addAttribute(TextAttribute.FONT,this.font);
        }
        
        setTextDecoration(attributedString);
        
        
        
        this.g2d.drawString(attributedString.getIterator(), 30, 30);
        
        
        
        
        /*
        this.g2d.drawString(this.text,20,30);
        this.g2d.drawString("text",20,30);
        */
    }

    /**
     * Function will set size of the words, when setting variant
     *
     * @return  size    int 
     */
    private int setFontVariant() {
        String variant = this.property.get("font-variant").toLowerCase();
        int variantSmall = 4;
        if(variant.equals("small-caps")) {
            this.text = this.text.toUpperCase();

            return variantSmall;
        }

        return 0;
    }

    /**
     * Function will set font size 
     *
     * @return  font-size int  
     */
    private int setFontSize() {
        String fontSize = this.property.get("font-size");
        if( fontSize.matches("[0-9]+") ) {
            return Integer.parseInt(fontSize);
        }
        else if(fontSize.matches("[0-9]+px")) {
            fontSize = fontSize.replaceAll("([0-9]+)px","$1");          
            return Integer.parseInt(fontSize);
        }
        return 12;
    }

    /**
     * Function will set Font family, ex: Arial
     */
    private void setFont() {
        int size = setFontSize();
        int weight = setFontWeight();
        int variant = setFontVariant();
        String family = this.property.get("family");

        if(variant != 0) {
            this.fontVariant = new Font(family, weight, size+variant);
        }
        this.font = new Font(family, weight, size);
    }

    /**
     * Function will set Font Style, ex: Italic
     *
     * @param weight    mean the font weight 
     */
    private int setFontStyleItalic(int weight) {
        String strStyle = this.property.get("font-style").toLowerCase();

        if(weight == Font.BOLD && strStyle.equals("italic")) {
            weight = Font.BOLD + Font.ITALIC;       
        }
        else if(weight != Font.BOLD && strStyle.equals("italic")) {
            weight = Font.ITALIC;       
        }

        return weight;
    }

    /**
     * Function will set Font weight, ex: Bold 
     *
     * @return  weight  font bold
     */
    private int setFontWeight() {
        String strWeight = this.property.get("font-weight").toLowerCase();
        int weight = Font.PLAIN;

        if(strWeight.equals("bold" )) {
            weight = Font.BOLD;       
        }
        else if(strWeight.equals("italic")) {
            weight = Font.ITALIC;       
        }
        else if(strWeight.equals("plain")) {
            weight = Font.PLAIN;       
        }
        else if(strWeight.equals("bold+italic")) {
            weight = Font.BOLD + Font.ITALIC;       
        }
        weight = setFontStyleItalic(weight);

        return weight;
    }

    /**
     * Function will set Font text
     *
     * @param text
     *
     */
    public void setText(String text) {
        this.text = text;
        //this.repaint();
    }

    /**
     * Function will get Font style  
     *
     * @param style     element style
     * @param name      style name, ex: font-size
     *
     */
    public void getFontStyle(Style style,String name) {
        String type = style.getProperty(name);
        if( type != null ) {
            this.property.put(name, type) ;    
        }
    }

    
    /**
     * Function will get the length of the word
     *
     * @return  length  the length of the word
     */
	public int getLength(){
		FontMetrics f= g2d.getFontMetrics();
		int length =f.stringWidth(this.text);

		return length;
	}
	
    /**
     * function will set Color     
     *
     */
	public void setColor(){
		
		String color=property.get("color").toLowerCase();
		
		if(color.equals("maroon")){
			
			g2d.setColor(new Color(128,0,0));
		}
		else if(color.equals("red")){
			
			g2d.setColor(new Color(255,0,0));
		}
		else if(color.equals("orange")){
			
			g2d.setColor(new Color(255,165,0));
			
		}
		else if(color.equals("yellow")){
			
			g2d.setColor(new Color(255,255,0));
		}
		else if(color.equals("olive")){
			
			g2d.setColor(new Color(128,128,0));
		}
		else if(color.equals("purple")){
			
			g2d.setColor(new Color(128,0,128));
		}
		else if(color.equals("fuchsia")){
			
			g2d.setColor(new Color(255,0,255));
		}
		else if(color.equals("white")){
			
			g2d.setColor(new Color(255,255,255));
		}
		else if(color.equals("lime")){
			
			g2d.setColor(new Color(0,255,255));
		}
		else if(color.equals("green")){
			
			g2d.setColor(new Color(0,255,0));
		}
		else if(color.equals("navy")){
			
			g2d.setColor(new Color(0,0,128));
		}
		else if(color.equals("blue")){
			
			g2d.setColor(new Color(0,0,255));
		}
		else if(color.equals("aqua")){
			
			g2d.setColor(new Color(0,255,255));
		}
		else if(color.equals("teal")){
			
			g2d.setColor(new Color(0,128,128));
		}
		else if(color.equals("black")||color.equals("default")){
			g2d.setColor(new Color(0,0,0));
		}
		else if(color.equals("silver")){
			
			g2d.setColor(new Color(192,192,192));
		}
		else if(color.equals("gray")){
			
			g2d.setColor(new Color(128,128,128));
		}
		else if(color.matches("#[0-9A-Fa-f]{6}")){
            String firstColor=color.substring(1, 3);
            int firstColorNum=Integer.parseInt(firstColor, 16);

            String secondColor=color.substring(3,5);
            int secondColorNum=Integer.parseInt(secondColor, 16);

            String thirdColor=color.substring(5,7);
            int thirdColorNum=Integer.parseInt(thirdColor, 16);

            g2d.setColor(new Color(firstColorNum,secondColorNum,thirdColorNum));

		}
        else {
            
			g2d.setColor(new Color(0,0,0));
        }
	
	}

	
   
		
    /**
     * Function will set Text Decoration
     *
     * @param f The font f is 
     * @param s
     *
     * @return   
     */
	public void setTextDecoration(AttributedString attributedString){
		String textDecoration=property.get("text-decoration");
		
		if(textDecoration.equals("underline"))
		    attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

		if(textDecoration.equals("line-through"))
			attributedString.addAttribute(TextAttribute.STRIKETHROUGH,TextAttribute.STRIKETHROUGH_ON);
	
		
		
	}
	
    /**
     * Function set text indent
     *
     * @return   textIndent
     */
	public int setTextIndent(){
		String textIndent=property.get("text-align");
		
		return Integer.parseInt(textIndent);
	}
	
    /**
     * Function will get font text. 
     *
     * @param	element The element you want to get text from.  
     */
	private void getFontText(Element element){
		 NodeList nodeList =element.childNodes();
		 Node nodeText=nodeList.item(0);
		 this.text=nodeText.nodeValue();
	}
	
}
