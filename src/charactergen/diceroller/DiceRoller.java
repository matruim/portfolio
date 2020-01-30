package charactergen.diceroller;

import java.util.ArrayList;
import java.util.Random;

public class DiceRoller {
	
	private static Random random = new Random();
	
	public static int rollD4(int numberOfDice) {
		return random.nextInt(4*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollD6(int numberOfDice) {
		return random.nextInt(6*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollD8(int numberOfDice) {
		return random.nextInt(8*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollD10(int numberOfDice) {
		return random.nextInt(10*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollD12(int numberOfDice) {
		return random.nextInt(12*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollD20(int numberOfDice) {
		return random.nextInt(20*numberOfDice - numberOfDice) + numberOfDice;
	}
	public static int rollPercentage() {
		return random.nextInt(100) + 1;
	}
	public static ArrayList<Integer> roll8Attributes() {
		ArrayList<Integer> attributes = new ArrayList<Integer>();
		
		for(int x : new int[8]) {
			x = rollD6(3);
			if(x > 15)
				x += rollD6(1);
			if(x == 24)
				x+= rollD6(1);
			
			attributes.add(x);
		}
		
		return attributes;
	}

}
