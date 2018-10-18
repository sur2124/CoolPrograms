/**
 * 
 */
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * @author Suraj Kulkarni
 * 
 */
public class AbreviationToTranslation {
	static final String dictionary = "dictionary.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		boolean error = true;
		Scanner scanFile = null;
		String fileName = "";
		while (error) {
			fileName = JOptionPane
					.showInputDialog("What is the name of the file?\nREMEMBER TO TYPE .txt !!!");
			if (fileName.length() == 0) {
				JOptionPane
						.showMessageDialog(
								null,
								"Please enter the filename with the text to be translated.\nIt would be nice if it was in .txt format ;)");
				error = true;
				break;
			}
			try {
				scanFile = new Scanner(new File(fileName));
				error = false;
			} catch (FileNotFoundException e) {
				JOptionPane
						.showMessageDialog(null,
								"File Name does not exist\nPlease verify that file exists");
				error = true;
			}
		}
		if (!scanFile.hasNext()) {
			JOptionPane
					.showMessageDialog(null,
							"File is empty, please insert the text you want unabbreviated");
			System.exit(0);
		}
		String outFileName = "new_" + fileName;
		FileWriter fstream = new FileWriter(outFileName);
		BufferedWriter out = new BufferedWriter(fstream);
		while (scanFile.hasNextLine()) {
			String line = scanFile.nextLine();
			Scanner scanLine = new Scanner(line);
			while (scanLine.hasNext()) {
				String word = scanLine.next();
				out.write(getTranslation(word));
			}
			out.newLine();
		}
		out.close();
		JOptionPane.showMessageDialog(null, "ALL DONE!\nPlease look at "
				+ outFileName + " for the translated data.");
	}

	/**
	 * 
	 * @param word
	 * @return
	 */
	public static String getTranslation(String word) {
		Scanner scanAcroy = null;
		word.trim();
		char endOfWord = ' ';
		if (word.endsWith("!") || word.endsWith(".") || word.endsWith(",")
				|| word.endsWith("?") || word.endsWith(";")|| word.endsWith(":")) {
			endOfWord = word.charAt(word.length() - 1);
			word = word.substring(0, word.length() - 1);
		}
		try {
			scanAcroy = new Scanner(new File(dictionary));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Dictionary file does not exist!");
			System.exit(0);
		}
		while (scanAcroy.hasNextLine()) {
			String line = scanAcroy.nextLine();
			Scanner scanLine = new Scanner(line);
			String PosWord = scanLine.next();
			if (PosWord.equalsIgnoreCase(word)) {
				String ans;
				if (endOfWord == ' ')
					ans = line.substring(PosWord.length() + 1, line.length());
				else
					ans = line.substring(PosWord.length() + 1, line.length())
							+ endOfWord;
				ans.trim();
				return ans + " ";
			}

		}
		if (endOfWord == ' ')
			return word + " ";
		else
			return word + endOfWord + " ";
	}
}
