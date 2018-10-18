package gui.action;


import gui.GUI;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WebsiteAction implements ActionListener {

	private GUI gui;

	public WebsiteAction(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String url = "http://www.surajkulkarni.com";
		try {
			Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (IOException e1) {
			gui.printError("problem in opening url. Please visit http://SurajKulkarni.com");

		}

	}

}
