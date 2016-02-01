import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class ColorComponentScaler extends RGBImageFilter {

	  //The colors we need
	  private Color violetColor = new Color(169,136,214);
	  private Color greyColor = new Color(138, 145, 160);
	  
	  private Color blackColor = new Color(80, 80, 80);
	 // private Color blackColor = new Color(60,60,60);
	  


	  public ColorComponentScaler() {
	    canFilterIndexColorModel = true;
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
		  double distance = Math.pow((color1.getBlue() - color2.getBlue()),2) +Math.pow((color1.getRed() - color2.getRed()),2 )+ Math.pow((color1.getGreen() - color2.getGreen()),2);
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
		// if(calculateDistance(c, violetColor) < 50 || calculateDistance(c, greyColor) < 30 || calculateDistance(c, blackColor) < 85){
	  if(calculateDistance(c, blackColor) < 95){
			  return 0xff000000;
		  }
	    return 0xffffffff;
	  }


	  
	  
	}