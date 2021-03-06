package roguelike;
import java.awt.Color;
public class Potion extends Item{

	public enum Size {SMALL, MEDIUM, LARGE};
	protected Size size;

	public Potion (Size size){
		this.character = (char)5;
		this.color = new Color(255, 200, 200, 220);
		this.size = size; 
	}	

	public Potion () {
		switch(DiceRoller.GetInstance().Roll("1d3")){
		case 1: this.size = Size.SMALL; this.description = "This is a small potion bottle."; break;
		case 2: this.size = Size.MEDIUM; this.description = "This is a medium sized potion bottle."; break;
		case 3: this.size = Size.LARGE; this.description = "This is a large sized potion bottle."; break;
		}
	}

	public int GetHealingPower (){
		switch (size){
		case SMALL: return 10;
		case MEDIUM: return 20;
		case LARGE: return 30;
		default: return 0;
		}
	}

}
