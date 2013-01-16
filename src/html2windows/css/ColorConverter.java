package html2windows.css;

import java.awt.Color;

public class ColorConverter {

	static Color convert(String color){
		if(color.equals("maroon")){
			return new Color(128,0,0);
		}
		else if(color.equals("red")){
			return new Color(255,0,0);
		}
		else if(color.equals("orange")){
			return new Color(255,165,0);
		}
		else if(color.equals("yellow")){
			return new Color(255,255,0);
		}
		else if(color.equals("olive")){
			return new Color(128,128,0);
		}
		else if(color.equals("purple")){
			return new Color(128,0,128);
		}
		else if(color.equals("fuchsia")){
			return new Color(255,0,255);
		}
		else if(color.equals("white")){
			return new Color(255,255,255);
		}
		else if(color.equals("lime")){
			return new Color(0,255,255);
		}
		else if(color.equals("green")){
			return new Color(0,255,0);
		}
		else if(color.equals("navy")){
			return new Color(0,0,128);
		}
		else if(color.equals("blue")){
			return new Color(0,0,255);
		}
		else if(color.equals("aqua")){
			return new Color(0,255,255);
		}
		else if(color.equals("teal")){
			return new Color(0,128,128);
		}
		else if(color.equals("black")||color.equals("default")){
			return new Color(0,0,0);
		}
		else if(color.equals("silver")){
			return new Color(192,192,192);
		}
		else if(color.equals("gray")){
			return new Color(128,128,128);
		}
		else if(color.matches("#[0-9A-Fa-f]{6}")){
            String firstColor=color.substring(1, 3);
            int firstColorNum=Integer.parseInt(firstColor, 16);

            String secondColor=color.substring(3,5);
            int secondColorNum=Integer.parseInt(secondColor, 16);

            String thirdColor=color.substring(5,7);
            int thirdColorNum=Integer.parseInt(thirdColor, 16);

            return new Color(firstColorNum,secondColorNum,thirdColorNum);
		}
        else{
			return new Color(0,0,0);
        }
	}

}
