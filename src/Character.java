
public class Character {

	private String text;
	private Position position;
	
	public Character(String text, Position position){
		this.text = text;
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public String toString(){
		return this.text + " [" + this.position.toString() + "]";
	}
	
}
