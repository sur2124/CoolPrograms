package Data;

import java.io.File;

import javax.swing.JTable;

/**
 * Contains all the constants which are used by the game. These constants may be
 * changed by the User to further customize the game.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Config {
	public static final String[] columnNames = { "1", "2", "3", "4", "5", "6",
			"7", "8", "9" };
	public static final String saveDirectory = "games/";
	public static final File dirFile = new File(saveDirectory);
	public static final int easySetting = 40;
	public static final String fileType = ".sodoku";
	public static final int NumberOfRowsColumns = 9;
	public static Cell[][] gameData = new Cell[NumberOfRowsColumns][NumberOfRowsColumns];
	public static final int hardSetting = 20;
	public static final int height = 700;
	public static final int mediumSetting = 27;
	public static final int initialNumberOfGivens = mediumSetting;
	public static final int ROW_HEIGHT = 90;
	public static final String Sodoku_Application_Name = "Sodoku";
	public static final String Sodoku_NAME = "Suraj Kulkarni's Sodoku Game";
	public static final int width = 900;

	/**
	 * Convenience method to convert Cell array to a int array
	 * 
	 * @param tableData
	 * @return
	 */
	public static int[][] convertToIntArray(Cell[][] tableData) {
		int[][] data = new int[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				if (tableData[i][j] == null)
					data[i][j] = 0;
				else
					data[i][j] = tableData[i][j].getValue();
			}
		}
		return data;
	}

	/**
	 * Convenience method to convert JTable to a Cell Array
	 * 
	 * @param JTable
	 * @return Cell[][]
	 */
	public static Cell[][] convertJtableIntoCellArray(JTable tblSodoku) {
		Cell[][] data = new Cell[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				Object value = tblSodoku.getValueAt(i, j);
				System.out.println(value);
				data[i][j] = new Cell();
				if (value == null || value == "") {
					data[i][j].setValue(0);
				} else {
					try {
						data[i][j].setValue((Integer) value);
					} catch (ClassCastException e) {
						try {
							data[i][j].setValue(Integer
									.parseInt((String) value));
						} catch (Exception x) {
							data[i][j].setValue(0);
						}
					}

				}
				data[i][j].setEditable(tblSodoku.isCellEditable(i, j));
			}
		}
		return data;
	}

}
