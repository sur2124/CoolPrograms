package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import spamEngine.GmailSenderEngine;
import spamEngine.GoogleVoiceSenderEngine;
import spamEngine.SpammerEngine;
import config.Config;

public class Action {

	public static class CredentialsAction implements ActionListener {

		public static TextField lUserName;
		public static TextField lPassword;
		private Gui gui;

		public CredentialsAction(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gui.clearTextArea();
			gui.setEnabled(false);
			final Frame frm = new Frame("Credentials");
			frm.setSize(350, 120);
			frm.setVisible(true);
			frm.setAlwaysOnTop(true);
			frm.setResizable(false);
			frm.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					gui.setEnabled(true);
					frm.dispose();
				}
			});
			Panel p = new Panel();
			Panel p1 = new Panel();
			Label jUserName = new Label("Google User Name");
			lUserName = new TextField(20);
			Label jPassword = new Label("Password");
			lPassword = new TextField(20);
			if (isCredentialsSet()) {
				lUserName.setText(Config.UserName);
				lPassword.setText(Config.Password);
			}
			p.setLayout(new GridLayout(3, 1));
			p.add(jUserName);
			p.add(lUserName);
			p.add(jPassword);
			p.add(lPassword);
			Button Submit = new Button("Submit");
			p.add(Submit);
			p1.add(p);
			frm.add(p1, BorderLayout.NORTH);
			Submit.addActionListener(new SubmitAction(frm, gui));
		}

		static class SubmitAction implements ActionListener {
			private Frame frm;
			private Gui gui;

			public SubmitAction(Frame frm, Gui gui) {
				this.frm = frm;
				this.gui = gui;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				Config.UserName = CredentialsAction.lUserName.getText();
				Config.Password = CredentialsAction.lPassword.getText();
				if (Config.UserName.isEmpty() || Config.Password.isEmpty()) {
					Config.UserName = new String();
					Config.Password = new String();
					gui.addTextToNextLine("No data was entered! Username and Password not set.");
				} else {
					gui.addTextToNextLine("Username and Password successfully stored.");
				}
				gui.setEnabled(true);
				frm.dispose();
			}

		}
	}

	public static class HelpAction implements ActionListener {

		private Gui gui;

		public HelpAction(Gui gui) {
			this.gui = gui;
		}

		public void actionPerformed(ActionEvent e) {
			gui.clearTextArea();
			gui.showStartPage();
		}

	}

	public static class AllAction implements ActionListener {

		private Gui gui;
		private TextField lEmailAddress;
		private TextField lMessage;
		private TextField lSubject;
		private TextField lNumberEmails;
		private TextField lPhoneNumber;
		private TextField lNumberText;

		public AllAction(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isCredentialsSet()) {
				gui.clearTextArea();
				gui.addTextToNextLine("Credentials not set. Enter credentials to begin Spamming.");
			} else {
				gui.setEnabled(false);
				final Frame frm = new Frame("Full Attack");
				frm.setSize(810, 220);
				frm.setVisible(true);
				frm.setAlwaysOnTop(true);
				frm.setResizable(false);
				frm.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						gui.setEnabled(true);
						frm.dispose();
					}
				});
				Panel p = new Panel();
				Panel p1 = new Panel();
				Label jEmailAddress = new Label("Person's email address: ");
				lEmailAddress = new TextField(20);
				Label jMessage = new Label("Message: ");
				lMessage = new TextField(30);
				Label jSubject = new Label("Subject line: ");
				lSubject = new TextField(20);
				Label jNumberEmails = new Label(
				        "Number of times to send email: ");
				lNumberEmails = new TextField(20);
				Label jPhoneNumber = new Label(
				        "Person's Phone number: no dashes, spaces, or slashes");
				lPhoneNumber = new TextField(10);
				Label jNumberText = new Label("Number of times to send text: ");
				lNumberText = new TextField(20);

				p.setLayout(new GridLayout(4, 1));

				p.add(jMessage);
				p.add(lMessage);

				p.add(jPhoneNumber);
				p.add(lPhoneNumber);
				p.add(jNumberText);
				p.add(lNumberText);

				p.add(jEmailAddress);
				p.add(lEmailAddress);
				p.add(jSubject);
				p.add(lSubject);
				p.add(jNumberEmails);
				p.add(lNumberEmails);

				p.setLayout(new GridLayout(7, 1));
				Button Submit = new Button("Submit");
				p.add(Submit);
				p1.add(p);
				frm.add(p1, BorderLayout.NORTH);
				Submit.addActionListener(new SubmitAction(frm, gui));

			}

		}

		class SubmitAction implements ActionListener {

			private Frame frm;
			private Gui gui;

			public SubmitAction(Frame frm, Gui gui) {
				this.frm = frm;
				this.gui = gui;

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setEnabled(false);
				String emailAddress = lEmailAddress.getText();
				String message = lMessage.getText();
				String subject = lSubject.getText();
				String numberEmails = lNumberEmails.getText();
				String phoneNumber = lPhoneNumber.getText();
				String numberTexts = lNumberText.getText();

				if (emailAddress == null || message == null
				        || emailAddress.isEmpty() || message.isEmpty()
				        || phoneNumber == null || message == null
				        || phoneNumber.isEmpty() || message.isEmpty()) {
					Messaging.UserMessage("Please enter data.");
					frm.setEnabled(true);
				} else {
					int numEmail = 0;
					int numTexts = 0;
					numEmail = Integer.parseInt(numberEmails);
					numTexts = Integer.parseInt(numberTexts);
					if (numEmail < 1 || numEmail > Config.MAX_NUMBER_OF_EMAILS
					        || numTexts < 1
					        || numTexts > Config.MAX_NUMBER_OF_SMS) {
						if (numEmail < 1
						        || numEmail > Config.MAX_NUMBER_OF_EMAILS) {
							Messaging
							        .UserMessage("The number of times you can send emails must be between 1 and "
							                + Config.MAX_NUMBER_OF_EMAILS);
							frm.setEnabled(true);
						}

						if (numTexts < 1 || numTexts > Config.MAX_NUMBER_OF_SMS) {
							Messaging
							        .UserMessage("The number of times you send texts must be between 1 and "
							                + Config.MAX_NUMBER_OF_SMS);
							frm.setEnabled(true);
						}
						frm.setEnabled(true);
					} else {
						frm.dispose();
						GmailSenderEngine gmail = new GmailSenderEngine(gui,
						        emailAddress, message, subject, numEmail);
						GoogleVoiceSenderEngine googleVoice = new GoogleVoiceSenderEngine(
						        gui, phoneNumber, message, numTexts);
						ArrayList<SpammerEngine> spammers = new ArrayList<SpammerEngine>();
						spammers.add(gmail);
						spammers.add(googleVoice);

						gui.setEnabled(true);
						gui.addTextToNextLine("\nPreparing Google Voice Spam Attack...");
						gui.addTextToNextLine("\nPreparing Gmail Spam Attack...");
						frm.dispose();

						for (int i = 0; i < spammers.size(); i++) {
							spammers.get(i).attack(true);
						}
					}
				}
			}
		}
	}

	public static class ContactDeveloperAction implements ActionListener {

		private Gui gui;

		public ContactDeveloperAction(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Desktop desktop = Desktop.getDesktop();
			URI uri;
			gui.addTextToNextLine("\n\nOpening browser to http://SurajKulkarni.com\n\tClick "
			        + "on the Contact Me tab to send me an Email, I will respond as soon as possible!");

			try {
				uri = new java.net.URI(Config.DeveloperContactUrl);
				desktop.browse(uri);
			} catch (Exception e1) {
				gui.addTextToNextLine("Could not redirect to Developer's Webpage\nWebpage url is SurajKulkarni.com");
			}
		}

	}

	public static class GmailAction implements ActionListener {

		private Gui gui;
		public TextField lEmailAddress;
		public TextField lMessage;
		public TextField lSubject;
		public TextField lNumber;

		public GmailAction(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isCredentialsSet()) {
				gui.addTextToNextLine("Credentials not set. Enter credentials to begin Spamming.");
			} else {
				gui.setEnabled(false);
				final Frame frm = new Frame("Gmail");
				frm.setSize(610, 220);
				frm.setVisible(true);
				frm.setAlwaysOnTop(true);
				frm.setResizable(false);
				frm.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						gui.setEnabled(true);
						frm.dispose();
					}
				});
				Panel p = new Panel();
				Panel p1 = new Panel();
				Label jEmailAddress = new Label("Person's email address: ");
				lEmailAddress = new TextField(20);
				Label jMessage = new Label("Message: ");
				lMessage = new TextField(30);
				Label jSubject = new Label("Subject line: ");
				lSubject = new TextField(20);
				Label jNumber = new Label("Number of times to send email: ");
				lNumber = new TextField(20);

				p.setLayout(new GridLayout(5, 1));
				p.add(jEmailAddress);
				p.add(lEmailAddress);
				p.add(jSubject);
				p.add(lSubject);
				p.add(jMessage);
				p.add(lMessage);
				p.add(jNumber);
				p.add(lNumber);

				Button Submit = new Button("Submit");
				p.add(Submit);
				p1.add(p);
				frm.add(p1, BorderLayout.NORTH);
				Submit.addActionListener(new SubmitAction(frm, gui));

			}
		}

		class SubmitAction implements ActionListener {

			private Frame frm;
			private Gui gui;

			public SubmitAction(Frame frm, Gui gui) {
				this.frm = frm;
				this.gui = gui;

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setEnabled(false);
				String emailAddress = lEmailAddress.getText();
				String message = lMessage.getText();
				String subject = lSubject.getText();
				String number = lNumber.getText();
				if (emailAddress == null || message == null
				        || emailAddress.isEmpty() || message.isEmpty()) {
					Messaging.UserMessage("Please enter data.");
					frm.setEnabled(true);
				} else {
					int num = 0;
					try {
						num = Integer.parseInt(number);
						if (num < 1 || num > Config.MAX_NUMBER_OF_EMAILS) {
							Messaging
							        .UserMessage("The number of times you can send emails must be between 1 and "
							                + Config.MAX_NUMBER_OF_EMAILS);
							frm.setEnabled(true);
						} else {

							GmailSenderEngine gmail = new GmailSenderEngine(
							        gui, emailAddress, message, subject, num);
							gui.setEnabled(true);
							gui.addTextToNextLine("\nPreparing Gmail Spam Attack...");
							frm.dispose();
							boolean attackSuccessful = gmail.attack(true);
							if (!attackSuccessful) {
								gui.addTextToNextLine("Could not login to gmail wih your credentials."
								        + "\n\nVisit google.com/mail to confirm your credentials");
							}
						}
					} catch (NumberFormatException ex) {
						Messaging.UserMessage("You must enter a number!");
						frm.setEnabled(true);
					}

				}
			}
		}

	}

	public static class GoogleVoiceAction implements ActionListener {

		private Gui gui;
		public TextField lPhoneNumber;
		public TextField lMessage;
		public TextField lNumber;

		public GoogleVoiceAction(Gui gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isCredentialsSet()) {
				gui.clearTextArea();
				gui.addTextToNextLine("Credentials not set. Enter credentials to begin Spamming.");
			} else {
				gui.setEnabled(false);
				final Frame frm = new Frame("Google Voice");
				frm.setSize(810, 220);
				frm.setVisible(true);
				frm.setAlwaysOnTop(true);
				frm.setResizable(false);
				frm.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						gui.setEnabled(true);
						frm.dispose();
					}
				});
				Panel p = new Panel();
				Panel p1 = new Panel();
				Label jPhoneNumber = new Label(
				        "Person's Phone number: no dashes, spaces, or slashes");
				lPhoneNumber = new TextField(10);
				Label jMessage = new Label("Message: ");
				lMessage = new TextField(30);
				Label jNumber = new Label("Number of times to send Message: ");
				lNumber = new TextField(20);

				p.setLayout(new GridLayout(4, 1));
				p.add(jPhoneNumber);
				p.add(lPhoneNumber);
				p.add(jMessage);
				p.add(lMessage);
				p.add(jNumber);
				p.add(lNumber);

				Button Submit = new Button("Submit");
				p.add(Submit);
				p1.add(p);
				frm.add(p1, BorderLayout.NORTH);
				Submit.addActionListener(new SubmitAction(frm, gui));

			}
		}

		class SubmitAction implements ActionListener {

			private Frame frm;
			private Gui gui;

			public SubmitAction(Frame frm, Gui gui) {
				this.frm = frm;
				this.gui = gui;

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				frm.setEnabled(false);
				String phoneNumber = lPhoneNumber.getText();
				String message = lMessage.getText();
				String number = lNumber.getText();
				if (phoneNumber == null || message == null
				        || phoneNumber.isEmpty() || message.isEmpty()) {
					Messaging.UserMessage("Please enter data.");
					frm.setEnabled(true);
				} else {
					int num = 0;
					try {
						num = Integer.parseInt(number);
						if (num < 1 || num > Config.MAX_NUMBER_OF_SMS) {
							Messaging
							        .UserMessage("The number of times you send texts must be between 1 and "
							                + Config.MAX_NUMBER_OF_SMS);
							frm.setEnabled(true);
						} else {
							GoogleVoiceSenderEngine googleVoice = new GoogleVoiceSenderEngine(
							        gui, phoneNumber, message, num);
							gui.setEnabled(true);
							gui.addTextToNextLine("\nPreparing Google Voice Spam Attack...");
							frm.dispose();
							boolean attackSuccessful = googleVoice.attack(true);
							if (!attackSuccessful) {
								gui.addTextToNextLine("Could not login to google voice wih your credentials."
								        + "\n\nVisit google.com/voice to confirm your credentials");
							}
						}
					} catch (NumberFormatException ex) {
						Messaging.UserMessage("You must enter a number!");
						frm.setEnabled(true);
					}

				}
			}
		}

	}

	public static class ExitAction implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			int choice = Messaging
			        .UserChoice("Are you sure you would like to quit?");
			if (choice == JOptionPane.YES_OPTION) {
				Messaging.UserMessage("Thanks for using Sspammer!");
				System.exit(0);
			}
		}

	}

	public static boolean isCredentialsSet() {
		if (Config.UserName != null && !Config.UserName.isEmpty()
		        && Config.Password != null && !Config.Password.isEmpty()) {
			return true;
		}
		return false;
	}
}
