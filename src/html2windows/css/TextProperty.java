package html2windows.css;

import html2windows.dom.Element;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings(value = { "serial" })
public class TextProperty extends JPanel implements CSSPainter{
	
	Graphics g2d;
	HashMap<String,String> property=new HashMap<String,String>();
	
	
	public TextProperty(){
			initial();
		
	}
	
	@Override
	public void paint(Style style, Element element, Graphics g) {
		
		setFontStyle(style,"text-color");
		setFontStyle(style,"text-align");
		setFontStyle(style,"text-decoration");
		setFontStyle(style,"text-indent");
		setFontStyle(style,"line-height");
		
		
		
	}
	
	public void initial(){
		property.put("text-color","black");
		property.put("text-align","default");
		property.put("text-decoration","default");
		property.put("text-indent","default");
		property.put("line-height","default");
	}
	
	public void setFontStyle(Style type,String name){
		String style=type.getProperty(name);
		if(!style.isEmpty())
			this.property.put(name,style);
		
		
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g2d=g;
		setColor();
		setLineHeight();
		setTextIndent();
		setTextAlign();
		
	}
	
	
	
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
		else if(color.equals("black")){
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
	
	public void setLineHeight(){
		String textAlign=property.get("line-height");
		
	}
	
	public void setTextAlign(){
		String textAlign=property.get("text-align");
		if(textAlign.equals("center")){
			
			
		}
		if(textAlign.equals("left")){
			
			
		}
		if(textAlign.equals("right")){
			
			
		}

	}
		
	public AttributedString setTextDecoration(Font f,String s){
		String textDecoration=property.get("text-decoration");
		AttributedString as = new AttributedString(s);
		as.addAttribute(TextAttribute.FONT, f);
		
		if(textDecoration.equals("underline"))
		    as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		/*
		if(textDecoration.equals("overline"))
			 as.addAttribute(TextAttribute., TextAttribute.UNDERLINE_ON);
		*/
		if(textDecoration.equals("line-through"))
		    as.addAttribute(TextAttribute.STRIKETHROUGH,TextAttribute.STRIKETHROUGH_ON, 18, 25);

		
	
		return as;
		
	}
	
	public void setTextIndent(){
		String textIndent=property.get("text-align");
		
		
		
	}



	
	
	
}
