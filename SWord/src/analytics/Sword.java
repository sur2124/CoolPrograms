package analytics;

import gui.GUI;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Suraj
 * 
 */
public class Sword {
	/**
	 * @param args
	 */
	private static GUI gui;
	private static String lastWord;
	private static ArrayList<String> Word_List;
	private static Tools tool;

	public static void main(String[] args) {
		lastWord = "";
		tool = new Tools();
		try {
			Word_List = tool.loadWordList();
			gui = new GUI();
		} catch (Exception e) {
			System.out.println(e);
			gui = new GUI();
			gui.cleanPrint("");
			gui.printError("Error Loading Word List. Please redownload the program!");
			gui.print(e);
		}
	}

	public static void run() {

		try {
			String word = JOptionPane.showInputDialog(gui.getFrame(),
			        "Letters in the Scrabble Word:", lastWord);
			word.toLowerCase();
			word.trim();
			boolean validWord = true;
			for (char letter : word.toCharArray()) {
				if (!Character.isLetter(letter)) {
					if (letter != '?') {
						gui.printHelp();
						gui.printError("Invalid Character Entered!");
						validWord = false;
					}
				}
			}
			if (validWord) {
				lastWord = word;
				ArrayList<String> wordList = tool.getPossibleWords(word,
				        Word_List);
				printWordList(wordList, word);
			}
		} catch (Exception e) {
			gui.printHelp();
		}
	}

	private static void printWordList(ArrayList<String> wordList, String word) {

		String output = "Possible Words that can be Generated from " + word
		        + " are:\n";
		for (int i = word.length(); i > 0; i--) {
			String title = "Words of size " + (i + 1) + ":";
			String words = "";
			for (String posWord : wordList) {
				if (posWord.length() == (i + 1)) {
					words = words + "\t" + posWord + "\n";
				}
			}

			if (!words.trim().isEmpty()) {
				output = output + "\n" + title + "\n" + words;
			}
		}
		gui.cleanPrint(output);
	}

}