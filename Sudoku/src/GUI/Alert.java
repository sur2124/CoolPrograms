package GUI;

import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import Data.Cell;
import Data.Config;
import Service.Action;

/**
 * Contains the methods necessary to interact with the User.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Alert {
	/**
	 * Prompts the User to select a file, and returns the file
	 * 
	 * @return File
	 */
	public static File chooseFileGui() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(Config.dirFile);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		File selectedFile = chooser.getSelectedFile();
		return selectedFile;
	}

	/**
	 * Outputs game data to standard output, meant for debugging purposes
	 * 
	 * @param data
	 */
	public static void Debug(Cell[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println("i = " + i + " j = " + j + "\n\tvalue = "
						+ data[i][j].getValue());
			}
		}
	}

	/**
	 * Outputs game data to standard output, meant for debugging purposes
	 * 
	 * @param data
	 */
	public static void Debug(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println("i = " + i + " j = " + j + "\n\tvalue = "
						+ data[i][j]);
			}
		}
	}

	/**
	 * Outputs Error messages to the User
	 * 
	 * @param message
	 */
	public static void Error(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Prompts the User to pick difficulty level of the game. Returns either
	 * Easy, Medium, Hard, or -1
	 * 
	 * @return int
	 */
	public static int SetEasyMediumHard() {
		JRadioButton b1 = new JRadioButton("Easy");
		JRadioButton b2 = new JRadioButton("Medium");
		JRadioButton b3 = new JRadioButton("Hard");
		if (Action.difficultyLevel == Config.easySetting)
			b1.setSelected(true);
		if (Action.difficultyLevel == Config.mediumSetting)
			b2.setSelected(true);
		if (Action.difficultyLevel == Config.hardSetting)
			b3.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(b1);
		group.add(b2);
		group.add(b3);
		Object[] array = { new JLabel("Select an option:"), b1, b2, b3 };
		int res = JOptionPane.showConfirmDialog(null, array, "Select",
				JOptionPane.OK_CANCEL_OPTION);
		if (res == JOptionPane.CANCEL_OPTION
				|| res == JOptionPane.CLOSED_OPTION) {
			return -1;
		}
		if (b1.isSelected()) {
			return Config.easySetting;
		}
		if (b2.isSelected()) {
			return Config.mediumSetting;
		}
		if (b3.isSelected()) {
			return Config.hardSetting;
		}
		return -1;
	}

	/**
	 * Prompts the user with a question dialog, and returns yes or no
	 * 
	 * @param question
	 * @return
	 */
	public static int UserChoice(String question) {
		return JOptionPane.showOptionDialog(null, question,
				"Exit Confirmation", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
	}

	/**
	 * Outputs a message to the User
	 * 
	 * @param message
	 */
	public static void UserMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Prompts the user to enter data
	 * 
	 * @param question
	 * @return String
	 */
	public static String UserPrompt(String question) {
		return JOptionPane.showInputDialog(question);
	}
}
