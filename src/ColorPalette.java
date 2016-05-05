import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ColorPalette {

	public static void main(String[] args) throws IOException {    
		//Integer count = 1;
		
		Files.walk(Paths.get("/Users/veronica/Downloads/dinge2")).forEach(filePath -> {
			
		
		    if (filePath.toString().contains("tif")) {
		String input = filePath.toString();
		System.out.println(input);
		String output = "/Users/veronica/Desktop/generated.html";
	
		String fileName = "b" + filePath.getFileName();
		String type = "tif";
		ImageFormatter imageFormatter = new ImageFormatter(input);

		try {
			String html = imageFormatter.format(output, type, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		    }
		/*Analyzer analyzer = new Analyzer(html);
		System.out.println("hello");
		List<Character> characters = analyzer.getCharactersFromHTML();
		for(Character character : characters){
			System.out.println(character.getText());
			System.out.println(character.getPosition());
		}*/
		    });
    }
	
}
