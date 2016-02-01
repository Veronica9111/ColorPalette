
public class OCR {
	public static void main(String[] args) {   
		TesseractWrapper tw = new TesseractWrapper("/Users/veronica/Desktop/wordslist/2.tif");
		String result = tw.doOCR();
		System.out.println(result);
	}
}
