package gui;

import gui.Messaging.GuiMessage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import config.Config;

@SuppressWarnings("serial")
public class Gui extends JFrame {

	private JScrollPane scrollingResult;
	private static GuiMessage guiMessage;

	/**
	 * Initializes the Gui
	 */
	public Gui() {
		setTitle("Sspammer");
		setSize(700, 600);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu spamMenu = new JMenu("Choose Spam Engine");
		JMenu aboutMenu = new JMenu("About");
		menuBar.add(fileMenu);
		fileMenu.add(spamMenu);
		menuBar.add(aboutMenu);

		JMenuItem credentialsAction = new JMenuItem("Enter Credentials");
		JMenuItem googleVoiceAction = new JMenuItem("Google Voice");
		JMenuItem gmailAction = new JMenuItem("Gmail");
		JMenuItem allAction = new JMenuItem("ALL");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem helpAction = new JMenuItem("Help");
		JMenuItem developerAction = new JMenuItem("Contact Developer");

		fileMenu.add(credentialsAction);
		fileMenu.add(exitAction);
		spamMenu.add(googleVoiceAction);
		spamMenu.add(gmailAction);
		spamMenu.addSeparator();
		spamMenu.add(allAction);
		aboutMenu.add(helpAction);
		aboutMenu.add(developerAction);

		credentialsAction.addActionListener(new Action.CredentialsAction(this));
		googleVoiceAction.addActionListener(new Action.GoogleVoiceAction(this));
		gmailAction.addActionListener(new Action.GmailAction(this));
		allAction.addActionListener(new Action.AllAction(this));
		exitAction.addActionListener(new Action.ExitAction());
		helpAction.addActionListener(new Action.HelpAction(this));
		developerAction.addActionListener(new Action.ContactDeveloperAction(
		        this));

		guiMessage = new Messaging.GuiMessage();
		Thread messagingThread = new Thread(guiMessage);
		messagingThread.start();

		Messaging.textarea = new JTextArea();
		Messaging.textarea.setEditable(false);

		scrollingResult = new JScrollPane(Messaging.textarea);
		scrollingResult.createVerticalScrollBar();
		scrollingResult
		        .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollingResult);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Gui me = new Gui();
		me.setVisible(true);
		guiMessage.setText(Config.StartPageText);
	}

	public void showStartPage() {
		addTextToNextLine(Config.StartPageText);
	}

	public void addTextToNextLine(String textToAdd) {
		Messaging.textarea.getText();
		String currentText = Messaging.textarea.getText();
		System.out.println("Current Text" + currentText);
		guiMessage.setText(currentText + "\n" + textToAdd);
	}

	public void clearTextArea() {
		guiMessage.setText("");
		guiMessage.setText("");
	}

}
