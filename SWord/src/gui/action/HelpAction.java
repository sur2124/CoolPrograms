package gui.action;


import gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpAction implements ActionListener {

	private GUI gui;

	public HelpAction(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.printHelp();

	}
}
