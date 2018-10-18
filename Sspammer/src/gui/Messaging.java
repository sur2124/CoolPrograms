package gui;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Messaging {
	public static JTextArea textarea;

	public static int UserChoice(String question) {

		return JOptionPane.showOptionDialog(null, question,
		        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
		        JOptionPane.QUESTION_MESSAGE, null, null, null);
	}

	public static void UserMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static String UserPrompt(String question) {
		return JOptionPane.showInputDialog(question);
	}

	static class GuiMessage extends Thread {
		private String text;
		private boolean newData;

		public void setText(String text) {
			this.text = text;
			newData = true;
		}

		public GuiMessage() {
			super();
		}

		public void run() {
			while (true) {
				if (newData) {
					textarea.setText(text);
					newData = false;
				}
			}
		}
	}
}