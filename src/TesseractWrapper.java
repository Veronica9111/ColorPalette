

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.sun.jna.Pointer;

import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;
import net.sourceforge.tess4j.ITessAPI.TessPageIterator;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITessAPI.TessResultIterator;
import net.sourceforge.tess4j.TessAPI1;
import net.sourceforge.tess4j.util.ImageIOHelper;

public class TesseractWrapper {
	
	private String path;
	
	public TesseractWrapper(String path){
		this.path = path;
	}

	public static ByteBuffer readFile(String path) throws IOException{
		FileInputStream f = new FileInputStream( path );
		FileChannel ch = f.getChannel( );
		ByteBuffer bb = ByteBuffer.allocate( 999999 );
		byte[] barray = new byte[999999];
		long checkSum = 0L;
		int nRead, nGet;
		while ( (nRead=ch.read( bb )) != -1 )
		{
		    if ( nRead == 0 )
		        continue;
		    bb.position( 0 );
		    bb.limit( nRead );
		    while( bb.hasRemaining( ) )
		    {
		        nGet = Math.min( bb.remaining( ), 999999 );
		        bb.get( barray, 0, nGet );
		        for ( int i=0; i<nGet; i++ )
		            checkSum += barray[i];
		    }
		    bb.clear( );
		}
		return bb;
	}
	
	public String doOCR() throws IOException{
		/*File imageFile = new File(this.path);
        Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
        instance.setDatapath("/usr/local/share");
        instance.setHocr(true);
        instance.setLanguage("noel+chi_sim");*/
		String datapath =  "/usr/local/share";
				String language =

				"noel+chi_sim";
				String expOCRResult =

				"The (quick) [brown] {fox} jumps!\nOver the $43,456.78 <lazy> #90 dog";
				TessAPI1.TessBaseAPI handle;

				handle = TessAPI1.TessBaseAPICreate();

				System.

				out.println("TessBaseAPIGetIterator");
				String lang =

				"eng";
				File tiff =

				new File(this.path);
				BufferedImage image = ImageIO.read(

				new FileInputStream(tiff)); // require jai-imageio lib to read TIFF
				ByteBuffer buf = ImageIOHelper.convertImageData(image);

				int bpp = image.getColorModel().getPixelSize();
				int bytespp = bpp / 8;
				int bytespl = (int) Math.ceil(image.getWidth() * bpp / 8.0);
				TessAPI1.TessBaseAPIInit3(handle,

				"tessdata", lang);
				TessAPI1.TessBaseAPISetPageSegMode(handle, TessAPI1.TessPageSegMode.

				PSM_AUTO);
				TessAPI1.TessBaseAPISetImage(handle, buf, image.getWidth(), image.getHeight(), bytespp, bytespl);

				TessAPI1.TessBaseAPIRecognize(handle,

				null);
				TessAPI1.TessResultIterator ri = TessAPI1.TessBaseAPIGetIterator(handle);

				TessAPI1.TessPageIterator pi = TessAPI1.TessResultIteratorGetPageIterator(ri);

				TessAPI1.TessPageIteratorBegin(pi);

				System.

				out.println("Bounding boxes:\nchar(s) left top right bottom confidence font-attributes");
				// int height = image.getHeight();

				do {
				Pointer ptr = TessAPI1.TessResultIteratorGetUTF8Text(ri, TessAPI1.TessPageIteratorLevel.

				RIL_WORD);
				String word = ptr.getString(0);

				TessAPI1.TessDeleteText(ptr);

				float confidence = TessAPI1.TessResultIteratorConfidence(ri, TessAPI1.TessPageIteratorLevel.RIL_WORD);
				IntBuffer leftB = IntBuffer.allocate(1);

				IntBuffer topB = IntBuffer.allocate(1);

				IntBuffer rightB = IntBuffer.allocate(1);

				IntBuffer bottomB = IntBuffer.allocate(1);

				TessAPI1.TessPageIteratorBoundingBox(pi, TessAPI1.TessPageIteratorLevel.

				RIL_WORD, leftB, topB, rightB, bottomB);
				int left = leftB.get();
				int top = topB.get();
				int right = rightB.get();
				int bottom = bottomB.get();
				System.

				out.print(String.format("%s %d %d %d %d %f", word, left, top, right, bottom, confidence));
				// System.out.println(String.format("%s %d %d %d %d", str, left, height - bottom, right, height - top)); // training box coordinates

				IntBuffer boldB = IntBuffer.allocate(1);

				IntBuffer italicB = IntBuffer.allocate(1);

				IntBuffer underlinedB = IntBuffer.allocate(1);

				IntBuffer monospaceB = IntBuffer.allocate(1);

				IntBuffer serifB = IntBuffer.allocate(1);

				IntBuffer smallcapsB = IntBuffer.allocate(1);

				IntBuffer pointSizeB = IntBuffer.allocate(1);

				IntBuffer fontIdB = IntBuffer.allocate(1);

				String fontName = TessAPI1.TessResultIteratorWordFontAttributes(ri, boldB, italicB, underlinedB,

				monospaceB, serifB, smallcapsB, pointSizeB, fontIdB);

				boolean bold = boldB.get() == TessAPI1.TRUE;
				boolean italic = italicB.get() == TessAPI1.TRUE;
				boolean underlined = underlinedB.get() == TessAPI1.TRUE;
				boolean monospace = monospaceB.get() == TessAPI1.TRUE;
				boolean serif = serifB.get() == TessAPI1.TRUE;
				boolean smallcaps = smallcapsB.get() == TessAPI1.TRUE;
				int pointSize = pointSizeB.get();
				int fontId = fontIdB.get();
				System.

				out.println(String.format(" font: %s, size: %d, font id: %d, bold: %b," +
				" italic: %b, underlined: %b, monospace: %b, serif: %b, smallcap: %b",
				fontName, pointSize, fontId, bold, italic, underlined, monospace, serif, smallcaps));

				}

				while (TessAPI1.TessPageIteratorNext(pi, TessAPI1.TessPageIteratorLevel.RIL_WORD) == TessAPI1.TRUE);
				
				
		
        
        String result = "";
      /*  try {
            result = instance.doOCR(imageFile);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }*/
        return result;
	}

}