import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Analyzer {

	 private String html;
	
	  private static Position formatAttribute(String input){
		  String pattern = "(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)";
		  Pattern r = Pattern.compile(pattern);

	      Matcher m = r.matcher(input);
	      Position position = null;
	      if(m.find()){
	    	  Integer left = Integer.parseInt(m.group(1));
	    	  Integer top = Integer.parseInt(m.group(2));
	    	  Integer right = Integer.parseInt(m.group(3));
	    	  Integer bottom = Integer.parseInt(m.group(4));
		      position = new Position(top, right, bottom, left);
	      }

		return position;
		  
		  
	  }
	  
	  public Analyzer(String html){
		  this.html = html;
	  }
	  
	  public List<Character> getCharactersFromHTML(){
		  List<Character> characters = new ArrayList<>();
			Document doc = Jsoup.parse(this.html, "UTF-8");

			Elements spans = doc.select("span"); // a with href
			
			for(Element span : spans){
				if(span.childNodes().size() > 1){

					continue;
				}


				Position position = formatAttribute(span.attr("title"));
				Character character = new Character(span.text(), position);
				characters.add(character);
			}
			return characters;
	  }

}
