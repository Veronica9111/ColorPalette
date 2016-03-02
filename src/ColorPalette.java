import java.util.List;

public class ColorPalette {

	public static void main(String[] args) {    
		String input = "/Users/veronica/Desktop/i2.tif";
		String output = "/Users/veronica/Desktop/generated.html";
		String fileName = "ii2";
		String type = "tif";
		ImageFormatter imageFormatter = new ImageFormatter(input);
		String html = imageFormatter.format(output, type, fileName);
		Analyzer analyzer = new Analyzer(html);
		System.out.println("hello");
		List<Character> characters = analyzer.getCharactersFromHTML();
		for(Character character : characters){
			System.out.println(character.getText());
			System.out.println(character.getPosition());
		}
    }
	
}
