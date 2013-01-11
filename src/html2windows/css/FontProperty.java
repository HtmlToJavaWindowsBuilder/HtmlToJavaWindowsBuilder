package html2windows.css;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
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


class WindowEventHandler extends WindowAdapter {
    int count = 0;
    
    public WindowEventHandler() {
        while(this.count == 0){}
    }
    public void windowClosing(WindowEvent evt) {
        this.count = 1;
    }
}

@SuppressWarnings(value= { "serial" })
public class FontProperty extends JPanel implements CSSPainter {

    private Graphics2D g2d;
    private Graphics fontd;
    Graphics tmp;
    private String text;
    private Font font;
    private Font fontVariant;
    HashMap<String, String> property = new HashMap<String, String>();

    public FontProperty(String input) { 
        initial();
    }

    public void initial() {
        this.property.put("family","Arial");
        this.property.put("font-size","12");
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.tmp = g;
        this.g2d = (Graphics2D) g;

        setFont();
        //AttributedString fontAttr = setTextDecoration(this.font,this.text);
        int fontwidth = 300;
        int textIndent = 10;//= setTextIndent();

        this.fontd = tmp.create(textIndent, 0, fontwidth, 400);
        this.fontd.setFont(this.font);
        this.fontd.setColor(Color.red);
        //this.fontd.drawString(this.text, 35, 25);
        
        AttributedString attributedString = new AttributedString(this.text);
        if( _setFontVariant() != 0 ) {
            attributedString.addAttribute(TextAttribute.FONT,this.fontVariant,0,1);
            attributedString.addAttribute(TextAttribute.FONT,this.font, 1, this.text.length());
        }
        else {
            attributedString.addAttribute(TextAttribute.FONT,this.font);
        }
        this.fontd.drawString(attributedString.getIterator(), 0, 25);

    }
    
    public void paint(Style style, Element element, Graphics g) {
        this.g2d = (Graphics2D) g;
        setFontStyle(style,"family");
        setFontStyle(style,"font-size");
        setFontStyle(style,"font-weight");
        setFontStyle(style,"font-variant");
    }

    private int _setFontVariant() {
        if( this.property.get("font-variant") != null) {
            String variant = this.property.get("font-variant").toLowerCase();
            if(variant == "small-caps") {
                this.text = this.text.toUpperCase();
                return -8;
            }
        }
        return 0;
    }

    private int _setFontSize() {
        int size = 12;
        int variantSize = _setFontVariant(); 
        if(this.property.get("font-size") == null) {
            return size + variantSize;
        }
        else {
            return Integer.parseInt(this.property.get("font-size")) + variantSize;         
        }
    }
    private void setFont() {
        int size = _setFontSize();
        int weight = _setFontWeight();
        int variant = _setFontVariant();
        String family = this.property.get("family");
        if(variant != 0) {
            this.fontVariant = new Font(family, weight, size);
        }
        this.font = new Font(family, weight, size+variant);
    }
    private int _setFontStyle(int weight) {
        String strStyle = "";
        if(this.property.get("font-style") != null ) {
            strStyle = this.property.get("font-style").toLowerCase();
        }
        if(weight == Font.BOLD && strStyle == "italic") {
            weight = Font.BOLD + Font.ITALIC;       
        }
        else if(weight != Font.BOLD && strStyle == "italic") {
            weight = Font.ITALIC;       
        }

        return weight;
    }
    private int _setFontWeight() {
        String strWeight = "";
        if(this.property.get("font-weight") != null ) {
            strWeight = this.property.get("font-weight").toLowerCase();
        }

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
        weight = _setFontStyle(weight);
        return weight;
    }

    public void setText(String text) {
        this.text = text;
        this.repaint();
    }

    public void setFontStyle(Style style,String name) {
        String type = style.getProperty(name);
        if( type != null ) {
            this.property.put(name, type) ;    
        }
    }

    private boolean isNumberic(String name) {
       Pattern pattern = Pattern.compile("[0-9]*");
       Matcher isNum = pattern.matcher(name);
       if(!isNum.matches() ){
           return false;
       }
       return true;
    }

}
