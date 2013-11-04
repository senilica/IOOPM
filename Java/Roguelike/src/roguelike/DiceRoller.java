package roguelike;
import java.util.Random;

public class DiceRoller {
	private Random Dice;
	
	DiceRoller(Random Dice) {
		this.Dice = Dice;
	}
	
	public static int Roll(String roll) {
		if((!roll.contains("d") && !roll.contains("D")) && !(roll.contains("d") && roll.contains("D")))
			return 0;
		Random Dice = new Random ();
		Integer numDice = null;
		Integer diceMax = null;
		Integer modifier = null;
		if(roll.contains("d")) {
			int i = roll.indexOf('d');
			numDice = Integer.valueOf(roll.substring(0, i));
			if(!roll.contains("+")) {
				diceMax = Integer.valueOf(roll.substring(i+1, roll.length()));
				modifier = 0;
			}
			else {
				int j = roll.indexOf('+');
				diceMax = Integer.valueOf(roll.substring(i+1, j));
				modifier = Integer.valueOf(roll.substring(j, roll.length()));
			}
		}
		else {
			int i = roll.indexOf('D');
			numDice = Integer.valueOf(roll.substring(0, i));
			if(!roll.contains("+")) {
				diceMax = Integer.valueOf(roll.substring(i+1, roll.length()));
				modifier = 0;
			}
			else {
				int j = roll.indexOf('+');
				diceMax = Integer.valueOf(roll.substring(i+1, j));
				modifier = Integer.valueOf(roll.substring(j, roll.length()));
			}			
		}
		int ret = 0;
		for(int i = 0; i < numDice; i++)
			ret += Dice.nextInt(diceMax-1) + 1;
		ret += modifier;
		return ret;
	}
}
