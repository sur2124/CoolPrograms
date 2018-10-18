package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Contains the code necessary to read data from a file and load it into the
 * gui.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class GameLoader {
	/**
	 * Reads in a file of the type .sodoku and reads it into a two-dimensional
	 * Cell array.
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Cell[][] getData(File file) throws FileNotFoundException {
		Cell[][] tableData = new Cell[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
		Scanner scanFile = new Scanner(file);
		while (scanFile.hasNextLine()) {
			String line = scanFile.nextLine();
			String[] lineData = line.split(",");
			int i = Integer.parseInt(lineData[0]);
			int j = Integer.parseInt(lineData[1]);
			int value = Integer.parseInt(lineData[2]);
			String editable = lineData[3].trim();
			Cell temp;
			if (editable.equalsIgnoreCase("true"))
				temp = new Cell(value, true);
			else
				temp = new Cell(value, false);
			tableData[i][j] = temp;
		}
		return tableData;
	}

}
