import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class ColorComponentScaler extends RGBImageFilter {

	  //The colors we need
	  private Color violetColor = new Color(169,151,214);
	  private Color greyColor = new Color(138, 145, 160);
	  
	  private Color blackColor = new Color(90, 90, 90);
	 // private Color blackColor = new Color(60,60,60);
	  
	  private int[][] colors;
	  


	  public ColorComponentScaler(int[][]colors) {
	    canFilterIndexColorModel = true;
	    this.colors = colors;
	  }

	  public boolean inRange(int value, int start, int end){
		  if(value >= start && value <= end){
			  return true;
		  }
		  return false;
	  }
	  
	  public boolean isAverage(int red, int green, int blue){
		  int gap = Math.abs(red-green) + Math.abs(green - blue) + Math.abs(red - blue);
		  if(gap < 50){
			  return true;
		  }
		  return false;
	  }
	  
	  public double calculateDistance(Color color1, Color color2, boolean flag){
		 
		  double distance = Math.pow((color1.getBlue() - color2.getBlue()),2) +Math.pow((color1.getRed() - color2.getRed()),2 )+ Math.pow((color1.getGreen() - color2.getGreen()),2);
		  distance = Math.sqrt(distance);
		  if(flag){
			 // System.out.println("color distance");
		  System.out.println(distance);
		  }
		  return distance;
	  }
	  
	  public boolean isSurrounded(int x, int y){
		  int count = 0;
		  try{
		  for(int i = x-1; i <= x+1; i++){
			  for(int j = y - 1; j <= y+1; j++){
				  Color c = new Color(this.colors[i][j]);
				  if(calculateDistance(c, blackColor, false) < 60 ){
					  count += 1;
				  }
			  }
		  }
		  }catch(Exception e){
			 // System.out.println(x + " " + y);
		  }
		  if(count <=2){
			  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * split the argb value into its color components, multiply each color
	   * component by its corresponding scaler factor and pack the components back
	   * into a single pixel
	   */
	  public int filterRGB(int x, int y, int argb) {
		  Color c = new Color(argb);	
		  
		  
		// if(calculateDistance(c, violetColor) < 50 ||
				 if(calculateDistance(c, violetColor, true) < 50 || calculateDistance(c, greyColor, false) < 30 || (calculateDistance(c, blackColor, false) < 140  && isAverage(c.getRed(), c.getGreen(), c.getBlue()) )){
					 
					if  (calculateDistance(c,violetColor, true) < 50 ){
						if((c.getBlue() - c.getGreen()) >=20 && (c.getBlue() - c.getRed() )>= 20){
						    return 0xff000000;
						}
					}
					 if(calculateDistance(c, blackColor, true) < 80)
					 {
						 return 0xff000000;
					 }
					 if(!isSurrounded(x,y)){
						 return 0xff000000;
					 }
	  //if(calculateDistance(c, blackColor) < 95){
			  //return 0xff000000;
		  }
	    return 0xffffffff;
	  }


	  
	  
	}