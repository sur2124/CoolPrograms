package gui;

import gui.action.EmailAction;
import gui.action.ExitAction;
import gui.action.HelpAction;
import gui.action.RunAction;
import gui.action.WebsiteAction;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JFrame frame;

	public GUI() {
		textArea = new JTextArea(40, 50);
		scrollPane = new JScrollPane(textArea);
		frame = new JFrame("SWord");
		ImageIcon img = new ImageIcon("sword.png");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(img.getImage());
		frame.add(scrollPane);
		frame.pack();
		frame.setVisible(true);
		scrollPane
		        .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("Help");

		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);

		JMenuItem runAction = new JMenuItem("Run");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem helpAction = new JMenuItem("Help");
		JMenuItem emailAction = new JMenuItem("Email Developer");
		JMenuItem websiteAction = new JMenuItem("Visit Website");

		fileMenu.add(runAction);
		fileMenu.add(exitAction);
		aboutMenu.add(helpAction);
		aboutMenu.add(emailAction);
		aboutMenu.add(websiteAction);

		runAction.addActionListener(new RunAction());
		exitAction.addActionListener(new ExitAction(this));
		helpAction.addActionListener(new HelpAction(this));
		websiteAction.addActionListener(new WebsiteAction(this));
		emailAction.addActionListener(new EmailAction(this));

		frame.setJMenuBar(menuBar);
		printHelp();
	}

	public void printHelp() {
		cleanPrint(help());
	}

	public void cleanPrint(String output) {
		textArea.setText(null);
		print(output);
	}

	public void print(String output) {
		textArea.setText(textArea.getText() + "\n" + output);
	}

	private String help() {
		String output = "SWord Manual:\n\n";
		output += "This program is intended to be used as a word descrambler or as a scrabble word finder. It is based off of the SOWPODS and TWL dictionaries.";
		output += "\nType in any number of letters and this program will print out a list of possible words";
		output += "\n\nEnter letters into the scrabble word finder, all valid scrabble words will\nbe generated. Use '?' as wildcard.";
		output += "\n\nSample input: abcyr?ddk?";
		output += "\n\n\n\tCreated By:\n\t\tSuraj Kulkarni\n\t\thttp://SurajKulkarni.com";
		output += "\n\n\nClick File -> Run to begin using the program";
		return output;
	}

	public void printError(String error) {
		print("\n" + error.toUpperCase());
	}

	/**
	 * @return the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * @param textArea
	 *            the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane
	 *            the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void print(Exception e) {
		print(e.getMessage());
	}

}
