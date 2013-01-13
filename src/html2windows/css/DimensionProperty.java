package html2windows.css;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import html2windows.dom.Document;
import html2windows.dom.Element;

@SuppressWarnings(value= { "serial" })
public class DimensionProperty extends JPanel implements CSSPainter {
    private int width;
    private int height;
    private int backgroundColor;

    Graphics2D g2d;
    HashMap <String, String> property = new HashMap <String, String>();

    public DimensionProperty(String input) { 
        initial();
    }

    public void paint(Style style, Element element, Graphics g) {
        this.g2d = (Graphics2D) g;
        setDimensionStyle(style, "width");
        setDimensionStyle(style, "min-width");
        setDimensionStyle(style, "max-width");
        setDimensionStyle(style, "height");
        setDimensionStyle(style, "min-height");
        setDimensionStyle(style, "max-height");
        setDimensionStyle(style, "background-color");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        /*
        String string="2333333";
        this.g2d.drawString(string, 20, 20);
        */
        setDimension();
        this.g2d.fillRect(0, 0, this.width, this.height);
    }
    
    private void initial() {
        String defaultValue = "-1";
        String defaultColor = "white";
        this.property.put("width", "0") ;    
        this.property.put("min-width", defaultValue) ;    
        this.property.put("max-width", defaultValue) ;    
        this.property.put("height", "0") ;    
        this.property.put("min-height", defaultValue) ;    
        this.property.put("max-height", defaultValue) ;    
        this.property.put("background-color", defaultColor) ;    
    }

    private void setDimension() {
        this.width = setWidth();    
        this.height = setHeight();    
        setBackgroundColor();
    }
    private void setDimensionStyle(Style style, String name) {
        String type = style.getProperty(name);
        if( type != null ) {
            this.property.put(name, type) ;    
        }
    }

    private void implementDimensionProperty() {
    }

    private void setMaxHeight(){

    }
    private void setMinHeight(){

    }

    private int setHeight(){
        int min = -1;
        int max = -1;
        int value = 0;
        value = Integer.parseInt(this.property.get("height"));
        max = Integer.parseInt(this.property.get("max-height") );
        min = Integer.parseInt(this.property.get("min-height") );
        if( value < min  && min != -1) {
            return min;
        }
        else if( value > max && max != -1) {
            return max;
        }
        else if(max != -1 && min != -1 && min < max) {
            return min;
        }
        return value;
    }
    private int setWidth(){
        int min = -1;
        int max = -1;
        int value = 0;
        value = Integer.parseInt(this.property.get("width"));
        max = Integer.parseInt(this.property.get("max-width") );
        min = Integer.parseInt(this.property.get("min-width") );
        if( value < min  && min != -1) {
            return min;
        }
        else if( value > max && max != -1) {
            return max;
        }
        else if(max != -1 && min != -1 && min < max) {
            return min;
        }
        return value;
    }
    private void setBackgroundColor(){
        String color = this.property.get("background-color").toLowerCase();
        if(color == "orange") {
            this.g2d.setColor(Color.orange);    
        }
        else if(color == "black") {
            this.g2d.setColor(Color.black);    
        }
        else {
            this.g2d.setColor(Color.white);    
        }
    }
}
