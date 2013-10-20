package roguelike;

public class Rectangle {
	private int Left;
	private int Top;
	private int Right;
	private int Bottom;
	
	public Rectangle(int Left, int Top, int Right, int Bottom) {
		this.Left = Left;
		this.Top = Top;
		this.Right = Right;
		this.Bottom = Bottom;
	}
	
	public int Left() {
		return Left;
	}
	
	public int Top() {
		return Top;
	}
	public int Right() {
		return Right;
	}
	
	public int Bottom() {
		return Bottom;
	}
	
	public int Width() {
		return Right-Left;
	}
	
	public int Height() {
		return Bottom-Top;
	}
	
	public int XCenter() {
		return Left + (Width()/2);
	}
	
	public int YCenter() {
		return Top + (Height()/2);
	}
	
	public void TL(Integer x, Integer y)	{
		x = this.Left;
		y = this.Top;
	}
	
	public void BR(Integer x, Integer y) {
		x = this.Right;
		y = this.Bottom;
	}
	
	public void Center(Integer x, Integer y) {
		x = XCenter();
		y = YCenter();
	}
}
