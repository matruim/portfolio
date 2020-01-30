package charactergen;

import java.io.IOException;
import java.util.ArrayList;

import charactergen.diceroller.DiceRoller;
import charactergen.namegen.NameGen;
import charactergen.namegen.NameGen.NAMETYPES;

public class Character {
	String name = "";
	ArrayList<Integer> attributes = DiceRoller.roll8Attributes();
	String sex, alignment,race,occ,landOfOrigin,environment,background,hostilities,disposition;
	int height, weight, age;
	
	public enum Gender{MALE, FEMALE}
	
	public Character() {
		setGender();
		setRace();
		setAlignment();
		setAge();
		setEnvironment();
		setLandOfOrigin();
		setBackground();
		setHostilities();
		setDisposition();
		setOCC();
	}
	public Character(String name) {
		this();
		this.setName(name);
	}
	public Character(String name,Gender gender) {
		this(name);
		this.setGender(gender);
	}


	private void setName(NAMETYPES type) {
		try {
			name = new NameGen(type).getName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// If no name exit 
		if(name.equals("")) {
			System.exit(1);
		}
	}
	private void setName(String name) {
		this.name = name;
	}
	private void setRace() {
		int roll = DiceRoller.rollPercentage();
		if (isBetween(roll,1,60)) {
			race = "Human";
			setName(NAMETYPES.ROMAN);
		}
		else if (isBetween(roll,61,80)) {
			race = "Dwarf";
			setName(NAMETYPES.FANTASY);
			height = height-18;
		}
		else {
			race = "Elf";
			setName(NAMETYPES.ELF);
			weight = 120 +  DiceRoller.rollD10(6);
		}
	}
	private void setGender() {
		if(DiceRoller.rollPercentage() < 50) {
			sex = "Male";
			height = 66 + DiceRoller.rollD12(1);
			weight = 130 + DiceRoller.rollD20(8);
		}
		else {
			sex = "Female";
			height = 60 + DiceRoller.rollD12(1);
			weight = 90 + DiceRoller.rollD20(7);
		}
	}
	private void setGender(Gender sex) {
		if(sex.equals(Gender.MALE)) {
			this.sex = "Male";
			height = 66 + DiceRoller.rollD12(1);
			weight = 130 + DiceRoller.rollD20(8);
		}else {
			this.sex = "Female";
			height = 60 + DiceRoller.rollD12(1);
			weight = 90 + DiceRoller.rollD20(7);
		}
			
	}
	private void setAlignment() {
		int roll = DiceRoller.rollPercentage();
		if(isBetween(roll,1,45)) {
			//Good Alignment
			roll = DiceRoller.rollPercentage();
			if(isBetween(roll,1,40))
				alignment = "Principled";
			else
				alignment = "Scrupulous";
		}else if(isBetween(roll,45,85)) {
			//Selfish Alignment
			roll = DiceRoller.rollPercentage();
			if(isBetween(roll,1,50))
				alignment = "Unprincipled";
			else
				alignment = "Anarchist";
		}else {
			//Evil Alignment
			roll = DiceRoller.rollPercentage();
			if(isBetween(roll,1,45))
				alignment = "Miscreant";
			else if(isBetween(roll,45,90))
				alignment = "Aberrant";
			else
				alignment = "Diabolic";
		}
	}
	private void setAge() {
		age = 17 + DiceRoller.rollD8(1);
	}
	private void setEnvironment() {
		int roll = DiceRoller.rollPercentage();
		if(isBetween(roll,1,20))
			environment = "Farm Community";
		else if(isBetween(roll,21,40))
			environment = "Wilderness Town";
		else if(isBetween(roll,41,60))
			environment = "Small City";
		else if(isBetween(roll,61,80))
			environment = "Medium City";
		else 
			environment = "Capital City";
	}
	private void setLandOfOrigin() {
		int roll = DiceRoller.rollPercentage();
		if(isBetween(roll,1,20))
			landOfOrigin = "Eastern Territory";
		else if(isBetween(roll,21,40))
			landOfOrigin = "Land of the South Winds";
		else if(isBetween(roll,41,70))
			landOfOrigin = "Western Empire";
		else if(isBetween(roll,71,90))
			landOfOrigin = "Bizantium";
		else 
			landOfOrigin = "Old Kingdoms";
	}
	private void setBackground() {
		
	}
	private void setHostilities() {
		
	}
	private void setDisposition() {
		int roll = DiceRoller.rollPercentage();
		if(isBetween(roll,1,10))
			disposition = "Mean, suspicious, vengeful.";
		else if(isBetween(roll,11,15))
			disposition = "Shy, timid, tends to be a loner.";
		else if(isBetween(roll,16,20))
			disposition = "Gung-ho, guts and glory type who sees himself as a hero. Likes combat.";
		else if(isBetween(roll,21,25))
			disposition = "Worry wart, nervous and cautious.";
		else if(isBetween(roll,26,37))
			disposition = "Hot-head, quick-tempered, emotional, but basically nice.";
		else if(isBetween(roll,38,45))
			disposition = "Schemer; gambler who likes to take chances.";
		else if(isBetween(roll,46,50))
			disposition = "Blabber-mouth, nice guy, but too talkative.";
		else if(isBetween(roll,51,56))
			disposition = "Wild man, cocky, overconfident, takes unnecessary risks.";
		else if(isBetween(roll,57,66))
			disposition = "Nice guy, friendly, courteous and hospitable.";
		else if(isBetween(roll,67,76))
			disposition = "Snob, arrogant, feels superior to others.";
		else if(isBetween(roll,77,84))
			disposition = "Tough guy, self-reliant, cocky, a lone wolf.";
		else if(isBetween(roll,85,89))
			disposition = "Paternal, overbearing, overprotective of others, especially young characters.";
		else if(isBetween(roll,90,94))
			disposition = "Complainer, constantly aggravated about something.";
		else
			disposition = "Paranoid, trusts no one.";
	}
	private void setOCC() {occ = "";}
	
	private boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}
	
	private String feetAndInches(int x) {
		String temp = "";
		temp = "" + x / 12 + "' " + x % 12 + "\"";
		return temp;
	}
	
	public String toString() {
		
		StringBuilder strb = new StringBuilder("");
		strb.append("Name: " + name + "\n");
		strb.append("Race: " + race+ "\n");
		strb.append("O.C.C.: " + occ + "\n");
		strb.append("Level: " + 1 + "\t Experience: " + 0 + "\n");
		strb.append("Alignment: " + alignment + "\n");
		strb.append("Age: " + age + "\tSex: " + sex + "\n");
		strb.append("Height: " + feetAndInches(height) + "\tWeight: " + weight + "\n");
		strb.append("Land Of Origin: " + landOfOrigin + "\n");
		strb.append("Environment: " + environment + "\n");
		strb.append("Background: " + background + "\n");
		strb.append("Racial Hostilities: " + hostilities + "\n");
		strb.append("Disposition: " + disposition + "\n");
		strb.append("" + "\n");
		strb.append("IQ: "+ attributes.get(0) + "\n");
		strb.append("ME: "+ attributes.get(1) + "\n");
		strb.append("MA: "+ attributes.get(2) + "\n");
		strb.append("PS: "+ attributes.get(3) + "\n");
		strb.append("PE: "+ attributes.get(4) + "\n");
		strb.append("PP: "+ attributes.get(5) + "\n");
		strb.append("PB: "+ attributes.get(6) + "\n");
		strb.append("Spd: "+ attributes.get(7) + "\n");
		
		return strb.toString();
	}

	
}
