import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test {

	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("/Users/veronica/Downloads/00746.tif") );
		File file = new File("/Users/veronica/Desktop/hello2" + "." + "png");
		try {
			ImageIO.write(image, "png", file);  // ignore returned boolean
		} catch(IOException e) {
			System.out.println("Write error for " + file.getPath() +
					": " + e.getMessage());
		}

	}

}
