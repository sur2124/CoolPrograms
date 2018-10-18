package spamEngine;

import java.io.IOException;

import gui.Gui;
import com.techventus.server.voice.Voice;
import config.Config;

public class GoogleVoiceSenderEngine implements SpammerEngine {

	private Voice voice;
	private int number;
	private String message;
	private String phoneNumber;
	private Gui gui;

	public GoogleVoiceSenderEngine(Gui gui, String phoneNumber, String message,
	        int number) {
		this.gui = gui;
		this.phoneNumber = phoneNumber;
		this.message = message;
		this.number = number;
	}

	public boolean attack(boolean fireConstantly) {
		try {
			voice = new Voice(Config.UserName, Config.Password);
			voice.login();
			gui.addTextToNextLine("Begining attack on " + phoneNumber);
			int count = 0;
			if (fireConstantly) {
				while (send(count)) {
					count = count + 1;
				}
				gui.addTextToNextLine("The message\n\t" + message
				        + "\nwas sent " + (number) + " times to " + phoneNumber);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean send(int count) {
		if (count < number) {
			try {
				voice.sendSMS(phoneNumber, message);

				gui.addTextToNextLine((count + 1) + " message sent "
				        + (number - count - 1)
				        + " messages left to be sent:\tGoogle Voice");
				return true;
			}

			catch (IOException e) {
				return false;
			}
		}
		return false;
	}
}