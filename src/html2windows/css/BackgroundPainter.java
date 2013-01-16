package html2windows.css;

import html2windows.css.Style;
import html2windows.dom.Element;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Draw border by border painter according to some 
 * user defined property in style.
 *  
 * @author Jason Kuo
 */

@SuppressWarnings("serial")
public class BackgroundPainter extends JPanel implements CSSPainter{
	
	/**
	 * graphic to draw on
	 */
	private Graphics2D g2d;
	private int height=100;
	private int width=100;
	
	/**
	 * HashMap that contains properties.
	 */
	private HashMap<String, String> property = new HashMap<String, String>();
	
    public BackgroundPainter() { 
        initial();
    }

    /**
     * Initial property to prevent user from not defining
     * those property.
     */
    private void initial() {
    	property.put("background-color","red");
    }

    /**
     * Function to paint. First, get property from user defined style.
     * Then, set color, stroke and draw on graphic.
     *
     * @param style     user defined style property
     * @param element   element to be drawn
     * @param g         graphic to draw on
     */
    public void paint(Style style, Element element, Graphics g) {
        this.g2d = (Graphics2D) g;
        
        //get property we want from user defined property
        getBackgroundStyle(style,"background-color");
        getBackgroundStyle(style,"width");
        getBackgroundStyle(style,"height");
        
        setColor();
        setWidth();
        setHeight();
        
        g2d.fillRect(0, 0,width ,height);
    }
    
    /**
	 * Set width according to user defined width
	 * 
	 * @throws NumberFormatException
	 */
	private void setWidth() throws NumberFormatException{
		try{
			width=Integer.parseInt(property.get("width"));
	    }
	    catch (NumberFormatException e){
	    }
	}
	
	/**
	 * Set height according to user defined height
	 * 
	 * @throws NumberFormatException
	 */
	private void setHeight() throws NumberFormatException{
		try{
			height=Integer.parseInt(property.get("height"));
	    }
	    catch (NumberFormatException e){
	    }
	}


    /**
     * Get property we want from user defined style property  
     *
     * @param style     user defined style
     * @param name      property name to get
     */
    private void getBackgroundStyle(Style style,String name) {
        String type = style.getProperty(name);
        if( type != null ) {
            this.property.put(name, type) ;    
        }
    }

    /**
     * Set color by user defined property "border-color"     
     */
    private void setColor(){
		String color=property.get("background-color").toLowerCase();
		g2d.setColor(ColorConverter.convert(color));
    }
}