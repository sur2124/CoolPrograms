package GoogleVoiceSpammer;

import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.techventus.server.voice.Voice;

public class SmsSpammer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String usrName = JOptionPane
					.showInputDialog(null, "Enter UserName");
			if (usrName == null) {
				System.exit(0);
			}
			JPasswordField pwd = new JPasswordField(10);
			int action = JOptionPane.showConfirmDialog(null, pwd,
					"Enter Password:", JOptionPane.OK_CANCEL_OPTION);
			String pass = null;
			if (action < 0)
				System.exit(0);
			else
				pass = new String(pwd.getPassword());
			Voice voice = new Voice(usrName, pass);
			voice.login();
			String phoneNumber = JOptionPane
					.showInputDialog("Enter the desired phone number to spam");
			if (phoneNumber == null) {
				System.exit(0);
			}
			boolean sendAgain = true;
			while (sendAgain) {
				String text = JOptionPane
						.showInputDialog("Enter the text to be sent");
				int num = Integer
						.parseInt(JOptionPane
								.showInputDialog("Enter the number of times to send the message"));
				Random rand = new Random((new Random().nextLong()));
				for (int i = 0; i < num; i++) {
					System.out.println(i + 1);
					voice.sendSMS(phoneNumber, text);
					Thread.sleep(rand.nextInt(i + 200) + 100);
				}
				String temp = "The message\n\t" + text + "\nwas sent " + (num)
						+ " times.";
				JOptionPane.showMessageDialog(null, temp, "Status",
						JOptionPane.PLAIN_MESSAGE);
				int choice = JOptionPane.showConfirmDialog(null,
						"Do you want to spam\n\t" + phoneNumber
								+ "\nwith another message?", "Spam",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
					sendAgain = true;
				else
					sendAgain = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
