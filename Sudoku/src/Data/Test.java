package Data;

import GUI.Gui;

/**
 * Contains positive and negative test data in order to properly test the sodoku
 * solution algorithms.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Test {
	private static Gui gui;

	public Test(Gui gui) {
		Test.gui = gui;
	}

	/**
	 * Sets the Gui with test data that is unsolvable. Expect algorithm to
	 * properly recognize that the game cannot be solved
	 */
	public static void GenerateNegativeTestData() {
		gui.setValueAtLocation(0, 1, 9, false);
		gui.setValueAtLocation(0, 0, 9, false);
		gui.setValueAtLocation(0, 4, 2, false);
		gui.setValueAtLocation(0, 6, 7, false);
		gui.setValueAtLocation(0, 7, 5, false);

		gui.setValueAtLocation(1, 0, 6, false);
		gui.setValueAtLocation(1, 4, 5, false);
		gui.setValueAtLocation(1, 7, 4, false);

		gui.setValueAtLocation(2, 1, 2, false);
		gui.setValueAtLocation(2, 3, 4, false);
		gui.setValueAtLocation(2, 7, 1, false);

		gui.setValueAtLocation(3, 0, 2, false);
		gui.setValueAtLocation(3, 2, 8, false);

		gui.setValueAtLocation(4, 1, 7, false);
		gui.setValueAtLocation(4, 3, 5, false);
		gui.setValueAtLocation(4, 5, 9, false);
		gui.setValueAtLocation(4, 7, 6, false);

		gui.setValueAtLocation(5, 6, 4, false);
		gui.setValueAtLocation(5, 8, 1, false);

		gui.setValueAtLocation(6, 1, 1, false);
		gui.setValueAtLocation(6, 5, 5, false);
		gui.setValueAtLocation(6, 7, 8, false);

		gui.setValueAtLocation(7, 1, 9, false);
		gui.setValueAtLocation(7, 4, 7, false);
		gui.setValueAtLocation(7, 8, 4, false);

		gui.setValueAtLocation(8, 1, 8, false);
		gui.setValueAtLocation(8, 2, 2, false);
		gui.setValueAtLocation(8, 4, 4, false);
		gui.setValueAtLocation(8, 8, 6, false);

	}

	/**
	 * Sets the Gui with test data that is solvable. Expect algorithm to
	 * properly recognize that the game can be solved and solve it.
	 */
	public static void GeneratePositiveTestData() {

		gui.setValueAtLocation(0, 0, 9, false);
		gui.setValueAtLocation(0, 4, 2, false);
		gui.setValueAtLocation(0, 6, 7, false);
		gui.setValueAtLocation(0, 7, 5, false);

		gui.setValueAtLocation(1, 0, 6, false);
		gui.setValueAtLocation(1, 4, 5, false);
		gui.setValueAtLocation(1, 7, 4, false);

		gui.setValueAtLocation(2, 1, 2, false);
		gui.setValueAtLocation(2, 3, 4, false);
		gui.setValueAtLocation(2, 7, 1, false);

		gui.setValueAtLocation(3, 0, 2, false);
		gui.setValueAtLocation(3, 2, 8, false);

		gui.setValueAtLocation(4, 1, 7, false);
		gui.setValueAtLocation(4, 3, 5, false);
		gui.setValueAtLocation(4, 5, 9, false);
		gui.setValueAtLocation(4, 7, 6, false);

		gui.setValueAtLocation(5, 6, 4, false);
		gui.setValueAtLocation(5, 8, 1, false);

		gui.setValueAtLocation(6, 1, 1, false);
		gui.setValueAtLocation(6, 5, 5, false);
		gui.setValueAtLocation(6, 7, 8, false);

		gui.setValueAtLocation(7, 1, 9, false);
		gui.setValueAtLocation(7, 4, 7, false);
		gui.setValueAtLocation(7, 8, 4, false);

		gui.setValueAtLocation(8, 1, 8, false);
		gui.setValueAtLocation(8, 2, 2, false);
		gui.setValueAtLocation(8, 4, 4, false);
		gui.setValueAtLocation(8, 8, 6, false);
	}

}
