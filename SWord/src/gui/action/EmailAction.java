package gui.action;

import gui.GUI;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class EmailAction implements ActionListener {

	private GUI gui;

	public EmailAction(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			URI mailto = new URI(
			        "mailto:suraj@surajkulkarni.com?subject=Regarding%20SWord");
			Desktop.getDesktop().mail(mailto);
		} catch (Exception e1) {
			gui.printError("problem in opening email client. Please email suraj@surajkulkarni.com");
		}
	}

}
