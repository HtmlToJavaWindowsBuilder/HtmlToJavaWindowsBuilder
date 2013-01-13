package html2windows.css;

import html2windows.dom.Element;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import javax.swing.JPanel;

public class BackgroundProperty extends JPanel implements CSSPainter{
	
	private Graphics2D g2d;
	private Graphics border;
	private HashMap<String, String> property = new HashMap<String, String>();
	
	private int height;
	private int width;
	private int top;
	private int left;
	private int borderWidth;

	public BackgroundProperty() {
		initial();
	}

	public void initial() {
		property.put("background-color","red");
	}
	
	public void paint(Style style, Element element, Graphics g) {
		this.g2d=(Graphics2D)g;
		setBorderStyle(style,"background-color");
	}
	
	@Override 
    public void paint(Graphics g) {
        Graphics tmp = g;
        this.g2d = (Graphics2D) g;
        
        setColor();
        g2d.fill(new Rectangle2D.Double(0, 0,  getParent().getSize().width,  getParent().getSize().height));
        
	}
	
	private void setColor(){
		String color=property.get("background-color");

		if(color.equals("maroon")){

			g2d.setPaint(new Color(128,0,0));
		}
		else if(color.equals("red")){

			g2d.setPaint(new Color(255,0,0));
		}
		else if(color.equals("orange")){

			g2d.setPaint(new Color(255,165,0));

		}
		else if(color.equals("yellow")){

			g2d.setPaint(new Color(255,255,0));
		}
		else if(color.equals("olive")){

			g2d.setPaint(new Color(128,128,0));
		}
		else if(color.equals("purple")){

			g2d.setPaint(new Color(128,0,128));
		}
		else if(color.equals("fuchsia")){

			g2d.setPaint(new Color(255,0,255));
		}
		else if(color.equals("white")){

			g2d.setPaint(new Color(255,255,255));
		}
		else if(color.equals("lime")){

			g2d.setPaint(new Color(0,255,255));
		}
		else if(color.equals("green")){

			g2d.setPaint(new Color(0,255,0));
		}
		else if(color.equals("navy")){

			g2d.setPaint(new Color(0,0,128));
		}
		else if(color.equals("blue")){

			g2d.setPaint(new Color(0,0,255));
		}
		else if(color.equals("aqua")){

			g2d.setPaint(new Color(0,255,255));
		}
		else if(color.equals("teal")){

			g2d.setPaint(new Color(0,128,128));
		}
		else if(color.equals("black")||color.equals("default")){
			g2d.setPaint(new Color(0,0,0));
		}
		else if(color.equals("silver")){

			g2d.setPaint(new Color(192,192,192));
		}
		else if(color.equals("gray")){

			g2d.setPaint(new Color(128,128,128));
		}
		else{
			String firstColor=color.substring(1, 3);
			int firstColorNum=Integer.parseInt(firstColor, 16);

			String secondColor=color.substring(3,5);
			int secondColorNum=Integer.parseInt(secondColor, 16);

			String thirdColor=color.substring(5,7);
			int thirdColorNum=Integer.parseInt(thirdColor, 16);

			g2d.setPaint(new Color(firstColorNum,secondColorNum,thirdColorNum));
		}
	}
	
	private void setBorderStyle(Style style,String name){
		if(style.getProperty(name)!=null)
			property.put(name, style.getProperty(name));
	}
}
