package html2windows.css;

import html2windows.css.Style;
import html2windows.dom.Element;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.HashMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BorderProperty extends JPanel implements CSSPainter {

	private Graphics2D g2d;
	private Graphics border;
	private HashMap<String, String> property = new HashMap<String, String>();
	
	private int height;
	private int width;
	private int top;
	private int left;
	private int borderWidth;

	public BorderProperty() {
		initial();
	}

	public void initial() {
		property.put("border-width","10");
		property.put("width","10");
		property.put("height","10");
		property.put("top","10");
		property.put("bottom","10");
		property.put("color","blue");
		property.put("border-style","dashed");
	}

	public void paint(Style style, Element element, Graphics g) {
		this.g2d=(Graphics2D)g;
		setBorderStyle(style,"width");
		setBorderStyle(style,"height");
		setBorderStyle(style,"top");
		setBorderStyle(style,"left");
		setBorderStyle(style,"border-width");
		setBorderStyle(style,"border-style");
	}
	
	@Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics tmp = g;
        this.g2d = (Graphics2D) g;
        
        borderWidth=Integer.parseInt(property.get("border-width"));
        width=Integer.parseInt(property.get("width"));
        height=Integer.parseInt(property.get("height"));
        top=Integer.parseInt(property.get("top"));
        left=Integer.parseInt(property.get("left"));
        
        
		g2d.setColor(Color.BLUE);
		g2d.setStroke(setStroke());
		if (property.get("border-style").equals("double")){
			g2d.drawRect(top,left,width,height);
			g2d.drawRect(top-borderWidth,left-borderWidth,width+2*borderWidth,height+2*borderWidth);
		}
		else
			g2d.drawRect(top,left,width,height);
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
	
	private void setBorderStyle(Style style,String name){
		if(style.getProperty(name)!=null)
			property.put(name, style.getProperty(name));
	}
}
