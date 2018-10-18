package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Data.Cell;
import Data.Config;
import Service.Action;
import Service.Action.btnSave_Action;
import Service.Action.btnStartNewGame_Action;

/**
 * Contains all the Gui information necessary to begin and run through a game.
 */
@SuppressWarnings("serial")
public class Gui extends JFrame {
	private JButton btnClearBoard;
	private JButton btnLoadSavedGame;
	private JButton btnSave;
	private JButton btnSelectDifficulty;
	private JButton btnSolveForMe;
	private JButton btnStartNewGame;
	private JButton btnSubmit;
	private JFrame frmMain;
	private DefaultTableModel mtblSodoku;
	private Container pane;
	private JPanel pnlSodoku;
	private JScrollPane stblSodoku;
	private Cell[][] tableData;
	private JTable tblSodoku;

	/**
	 * Initializes the Gui
	 */
	public Gui() {
		frmMain = new JFrame(Config.Sodoku_NAME);
		frmMain.setSize(Config.width, Config.height);
		tableData = new Cell[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
		frmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				onExit(tableData);
			}
		});
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializeButtons();
		initializeTableModel();
		initializeTable();
		pnlSodoku = new JPanel(null);
		pnlSodoku.setBorder(BorderFactory
				.createTitledBorder(Config.Sodoku_Application_Name));
		setActionListeners();
		stblSodoku = new JScrollPane(tblSodoku);
		pane.add(pnlSodoku);
		pnlSodoku.add(btnLoadSavedGame);
		pnlSodoku.add(btnSolveForMe);
		pnlSodoku.add(btnSubmit);
		pnlSodoku.add(btnSave);
		pnlSodoku.add(btnStartNewGame);
		pnlSodoku.add(btnClearBoard);
		pnlSodoku.add(btnSelectDifficulty);
		pnlSodoku.add(stblSodoku);
		setBounds();
		int SodokuHeight = Config.height - 400;
		stblSodoku.setBounds(40, 50, Config.width - 80, Config.height);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		tblSodoku.getParent().setBackground(tblSodoku.getBackground());
		tblSodoku.getTableHeader().setResizingAllowed(false);
		tblSodoku.getTableHeader().setReorderingAllowed(false);
		tblSodoku.setColumnSelectionAllowed(true);
		tblSodoku.setRowSelectionAllowed(true);
		tblSodoku.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSodoku.setRowHeight(SodokuHeight / 6);
		mtblSodoku.setColumnCount(Config.NumberOfRowsColumns);
		mtblSodoku.setRowCount(Config.NumberOfRowsColumns);
		initializeBoard();
		refreshPuzzle();
		btnStartNewGame_Action.newGame(this);
	}

	/**
	 * Convenience method for clearing the board of all data
	 */
	public void clearBoard() {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				tblSodoku.setValueAt(null, i, j);
			}
		}
		tableData = new Cell[Config.NumberOfRowsColumns][Config.NumberOfRowsColumns];
	}

	/**
	 * @return the tableData
	 */
	public Cell[][] getTableData() {
		return tableData;
	}

	/**
	 * Returns the data at the given location
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getValueAtLocation(int x, int y) {
		return (Integer) tblSodoku.getModel().getValueAt(x, y);
	}

	/**
	 * Initializes the board for gameplay
	 */
	public void initializeBoard() {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				Cell temp = new Cell();
				temp.setEditable(true);
				tableData[i][j] = temp;
				tblSodoku.setValueAt(null, i, j);
			}
		}
	}

	/**
	 * Provides custom Exit actions
	 * 
	 * @param tableData
	 */
	public void onExit(Cell[][] tableData) {
		int choice = Alert
				.UserChoice("Thank you for playing Sodoku!\nWould you like to save your game before you quit?");
		if (choice == JOptionPane.YES_OPTION) {
			btnSave_Action.Save(tblSodoku);
			System.exit(0);
		}
		if (choice == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Posts data to the Gui
	 * 
	 * @param data
	 * @param isEditable
	 */
	public void postGuiWithData(int[][] data, boolean isEditable) {
		clearBoard();
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				setValueAtLocation(i, j, data[i][j], isEditable);
			}
		}
	}

	/**
	 * Sets the game data to the global game data file, and updates the Gui.
	 * 
	 * @param tableData
	 */
	public void setTableDataAndUpdateGui(Cell[][] tableData) {
		for (int i = 0; i < tableData.length; i++) {
			for (int j = 0; j < tableData[i].length; j++) {
				if (tableData[i][j] == null) {
					tblSodoku.setValueAt("", i, j);
				} else {
					if (tableData[i][j].getValue() != 0) {
						setValueAtLocation(i, j, tableData[i][j].getValue(),
								tableData[i][j].isEditable());
						tblSodoku.setEnabled(tableData[i][j].isEditable());
					} else {
						tblSodoku.setEnabled(true);
					}
				}
			}
		}
	}

	/**
	 * Sets given value at the given location
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * @param isEditable
	 */
	public void setValueAtLocation(int x, int y, int value, boolean isEditable) {
		if (tblSodoku.isCellEditable(x, y)) {
			Cell temp = new Cell();
			temp.setValue(value);
			temp.setEditable(isEditable);
			tableData[x][y] = temp;
			tblSodoku.setValueAt(value, x, y);
		}
	}

	private void initializeButtons() {
		btnSave = new JButton("Save Game");
		btnSolveForMe = new JButton("Solve For Me");
		btnSubmit = new JButton("Submit");
		btnStartNewGame = new JButton("New Game");
		btnLoadSavedGame = new JButton("Load Saved Game");
		btnClearBoard = new JButton("Clear Board");
		btnSelectDifficulty = new JButton("Select Difficulty");
	}

	private void initializeTable() {
		tblSodoku = new JTable(mtblSodoku) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) comp;
				Border outside;
				Border inside = new EmptyBorder(0, 0, 0, 0);
				if (row % 3 == 0 && column % 3 == 0) {
					outside = new MatteBorder(1, 1, 0, 0, Color.black);
				} else if (row % 3 == 0) {
					outside = new MatteBorder(1, 0, 0, 0, Color.black);
				} else if (column % 3 == 0) {
					outside = new MatteBorder(0, 1, 0, 0, Color.black);
				} else {
					outside = new MatteBorder(0, 0, 0, 0, Color.black);
				}
				Border highlightRow = new CompoundBorder(outside, inside);
				jc.setBorder(highlightRow);
				return comp;
			}
		};
	}

	private void initializeTableModel() {
		mtblSodoku = new DefaultTableModel() {
			@Override
			public String getColumnName(int col) {
				return Config.columnNames[col];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				if (tableData[rowIndex][mColIndex] != null)
					return tableData[rowIndex][mColIndex].isEditable();
				else
					return true;
			}
		};
	}

	private void refreshPuzzle() {
		btnSolveForMe.setEnabled(true);
		btnSubmit.setEnabled(true);
		btnSave.setEnabled(true);
		btnStartNewGame.setEnabled(true);
		btnLoadSavedGame.setEnabled(true);
		btnClearBoard.setEnabled(true);
		btnSelectDifficulty.setEnabled(true);
		tblSodoku.setColumnSelectionAllowed(true);
		tblSodoku.setRowSelectionAllowed(true);
		tblSodoku.setDefaultRenderer(tblSodoku.getColumnClass(0),
				new tblSodokuRenderer(tblSodoku));
	}

	private void setActionListeners() {
		btnSolveForMe.addActionListener(new Action.btnSolveForMe_Action(this));
		btnSubmit
				.addActionListener(new Action.btnSubmit_Action(tblSodoku, this));
		btnSave.addActionListener(new Action.btnSave_Action(tblSodoku));
		btnStartNewGame.addActionListener(new Action.btnStartNewGame_Action(
				this));
		btnLoadSavedGame.addActionListener(new Action.btnLoadSavedGame_Action(
				this));
		btnClearBoard.addActionListener(new Action.btnClearBoard_Action(this));
		btnSelectDifficulty
				.addActionListener(new Action.btnSelectDifficulty_Action());
	}

	private void setBounds() {
		pnlSodoku.setBounds(0, 0, Config.width, Config.height);
		btnSave.setBounds(200, 15, 100, 25);
		btnSave.setForeground(Color.blue);
		btnLoadSavedGame.setBounds(42, 15, 140, 25);
		btnLoadSavedGame.setForeground(Color.blue);
		btnSolveForMe.setBounds(50, 570, 120, 25);
		btnSolveForMe.setForeground(Color.red);
		btnSubmit.setBounds(750, 570, 90, 25);
		btnSubmit.setForeground(Color.blue);
		btnStartNewGame.setBounds(760, 15, 100, 25);
		btnStartNewGame.setForeground(new Color(0, 112, 0));
		btnClearBoard.setBounds(190, 570, 110, 25);
		btnClearBoard.setForeground(Color.red);
		btnSelectDifficulty.setBounds(620, 15, 130, 25);
		btnSelectDifficulty.setForeground(new Color(0, 112, 0));
	}
}