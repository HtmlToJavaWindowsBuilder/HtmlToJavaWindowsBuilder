package html2windows.css;

import html2windows.css.Style;
import html2windows.dom.Element;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BorderPainter extends JPanel implements CSSPainter{
	
	private Graphics2D g2d;
	private HashMap<String, String> property = new HashMap<String, String>();
	
	/**
	 * height, width, top, left values of border.
	 */
	private int height=10;
	private int width=10;
	private int top=0;
	private int left=0;
	private int borderWidth = 2;

    public BorderPainter() { 
        initial();
    }

    /**
     * initial some property ,ex: font-size is 12 ...
     */
    private void initial() {
    	property.put("border-width","2");
		property.put("width","10");
		property.put("height","10");
		property.put("top","10");
		property.put("left","5");
		property.put("bottom","10");
		property.put("border-style","solid");
		property.put("border-color","black");
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
        getBorderStyle(style,"width");
		getBorderStyle(style,"height");
		getBorderStyle(style,"top");
		getBorderStyle(style,"left");
		getBorderStyle(style,"border-width");
		getBorderStyle(style,"border-style");
		getBorderStyle(style,"border-color");
		
		borderWidth=Integer.parseInt(property.get("border-width"));
        width=Integer.parseInt(property.get("width"));
        height=Integer.parseInt(property.get("height"));
        top=Integer.parseInt(property.get("top"));
        left=Integer.parseInt(property.get("left"));
		
		setColor();
		g2d.setStroke(setStroke());
		if (property.get("border-style").equals("double")){
			g2d.drawRect(top,left,width,height);
			g2d.drawRect(top-borderWidth,left-borderWidth,width+2*borderWidth,height+2*borderWidth);
		}
		else
			g2d.drawRect(top,left,width,height);
    }


    /**
     * Function will get Font style  
     *
     * @param style     element style
     * @param name      style name, ex: font-size
     *
     */
    private void getBorderStyle(Style style,String name) {
        String type = style.getProperty(name);
        if( type != null ) {
            this.property.put(name, type) ;    
        }
    }

    
    
	
    /**
     * function will set Color     
     *
     */
    private void setColor(){
		String color=property.get("border-color").toLowerCase();
		g2d.setColor(ColorConverter.convert(color));
    }
	
	/**
	 * Set border width according to user defined border-width
	 * 
	 * @throws NumberFormatException
	 */
	private void setBorderWidth(){
		try{
			borderWidth=Integer.parseInt(property.get("border-width"));
	    }
	    catch (NumberFormatException e){
	    }
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
	
	
	private Stroke setStroke(){
		Stroke s;
		if (property.get("border-style").equals("dotted")) {
			s = new BasicStroke(10, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 5,
					new float[] { 5 }, 10);
			return s;
		} else if (property.get("border-style").equals("dashed")) {
			s = new BasicStroke(borderWidth, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, borderWidth,
					new float[] { borderWidth*2,borderWidth }, 0f);
			return s;
		} else if (property.get("border-style").equals("solid")) {
			s = new BasicStroke(borderWidth);
			return s;

		} else if (property.get("border-style").equals("double")) {
			s = new BasicStroke(2);
			return s;
		}
		return null;
	}
	
}
