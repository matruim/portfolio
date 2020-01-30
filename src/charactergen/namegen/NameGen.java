package charactergen.namegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class NameGen {
	ArrayList<String> prefixes = new ArrayList<String>();
	ArrayList<String> suffixes = new ArrayList<String>();
	ArrayList<String> mids = new ArrayList<String>();
	
	final private static char[] vowels = {'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'õ', 'ü', 'y'};
	final private static char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y'};
	
	private String filename;
	
	public enum NAMETYPES{
		ELF("resources/elven.txt"),
		FANTASY("resources/fantasy.txt"),
		GOBLIN("resources/goblin.txt"),
		ROMAN("resources/roman.txt");
		
		private String path;
		
		private NAMETYPES(String filepath) {
			this.path = filepath;
		}
		
		public String getFilepath() {
			return path;
		}
		
		public static NAMETYPES getRandom() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}
	
	public NameGen(NAMETYPES type) throws IOException {
		filename = type.getFilepath();
		loadNames();

	}
	
	public void changeNameType(NAMETYPES type) throws IOException {
		filename = type.getFilepath();
		loadNames();
	}
	
	public String getName() {
		String name = "";
		//check to make sure the arrays are loaded with data.
		if(prefixes.size() == 0)
			throw new RuntimeException("You dont have any prefixes for names and those are required update the name file.");
		else if(suffixes.size() == 0)
			throw new RuntimeException("You dont have any suffixes for names and those are required update the name file.");
		
		int expecting = 0;
		int last = 0;
		
		int a = new Random().nextInt(prefixes.size());
		
		last = (endsWithVowel(syllableOnly(prefixes.get(a)))) ? 1 : 2;
		
		expecting = (expectsVowel(prefixes.get(a))) ? 1 : (expectsConsonant(prefixes.get(a))) ? 2 : 0;
		
		
		//create 2 syllable names only for now
		name += syllableOnly(prefixes.get(a));
		
		// make it capital
		name = name.substring(0,1).toUpperCase() + name.substring(1);
		int b = 0;
		
		do {
			b = new Random().nextInt(suffixes.size());
			
			if(((expecting == 1 && startsWithVowel(syllableOnly(suffixes.get(b)))) || 
					(expecting == 2 && startsWithConsonant(syllableOnly(suffixes.get(b)))) && 
					((last == 1 && cantFollowVowels(suffixes.get(b))) || (last == 2 && cantFollowConsonants(suffixes.get(b)) )))) 
				expecting = 0;
			
		}while(expecting != 0);
		
		name += syllableOnly(suffixes.get(b));
		
		return name;
	}
	
	private void loadNames() throws IOException {
		InputStreamReader stream = new InputStreamReader(getClass().getResourceAsStream(filename));
		BufferedReader bread = new BufferedReader(stream);
		
		String temp = "";
		
		while(temp != null) {
			temp = bread.readLine();
			
			if(temp !=null && !temp.equals("")) {
				if(temp.charAt(0) == '-') {
					prefixes.add(temp.substring(1).toLowerCase());
				}else if(temp.charAt(0) == '+') {
					suffixes.add(temp.substring(1).toLowerCase());
				}else {
					mids.add(temp.toLowerCase());
				}
			}
		}
		
		bread.close();
	}
	private boolean endsWithVowel(String s) {
		return (String.copyValueOf(vowels).contains(String.valueOf(s.charAt(s.length() - 1)).toLowerCase()));
	}
	private boolean startsWithVowel(String s) {
		return (String.copyValueOf(vowels).contains(String.valueOf(s.charAt(0)).toLowerCase()));
	}
	private boolean startsWithConsonant(String s) {
		return (String.copyValueOf(consonants).contains(String.valueOf(s.charAt(0)).toLowerCase()));
	}
	private boolean expectsVowel(String s) {
		return (s.substring(1).contains("+v")) ? true : false;
	}
	private boolean expectsConsonant(String s) {
		return (s.substring(1).contains("+c")) ? true : false;
	}
	private boolean cantFollowVowels(String s) {
		return (s.substring(1).contains("-v")) ? true : false;
	}
	private boolean cantFollowConsonants(String s) {
		return (s.substring(1).contains("-c")) ? true : false;
	}
	private String syllableOnly(String s) {
		s = s.trim();
		if(s.charAt(0) == '+' || s.charAt(0) == '-')
			s = s.substring(1);
		return s.split(" ")[0];
	}
	

}
