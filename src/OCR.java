import java.io.IOException;

public class OCR {
	public static void main(String[] args) throws IOException {   
		TesseractWrapper tw = new TesseractWrapper("/Users/veronica/Desktop/new3/noel.icr.exp110.tif");
		String result = tw.doOCR();
		System.out.println(result);
	}
}
