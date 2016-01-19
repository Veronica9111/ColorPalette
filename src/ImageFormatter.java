import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFormatter {
	
	
	public static void main(String[] args) {    
        Image image = null;
        BufferedImage bfImage = null;
        System.out.println(Math.sqrt(9));
        try {
            //image = ImageIO.read(new File("/Users/veronica/Desktop/material/png/invoice9.png") );
        	 image = ImageIO.read(new File("/Users/veronica/Desktop/test.tif") );



          
            ImageFilter colorfilter = new ColorComponentScaler();             
            FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), colorfilter );
   
            Image filteredImage = Toolkit.getDefaultToolkit().createImage(filteredImageSource); 

           BufferedImage filtered = toBufferedImage(filteredImage);
         int[][] colors = new int[image.getWidth(null)][image.getHeight(null)];

           for(int i = 0; i < colors.length; i++){

           	for(int j = 0; j < colors[i].length; j++){

           			if(filtered.getRGB(i, j) == 0xff000000){
           				colors[i][j] = 0;
           			}else{
           				colors[i][j] = 1;
           			}
                  
           	}
           }

           ImageFilter blackFilter = new BlackColorFilter(colors);
           FilteredImageSource filteredNewImageSource = new FilteredImageSource(filteredImage.getSource(), blackFilter);
           Image filteredNewImage = Toolkit.getDefaultToolkit().createImage(filteredNewImageSource);
           BufferedImage newFiltered = toBufferedImage(filteredNewImage);
           newFiltered = rotate90DX(newFiltered);


            save(newFiltered, "tif");    
        } catch( IOException e){
            //do something
            System.out.print(e.getMessage());
        }
    }

	
	public static BufferedImage rotate90DX(BufferedImage bi) {
	    int width = bi.getWidth();
	    int height = bi.getHeight();
	    BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
	    for(int i=0; i<width; i++)
	        for(int j=0; j<height; j++)
	            biFlip.setRGB(height-1-j, i, bi.getRGB(i, j));
	    return biFlip;
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
    private static void save(BufferedImage image, String ext) {
        String fileName = "test2";
        File file = new File("/Users/veronica/Desktop/" + fileName + "." + ext);
        try {
            ImageIO.write(image, ext, file);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                               ": " + e.getMessage());
        }
    }       

	

}


