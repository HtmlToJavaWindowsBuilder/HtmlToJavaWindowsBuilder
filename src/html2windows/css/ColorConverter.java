package html2windows.css;

import java.awt.Color;

public class ColorConverter {

	static Color convert(String color){
		if("maroon".equals(color)){
			return new Color(128,0,0);
		}
		else if("red".equals(color)){
			return new Color(255,0,0);
		}
		else if("orange".equals(color)){
			return new Color(255,165,0);
		}
		else if("yellow".equals(color)){
			return new Color(255,255,0);
		}
		else if("olive".equals(color)){
			return new Color(128,128,0);
		}
		else if("purple".equals(color)){
			return new Color(128,0,128);
		}
		else if("fuchsia".equals(color)){
			return new Color(255,0,255);
		}
		else if("white".equals(color)){
			return new Color(255,255,255);
		}
		else if("lime".equals(color)){
			return new Color(0,255,255);
		}
		else if("green".equals(color)){
			return new Color(0,255,0);
		}
		else if("navy".equals(color)){
			return new Color(0,0,128);
		}
		else if("blue".equals(color)){
			return new Color(0,0,255);
		}
		else if("aqua".equals(color)){
			return new Color(0,255,255);
		}
		else if("teal".equals(color)){
			return new Color(0,128,128);
		}
		else if("black".equals(color)||"default".equals(color)){
			return new Color(0,0,0);
		}
		else if("silver".equals(color)){
			return new Color(192,192,192);
		}
		else if("gray".equals(color)){
			return new Color(128,128,128);
		}
		else if(color!=null && color.matches("#[0-9A-Fa-f]{6}")){
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
