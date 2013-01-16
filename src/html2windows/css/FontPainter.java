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
		this.property.put("text-color","black");
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
		getFontStyle(style, "text-color");
		getFontStyle(style, "text-align");
		getFontStyle(style, "text-decoration");
		getFontStyle(style, "text-indent");
		getFontStyle(style, "line-height");
        setFont();
        //getFontText(element);
        /*
        AttributedString fontAttr = setTextDecoration(this.font,this.text);
        int fontwidth = 300;
        int textIndent = setTextIndent();
        */
        AttributedString attributedString = new AttributedString(this.text);
        if( setFontVariant() != 0 ) {
            attributedString.addAttribute(TextAttribute.FONT,this.fontVariant,0,1);
            attributedString.addAttribute(TextAttribute.FONT,this.font, 1, this.text.length());
        }
        else {
            attributedString.addAttribute(TextAttribute.FONT,this.font);
        }
        this.g2d.drawString(attributedString.getIterator(), 30, 30);
        
        /*
        this.g2d.setFont(this.font);
        this.g2d.setColor(Color.red);
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
        int variantSmall = -8;
        if(variant == "small-caps") {
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
        int variantSize = setFontVariant(); 

        return Integer.parseInt(this.property.get("font-size")) + variantSize;
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
            this.fontVariant = new Font(family, weight, size);
        }
        this.font = new Font(family, weight, size+variant);
    }

    /**
     * Function will set Font Style, ex: Italic
     *
     * @param weight    mean the font weight 
     */
    private int setFontStyleItalic(int weight) {
        String strStyle = this.property.get("font-style").toLowerCase();

        if(weight == Font.BOLD && strStyle == "italic") {
            weight = Font.BOLD + Font.ITALIC;       
        }
        else if(weight != Font.BOLD && strStyle == "italic") {
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

        if(strWeight == "bold" ) {
            weight = Font.BOLD;       
        }
        else if(strWeight == "italic") {
            weight = Font.ITALIC;       
        }
        else if(strWeight == "plain") {
            weight = Font.PLAIN;       
        }
        else if(strWeight == "bold+italic") {
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
		
		String color=property.get("text-color");
		
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
		else{
			String firstColor=color.substring(1, 3);
			int firstColorNum=Integer.parseInt(firstColor, 16);
			
			String secondColor=color.substring(3,5);
			int secondColorNum=Integer.parseInt(secondColor, 16);
			
			String thirdColor=color.substring(5,7);
			int thirdColorNum=Integer.parseInt(thirdColor, 16);
			
			g2d.setColor(new Color(firstColorNum,secondColorNum,thirdColorNum));
			
			
		}
	
	}
	
    /**
     * Function will set light height 
     *
     */
	public void setLineHeight(){
		String textAlign=property.get("line-height");
		
	}
	
    /**
     * Function will set text align
     *
     * @param   s     text
     *
     * @return  length   
     */
	public int setTextAlign(String s){
		String textAlign=property.get("text-align");
		int width=getWidth();
		int stringLength = getFontLength(s);
		
		if(textAlign.equals("center")){
			return (width/2-stringLength/2);
			
		}
		if(textAlign.equals("left")){
			
			return 0;
		}
		if(textAlign.equals("right")){
			return (width-stringLength);
			
		}
		
		return 0;
	}
		
    /**
     * Function will set Text Decoration
     *
     * @param f
     * @param s
     *
     * @return   
     */
	public AttributedString setTextDecoration(Font f,String s){
		String textDecoration=property.get("text-decoration");
		AttributedString as = new AttributedString(s);
		as.addAttribute(TextAttribute.FONT, f);
		
		if(textDecoration.equals("underline"))
		    as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

		if(textDecoration.equals("line-through"))
		    as.addAttribute(TextAttribute.STRIKETHROUGH,TextAttribute.STRIKETHROUGH_ON, 18, 25);
	
		return as;
		
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
     * Function will get font length 
     *
     * @param   s       text
     *
     * @return  length     
     */
	public int getFontLength(String s){
		FontMetrics fm = g2d.getFontMetrics();
		int length = fm.stringWidth(s);
		
		return length;
	}
    /**
     * Function will get font length 
     *
     * @param   s       text
     *
     * @return  length     
     */
	private void getFontText(Element element){
		 this.text = element.nodeValue();
		
	}
	
}
