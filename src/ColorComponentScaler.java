import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class ColorComponentScaler extends RGBImageFilter {
	  private double redMultiplier, greenMultiplier, blueMultiplier;

	  private int newRed, newGreen, newBlue;

	  private Color color, newColor;
	  
	  private Color violetColor = new Color(169,136,214);
	  private Color greyColor = new Color(138, 145, 160);
	  
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
	  
	  public double calculateDistance(Color color1, Color color2){
		  double distance =Math.pow((color1.getBlue() - color2.getBlue()),2) +Math.pow((color1.getRed() - color2.getRed()),2 )+ Math.pow((color1.getGreen() - color2.getGreen()),2);
		  distance = Math.sqrt(distance);
		  return distance;
	  }
	  
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
		/*   if(c.getBlue() > 150 && c.getRed()> 120 && (c.getBlue() + c.getRed() + c.getGreen()) < 580 && c.getBlue() > c.getRed() && c.getBlue() > c.getGreen()){

				  return 0xff000000;

		  }
		   else if(c.getBlue() > 220 && (c.getBlue() - c.getRed()) > 20 && (c.getBlue() - c.getRed()) < 50 && (c.getBlue() - c.getGreen()) > 20 && (c.getBlue() - c.getGreen()) < 70){
			   return 0xff000000;
		   }
		  else if (c.getRed() > 150){

			  return 0xffffffff;
		  }*/
		  
		  if(calculateDistance(c, violetColor) < 50 || calculateDistance(c, greyColor) < 30){
			  return 0xff000000;
		  }

	    return 0xffffffff;
	    

	  }


	  
	  
	}