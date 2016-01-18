import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class ColorComponentScaler extends RGBImageFilter {
	  private double redMultiplier, greenMultiplier, blueMultiplier;

	  private int newRed, newGreen, newBlue;

	  private Color color, newColor;
	  
	  //private int a[ ][ ] = new int[image.getWidth(null)][image.getHeight(null)];
	//  public int[][] colors;

	  /**
	   * rm = red multiplier gm = green multiplier bm = blue multiplier
	   */
	  public ColorComponentScaler() {
	    canFilterIndexColorModel = true;
	  //  this.colors = colors;
	  }

	  private int multColor(int colorComponent, double multiplier) {
	    colorComponent = (int) (colorComponent * multiplier);
	    if (colorComponent < 0)
	      colorComponent = 0;
	    else if (colorComponent > 255)
	      colorComponent = 255;

	    return colorComponent;
	  }

	  public boolean inRange(int value, int start, int end){
		  if(value >= start && value <= end){
			  return true;
		  }
		  return false;
	  }
	  
	  public boolean isAverage(int red, int green, int blue){
		  int gap = Math.abs(red-green) + Math.abs(green - blue) + Math.abs(red - blue);
		  if(gap < 30){
			  return true;
		  }
		  return false;
	  }
	  
	/*  public boolean isBlack(int x, int y){
		  int count = 0;
		  if(x >= 10 && y >= 10 && x < this.colors.length && y < this.colors.length){
			  for(int i = x - 10; i < x + 10; i++ ){
				  for(int j = y - 10; j < y + 10; j++){
					  if(this.colors[i][j] == 0){
						  count += 1;
					  }
				  }
			  }
		  }
		  if(count >= 25){
			  return true;
		  }
		  else{
			  return false;
		  }
	  }*/
	  
	  /**
	   * split the argb value into its color components, multiply each color
	   * component by its corresponding scaler factor and pack the components back
	   * into a single pixel
	   */
	  public int filterRGB(int x, int y, int argb) {
		  Color c = new Color(argb);
		 // for grey color
	/*	  if(isAverage(c.getRed(), c.getGreen(), c.getBlue()) && inRange(c.getRed(), 100, 200) && inRange(c.getGreen(), 100, 200) && inRange(c.getBlue(), 100, 200)){

				  return 0xff000000;

		  }
		  */
		  // for violet color
		   if(c.getBlue() > 150 && c.getRed()> 120 && (c.getBlue() + c.getRed() + c.getGreen()) < 580 && c.getBlue() > c.getRed() && c.getBlue() > c.getGreen()){

				  return 0xff000000;

		  }
		   else if(c.getBlue() > 220 && (c.getBlue() - c.getRed()) > 20 && (c.getBlue() - c.getRed()) < 50 && (c.getBlue() - c.getGreen()) > 20 && (c.getBlue() - c.getGreen()) < 70){
			   return 0xff000000;
		   }
		  else if (c.getRed() > 150){

			  return 0xffffffff;
		  }

	    return 0xffffffff;
	    

	  }


	  
	  
	}