

import java.io.File;
import net.sourceforge.tess4j.*;

public class TesseractWrapper {
	
	private String path;
	
	public TesseractWrapper(String path){
		this.path = path;
	}

	public String doOCR(){
		File imageFile = new File(this.path);
        Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
        String value = System.getenv("TESSDATA_PREFIX");
        instance.setDatapath("/usr/local/share");
        instance.setHocr(true);
        instance.setLanguage("vie+eng");
        String result = "";
        try {
            result = instance.doOCR(imageFile);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return result;
	}

}