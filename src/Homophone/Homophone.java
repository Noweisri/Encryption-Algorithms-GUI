package Project;


import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.Font;
import java.awt.Frame;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Random;
import java.util.function.BiConsumer;
import javax.swing.*;


public class Homophone {
	
	private static Hashtable<String, ArrayList<String>> table; // the Homophonic table, which the actual encryption sets on, is allocated to this hash table.
	
	
	public Homophone() {

			table = new Hashtable<String, ArrayList<String>>(52);
			
			/* Because converting between cases is weirdly difficult in Java, we decided to store 
			 * both lower-case and upper-case English letters in this hash table */
			ArrayList<String> LetterMap = new ArrayList<String>();
			
			
			
			// These are the upper-case mappings
			LetterMap.add("D");
			LetterMap.add("9");
			table.put("A", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("X");
			table.put("B", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("S");
			table.put("C", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("F");
			table.put("D", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("Z");
			LetterMap.add("7");
			LetterMap.add("2");
			LetterMap.add("1");
			table.put("E", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("E");
			table.put("F", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("H");
			table.put("G", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("C");
			table.put("H", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("V");
			LetterMap.add("3");
			table.put("I", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("I");
			table.put("J", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("T");
			table.put("K", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("P");
			table.put("L", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("G");
			table.put("M", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("A");
			LetterMap.add("5");
			table.put("N", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("Q");
			LetterMap.add("0");
			table.put("O", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("L");
			table.put("P", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("K");
			table.put("Q", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("J");
			table.put("R", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("R");
			LetterMap.add("4");
			table.put("S", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("U");
			LetterMap.add("6");
			table.put("T", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("O");
			table.put("U", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("W");
			table.put("V", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("M");
			table.put("W", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("Y");
			table.put("X", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("B");
			table.put("Y", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("N");
			table.put("Z", LetterMap);
			
			
			
			
			
			
			
			
			// And these are the lower-case mappings.
			LetterMap.add("d");
			LetterMap.add("9");
			table.put("a", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("x");
			table.put("b", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("s");
			table.put("c", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("f");
			table.put("d", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("z");
			LetterMap.add("7");
			LetterMap.add("2");
			LetterMap.add("1");
			table.put("e", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("e");
			table.put("f", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("h");
			table.put("g", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("c");
			table.put("h", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("v");
			LetterMap.add("3");
			table.put("i", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("i");
			table.put("j", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("t");
			table.put("k", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("p");
			table.put("l", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("g");
			table.put("m", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("a");
			LetterMap.add("5");
			table.put("n", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("q");
			LetterMap.add("0");
			table.put("o", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("l");
			table.put("p", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("k");
			table.put("q", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("j");
			table.put("r", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("r");
			LetterMap.add("4");
			table.put("s", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("u");
			LetterMap.add("6");
			table.put("t", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("o");
			table.put("u", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("w");
			table.put("v", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("m");
			table.put("w", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("y");
			table.put("x", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("b");
			table.put("y", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("n");
			table.put("z", LetterMap);
			
			
			
			
			
			
			
			
			// These are some punctuation marks and whitespaces.
			LetterMap = new ArrayList<String>();
			LetterMap.add(" ");
			table.put(" ", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("?");
			table.put("?", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("!");
			table.put("!", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(",");
			table.put(",", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(".");
			table.put(".", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("\t");
			table.put("\t", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("%");
			table.put("%", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("&");
			table.put("&", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("~");
			table.put("~", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("`");
			table.put("`", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("-");
			table.put("-", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("+");
			table.put("+", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("_");
			table.put("_", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(")");
			table.put(")", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("(");
			table.put("(", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("@");
			table.put("@", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("'");
			table.put("'", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("\"");
			table.put("\"", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("[");
			table.put("[", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("]");
			table.put("]", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("{");
			table.put("{", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("}");
			table.put("}", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(";");
			table.put(";", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("<");
			table.put("<", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(">");
			table.put(">", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("=");
			table.put("=", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add(":");
			table.put(":", LetterMap);
			
			// And here are the decimal numbers
			LetterMap = new ArrayList<String>();
			LetterMap.add("0");
			table.put("0", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("1");
			table.put("1", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("2");
			table.put("2", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("3");
			table.put("3", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("4");
			table.put("4", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("5");
			table.put("5", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("6");
			table.put("6", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("7");
			table.put("7", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("8");
			table.put("8", LetterMap);
			
			LetterMap = new ArrayList<String>();
			LetterMap.add("9");
			table.put("9", LetterMap);
			
			
			
	    
		
	}
	
	public static void encrypt(StringBuilder plaintext, StringBuilder ciphertext) {
		for(int i = 0; i < plaintext.length(); i++) {
			int codePoint = plaintext.codePointAt(i);
			String letter = Character.toString(codePoint);
			int possibleMappings = table.get(letter).size();
			Random mappedLetter = new Random(); // we constructed this pseudorandom generator to encrypt each plain-letter into a random cipher-letter
			if(possibleMappings > 1) {
				int chosen = mappedLetter.nextInt(possibleMappings);
				letter = table.get(letter).get(chosen);
			} else
				letter = table.get(letter).get(0);
			ciphertext.append(letter);
		}
	}
	
	public static void decrypt(StringBuilder ciphertext, StringBuilder plaintext, ArrayList<String> capitalizedBeforeEncryption, 
			ArrayList<Boolean> digitBeforeEncryption) {
		for(int i = 0; i < ciphertext.length(); i++) {
			String cipherLetter = Character.toString(ciphertext.codePointAt(i));
			String wasCapitalized = capitalizedBeforeEncryption.get(i);
			boolean wasDigit = digitBeforeEncryption.get(i);
			Enumeration<String> originalLetters = table.keys();
			while(originalLetters.hasMoreElements()) { // this loop represents each plaintext letter
				String plainLetter = originalLetters.nextElement();
				ArrayList<String> mappedLetters = table.get((Object) plainLetter);
				String discoveredLetter = null; // we want to break out of the inner for loop here so that letters with multiple possibilities are not duplicated.
				for(String lc : mappedLetters) // and this represents the ArrayList<String> of the possible ciphertext letters for each plaintext letter
					if(cipherLetter.contentEquals(lc)) {
						if(wasCapitalized.contentEquals("true")) {
							if(wasDigit) {
								plaintext.append(cipherLetter);
								discoveredLetter = cipherLetter;
							} else {
								plaintext.append(plainLetter.toUpperCase());
								discoveredLetter = plainLetter.toUpperCase();
							}
							break;
						}
						
						else if(wasCapitalized.contentEquals("false")) {
							if(wasDigit) {
								plaintext.append(cipherLetter);
								discoveredLetter = cipherLetter;
							} else {
								plaintext.append(plainLetter.toLowerCase());
								discoveredLetter = plainLetter.toLowerCase();
							}
							break;
						}
							
						else if(wasCapitalized.contentEquals("null")) {
							if(wasDigit) {
								plaintext.append(cipherLetter);
								discoveredLetter = cipherLetter;
							} else {
								plaintext.append(plainLetter);
								discoveredLetter = plainLetter;
							}
							break;
						}
					}
				
				if(discoveredLetter != null)
					break;
			}
		}
	}
	
}
