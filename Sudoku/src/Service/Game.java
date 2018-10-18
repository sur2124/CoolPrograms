package Service;

import java.util.Random;

import Data.Cell;
import Data.Config;
import GUI.Gui;

/**
 * Provides all the logic required to analyze and play the game.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Game {
	@SuppressWarnings("unused")
	private static int[][] data = new int[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];

	/**
	 * Checks whether the given solution to the sodoku puzzle is correct
	 * 
	 * @param tableData
	 * @return boolean
	 */
	public static boolean checkSolution(Cell[][] tableData) {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				if (tableData[i][j] == null)
					return false;
				int number = tableData[i][j].getValue();
				tableData[i][j].setValue(0);
				if (!isValidMove(i, j, number,
						Config.convertToIntArray(tableData))) {
					return false;
				} else
					tableData[i][j].setValue(number);
			}
		}
		return true;
	}

	/**
	 * Clears the game data except for the givens
	 * 
	 * @param tableData
	 * @return Cell[][]
	 */
	public static Cell[][] ClearBoard(Cell[][] tableData) {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				if (tableData[i][j] == null || tableData[i][j].isEditable()) {
					tableData[i][j] = new Cell();
				}
			}
		}
		return tableData;
	}

	/**
	 * Generates a new game and posts to the Gui
	 * 
	 * @param tableData
	 * @param numOfGivens
	 * @param gui
	 */
	public static void generateNewGame(int numOfGivens, Gui gui) {
		Random randomGen = new Random((new Random()).nextLong());
		gui.clearBoard();
		Cell[][] data = new Cell[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
		generateBoard(FixTable(data), randomGen, numOfGivens, gui);
	}

	public static boolean isValidMove(int row, int column, int value,
			int[][] cells) {
		for (int k = 0; k < Config.NumberOfRowsColumns; ++k)
			if (value == cells[k][column])
				return false;

		for (int k = 0; k < Config.NumberOfRowsColumns; ++k)
			if (value == cells[row][k])
				return false;

		int boxRowOffset = (row / 3) * 3;
		int boxColOffset = (column / 3) * 3;
		for (int k = 0; k < 3; ++k)
			for (int m = 0; m < 3; ++m)
				if (value == cells[boxRowOffset + k][boxColOffset + m])
					return false;
		return true;
	}

	/**
	 * Solves the game if it can be solved, and returns the solution
	 * 
	 * @param tableData
	 * @param postToGui
	 * @param gui
	 * @return boolean
	 */
	public static boolean Solve(Cell[][] tableData, boolean postToGui,
			boolean isEditable, Gui gui) {
		tableData = FixTable(tableData);
		int[][] data = Config.convertToIntArray(tableData);
		boolean solved = solve(0, 0, data);
		if (solved) {
			if (postToGui)
				gui.postGuiWithData(data, isEditable);
			return true;
		} else
			return false;
	}

	/**
	 * Convenience method to write zeros to all empty cells of the gui
	 * 
	 * @param tableData
	 * @return
	 */
	public static Cell[][] WriteZerosToTable(Cell[][] tableData) {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				if (tableData[i][j] == null)
					tableData[i][j] = new Cell(0, true);
			}
		}
		return tableData;
	}

	private static Cell[][] FixTable(Cell[][] tableData) {
		return WriteZerosToTable(ClearBoard(tableData));
	}

	private static void generateBoard(Cell[][] data, Random randomGen,
			int minimum, Gui gui) {
		gui.clearBoard();
		int count = 0;
		while (count < minimum) {
			int row = randomGen.nextInt(Config.NumberOfRowsColumns);
			int col = randomGen.nextInt(Config.NumberOfRowsColumns);
			int value = getValidRandomNumber(randomGen);
			if (isValidMove(row, col, value, Config.convertToIntArray(data))) {
				data[row][col].setValue(value);
				data[row][col].setEditable(false);
				gui.setValueAtLocation(row, col, value, false);
				count++;
			}
		}
		Config.gameData = data;
	}

	private static int getValidRandomNumber(Random randomGen) {
		int number = randomGen.nextInt(Config.NumberOfRowsColumns);
		while (number == 0)
			number = randomGen.nextInt(Config.NumberOfRowsColumns);
		return number;
	}

	private static boolean solve(int row, int column, int[][] cells) {
		if (row == Config.NumberOfRowsColumns) {
			row = 0;
			if (++column == Config.NumberOfRowsColumns) {
				data = cells;
				return true;
			}
		}
		if (cells[row][column] != 0)
			return solve(row + 1, column, cells);

		for (int val = 1; val <= Config.NumberOfRowsColumns; ++val) {
			if (isValidMove(row, column, val, cells)) {
				cells[row][column] = val;
				if (solve(row + 1, column, cells))
					return true;
			}
		}
		cells[row][column] = 0;
		return false;
	}
}
