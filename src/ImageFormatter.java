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

import org.apache.commons.io.FileUtils;

public class ImageFormatter {

	private String path;

	public ImageFormatter(String path){
		this.path = path;
	}

	public String filterImage(String type, String fileName){
		Image image = null;
		BufferedImage  newFiltered = null;
		String filteredPath = "";
		try {
			image = ImageIO.read(new File(this.path) );

			
			BufferedImage bwImage = filterColor(image);
			//BufferedImage finalImage = filterBlack(image, bwImage);
			//finalImage = rotate90DX(finalImage);
			//BufferedImage finalImage = rotate90DX(bwImage);
			BufferedImage finalImage = bwImage;
			filteredPath = save(finalImage, type, fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return filteredPath;
	}
	
	/**
	 * 
	 * Get needed color only
	 */
	public BufferedImage filterColor(Image image){
		//filter the image to get the certain color
		BufferedImage bfImage = (BufferedImage) image;
		int[][]colors = generateColorMatrix2(bfImage, image.getWidth(null), image.getHeight(null));
		ImageFilter colorfilter = new ColorComponentScaler(colors);             
		FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), colorfilter );
		Image filteredImage = Toolkit.getDefaultToolkit().createImage(filteredImageSource); 
		BufferedImage filtered = toBufferedImage(filteredImage);
		return filtered;
	}
	
	/**
	 * 
	 * Remove the black dots and border
	 */
	public BufferedImage filterBlack(Image image, BufferedImage bfImage){
		int[][] colors = generateColorMatrix(bfImage, image.getWidth(null), image.getHeight(null));
		ImageFilter blackFilter = new BlackColorFilter(colors);
		FilteredImageSource filteredNewImageSource = new FilteredImageSource(bfImage.getSource(), blackFilter);
		Image newImage = Toolkit.getDefaultToolkit().createImage(filteredNewImageSource);
		BufferedImage filtered = toBufferedImage(newImage);
		return filtered;
    
	}

	public String doOCR(String imagePath, String outputPath){
		String html = "";
		TesseractWrapper tesseractWrapper = new TesseractWrapper(imagePath);
		html = tesseractWrapper.doOCR();
		try {
			FileUtils.writeStringToFile(new File(outputPath), html);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	public String format(String outputPath,String type, String fileName){
		String filteredPath = filterImage(type, fileName);
		String html = doOCR(filteredPath, outputPath);
		return html;
	}


	public int[][] generateColorMatrix(BufferedImage bfImage, int width, int height){
		int[][] colors = new int[width][height];

		for(int i = 0; i < colors.length; i++){

			for(int j = 0; j < colors[i].length; j++){

				if(bfImage.getRGB(i, j) == 0xff000000){
					colors[i][j] = 0;
				}else{
					colors[i][j] = 1;
				}

			}
		}

		return colors;
	}
	
	public int[][] generateColorMatrix2(BufferedImage bfImage, int width, int height){
		int[][] colors = new int[width][height];

		for(int i = 0; i < colors.length; i++){

			for(int j = 0; j < colors[i].length; j++){

				colors[i][j] = bfImage.getRGB(i, j);

			}
		}

		return colors;
	}


	public  BufferedImage rotate90DX(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				biFlip.setRGB(height-1-j, i, bi.getRGB(i, j));
		return biFlip;
	}

	public  BufferedImage toBufferedImage(Image img)
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

	private  String save(BufferedImage image, String ext, String fileName) {

		File file = new File("/Users/veronica/Desktop/" + fileName + "." + ext);
		try {
			ImageIO.write(image, ext, file);  // ignore returned boolean
		} catch(IOException e) {
			System.out.println("Write error for " + file.getPath() +
					": " + e.getMessage());
		}
		return "/Users/veronica/Desktop/" + fileName + "." + ext;
	}       



}


