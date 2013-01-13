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
		property.put("border-color","black");
	}
	
	public void paint(Style style, Element element, Graphics g) {
		this.g2d=(Graphics2D)g;
		setBorderStyle(style,"width");
		setBorderStyle(style,"height");
		setBorderStyle(style,"top");
		setBorderStyle(style,"left");
		setBorderStyle(style,"border-width");
		setBorderStyle(style,"border-style");
		setBorderStyle(style,"border-color");
	}
	
	@Override 
    public void paint(Graphics g) {
        Graphics tmp = g;
        this.g2d = (Graphics2D) g;
        
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
	
	private void setColor(){
		String color=property.get("border-color");

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
