import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class BlackColorFilter extends RGBImageFilter {
	  private double redMultiplier, greenMultiplier, blueMultiplier;

	  private int newRed, newGreen, newBlue;

	  private Color color, newColor;
	  
	  //private int a[ ][ ] = new int[image.getWidth(null)][image.getHeight(null)];
	  public int[][] colors;

	  /**
	   * rm = red multiplier gm = green multiplier bm = blue multiplier
	   */
	  public BlackColorFilter(int[][] colors) {
	    canFilterIndexColorModel = true;
	    this.colors = colors;
	  }

	  private int multColor(int colorComponent, double multiplier) {
	    colorComponent = (int) (colorComponent * multiplier);
	    if (colorComponent < 0)
	      colorComponent = 0;
	    else if (colorComponent > 255)
	      colorComponent = 255;

	    return colorComponent;
	  }


	  
	  public boolean isBlack(int x, int y){
		  int count = 0;
		  if(x >= 10 && y >= 10 && x < this.colors.length -10 && y < this.colors.length - 10){
			  for(int i = x - 10; i < x + 10; i++ ){
				  for(int j = y - 10; j < y + 10; j++){
					  if(this.colors[i][j] == 0){
						  count += 1;
					  }
				  }
			  }
		  }
		  if(count >= 30){
			  return true;
		  }
		  else{
			  return false;
		  }
	  }
	  
	  public boolean isBlack2(int x, int y){
		  int count = 0;
		  if(x >= 100 && y >= 100 && x < this.colors.length - 100 && y < this.colors[0].length - 100){
			  for(int i = x - 100; i < x + 100; i++ ){
				  for(int j = y - 100; j < y + 100; j++){
					  if(this.colors[i][j] == 0){
						  count += 1;
					  }
				  }
			  }
		  }
		  if(count <=8000){
			  return true;
		  }
		  else{
			  return false;
		  }
	  }
	  
	  public boolean isBlack3(int x, int y){
		  int count = 0;
		  if( x < this.colors.length - 10 && y < this.colors[0].length - 20){
			  for(int i = x; i < x + 10; i++ ){
				  for(int j = y; j < y + 20; j++){
					  if(this.colors[i][j] == 0){
						  count += 1;
					  }
				  }
			  }
		  }
		  if(count <=120){
			  return true;
		  }
		  else{
			  return false;
		  }
	  }
	  
	  
	  /**
	   * split the argb value into its color components, multiply each color
	   * component by its corresponding scaler factor and pack the components back
	   * into a single pixel
	   */
	  public int filterRGB(int x, int y, int argb) {
		  Color c = new Color(argb);
		  if(argb == 0xff000000){
			  if(isBlack(x, y)){
				  return 0xff000000;
			  }
		  }
		  return 0xffffffff;
		  

	  }


	  
	  
	}