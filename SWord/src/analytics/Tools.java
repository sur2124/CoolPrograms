package analytics;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Tools {

	public ArrayList<String> getPossibleWords(String word,
	        ArrayList<String> WordList) {
		int anonCount = countOccurrences(word, '?');
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < WordList.size(); i++) {

			ArrayList<String> wordSet = new ArrayList<String>();
			for (char charWord : word.toCharArray()) {
				wordSet.add(charWord + "");
			}
			ArrayList<String> posWordSet = new ArrayList<String>();
			for (char charPosWord : WordList.get(i).toCharArray()) {
				posWordSet.add(charPosWord + "");
			}

			boolean valid = intersect(posWordSet, wordSet, anonCount);
			if (valid)
				result.add(WordList.get(i));
		}
		return result;
	}

	private static boolean intersect(ArrayList<String> posWordSet,
	        ArrayList<String> wordSet, int anonCount) {
		if (posWordSet.size() - anonCount > wordSet.size())
			return false;
		for (int i = 0; i < wordSet.size(); i++) {
			posWordSet.remove(wordSet.get(i));
		}
		if (posWordSet.size() > anonCount)
			return false;
		return true;

	}

	private static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

	public ArrayList<String> loadWordList() throws Exception {
		HashSet<String> wordList = new HashSet<String>();
		InputStream is = this.getClass().getResourceAsStream(
		        "resources/SOWPODS.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		while ((line = br.readLine()) != null) {
			wordList.add(line.trim().toLowerCase());
		}
		is = this.getClass().getResourceAsStream("resources/TWL06.txt");
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		line = null;
		while ((line = br.readLine()) != null) {
			wordList.add(line.trim().toLowerCase());
		}

		ArrayList<String> completeWordList = new ArrayList<String>();
		completeWordList.addAll(wordList);
		Collections.sort(completeWordList, new WordComparator());
		return completeWordList;
	}

	public static class WordComparator implements Comparator<String> {
		public int compare(String o1, String o2) {
			if (o1.length() > o2.length()) {
				return 1;
			} else if (o1.length() < o2.length()) {
				return -1;
			} else {
				return o1.compareTo(o2);
			}
		}
	}

}
