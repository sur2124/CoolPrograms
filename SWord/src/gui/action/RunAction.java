package gui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import analytics.Sword;

public class RunAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Sword.run();
	}

}
