package Data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Contains the methods to save game data to a file of the type .sodoku
 * 
 * @author Suraj
 * 
 */
public class GameStore {
	/**
	 * Saves the game into a file, given the name of the users choice, into a
	 * local directory named "games"
	 * 
	 * @param tableData
	 * @param name
	 * @throws IOException
	 */
	public static void SaveGame(Cell[][] tableData, String name)
			throws IOException {
		if (!Config.dirFile.exists())
			Config.dirFile.mkdir();
		File outFile = new File(Config.saveDirectory.toString() + "/" + name
				+ Config.fileType);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		saveData(writer, tableData);
		writer.close();
	}

	private static void saveData(BufferedWriter writer, Cell[][] tableData)
			throws IOException {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				Cell temp = tableData[i][j];
				writer.write(i + "," + j + "," + temp.getValue() + ","
						+ temp.isEditable());
				writer.newLine();
			}
		}
	}
}
