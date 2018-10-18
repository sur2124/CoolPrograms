package Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Data.Cell;
import Data.Config;
import Data.GameLoader;
import Data.GameStore;
import GUI.Alert;
import GUI.Gui;

/**
 * Contains the Action Listeners which respond to User actions
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Action {
	public static int difficultyLevel = Config.mediumSetting;

	/**
	 * Clears the board if the Clear Board button is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnClearBoard_Action implements ActionListener {
		private Gui gui;

		public btnClearBoard_Action(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gui.clearBoard();
			gui.setTableDataAndUpdateGui(Config.gameData);
		}
	}

	/**
	 * Loads a Saved Game if the Load Saved button is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnLoadSavedGame_Action implements ActionListener {
		private Gui gui;

		public btnLoadSavedGame_Action(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent arg) {
			File selectedFile = Alert.chooseFileGui();
			try {
				gui.clearBoard();
				Cell[][] tableData = GameLoader.getData(selectedFile);
				gui.setTableDataAndUpdateGui(tableData);
				Alert.UserMessage("Game Loaded!\n\tEnjoy!");
			} catch (FileNotFoundException e) {
				Alert.Error("Could not load game. Please try again.\n If error continues, restart application");
			}
		}

	}

	/**
	 * Saves the board if the Save button is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnSave_Action implements ActionListener {
		private static String fileName;
		private static JTable tblSodoku;

		public btnSave_Action(JTable tblSodoku) {
			btnSave_Action.tblSodoku = tblSodoku;
		}

		public static void Save(JTable tblSodoku) {
			btnSave_Action.tblSodoku = tblSodoku;
			Save();
		}

		private static void Save() {
			fileName = Alert
					.UserPrompt("What do you wish to name this game?\nExample: Great Game");
			try {
				if (fileName != "") {
					GameStore.SaveGame(
							Config.convertJtableIntoCellArray(tblSodoku),
							fileName);
					Alert.UserMessage("Game Saved!");
				}
			} catch (IOException e) {
				Alert.Error("Could not save game. Please try again.\n If error continues, restart application");
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg) {
			Save();
		}
	}

	/**
	 * Sets the difficulty level if the select difficulty action is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnSelectDifficulty_Action implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int difficulty = Alert.SetEasyMediumHard();
			if (difficulty != -1) {
				difficultyLevel = difficulty;
				btnStartNewGame_Action.newGame();
			}
		}
	}

	public static class btnSolveForMe_Action implements ActionListener {
		private Gui gui;

		public btnSolveForMe_Action(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent arg) {
			boolean solved = Game.Solve(Config.gameData, true, false, gui);
			if (solved) {
				Alert.UserMessage("Solved!\nPlease Study the solution and when you are ready...\n\tClick new game!");
			} else
				Alert.Error("Sodoku Puzzle Could Not Be Solved\n\tApologies.");
		}
	}

	/**
	 * Starts a new game if the Start New Game is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnStartNewGame_Action implements ActionListener {
		private static Gui gui;

		public btnStartNewGame_Action(Gui gui) {
			btnStartNewGame_Action.gui = gui;
		}

		public static void newGame(Gui gui) {
			btnStartNewGame_Action.gui = gui;
			gui.clearBoard();
			newGame();
			gui.setTableDataAndUpdateGui(Config.gameData);
		}

		@Override
		public void actionPerformed(ActionEvent arg) {
			newGame();
		}

		private static void newGame() {
			gui.clearBoard();
			Game.generateNewGame(difficultyLevel, gui);
			while (!Game.Solve(Config.gameData, false, false, gui))
				Game.generateNewGame(difficultyLevel, gui);
			gui.clearBoard();
			gui.setTableDataAndUpdateGui(Config.gameData);
		}
	}

	/**
	 * Submits the game data if Submit button is pressed
	 * 
	 * @author Suraj Kulkarni
	 * 
	 */
	public static class btnSubmit_Action implements ActionListener {
		private Gui gui;
		private JTable tblSodoku;

		public btnSubmit_Action(JTable tblSodoku, Gui gui) {
			this.tblSodoku = tblSodoku;
			this.gui = gui;
		}

		public void actionPerformed(ActionEvent arg) {
			Cell[][] temp = Config.convertJtableIntoCellArray(tblSodoku);
			if (!hasEmptyCells(temp)) {
				int choice;
				if (Game.checkSolution(temp)) {
					choice = Alert
							.UserChoice("Congratulations! Solution Is Correct!\n\nWould you like to start a new game?");
					if (choice == JOptionPane.YES_OPTION)
						btnStartNewGame_Action.newGame();
				} else
					choice = Alert
							.UserChoice("Incorrect Solution!\nWould you like to try again?\nClick No to start a new game.");
				if (choice == JOptionPane.NO_OPTION)
					btnStartNewGame_Action.newGame();
			} else {
				int choice = Alert
						.UserChoice("You have empty cells in your solution.\nWould you like to return to the game?");
				if (choice == JOptionPane.NO_OPTION) {
					Game.Solve(Config.gameData, true, false, gui);
				}
			}
		}

		private boolean hasEmptyCells(Cell[][] tableData) {
			for (int i = 0; i < tableData.length; i++) {
				for (int j = 0; j < tableData[i].length; j++) {
					if (tableData[i][j] == null
							|| tableData[i][j].getValue() == 0)
						return true;
				}
			}
			return false;
		}
	}
}
