package charactergen;

import java.io.IOException;
import java.util.ArrayList;

import charactergen.diceroller.DiceRoller;
import charactergen.namegen.*;
import charactergen.namegen.NameGen.NAMETYPES;

public class Main {

	public static void main(String[] args) {
		String name = "";
		ArrayList<Integer> attributes = DiceRoller.roll8Attributes();
		
		try {
			name = new NameGen(NAMETYPES.getRandom()).getName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// If no name exit 
		if(name.equals("")) {
			System.exit(1);
		}
		
		
	}

}
