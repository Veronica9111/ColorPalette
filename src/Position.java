
public class Position {

	private Integer top;
	private Integer right;
	private Integer bottom;
	private Integer left;
	
	public Position(Integer top, Integer right, Integer bottom, Integer left){
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getBottom() {
		return bottom;
	}

	public void setBottom(Integer bottom) {
		this.bottom = bottom;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}
	
	//@Override
	public String toString(){
		return this.getTop() + "; " + this.getLeft() + "; " + this.getRight() + "; " + this.getBottom();
	}
	
}
