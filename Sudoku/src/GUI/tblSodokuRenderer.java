package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Contains the methods necessary to customize the Gui.
 * 
 * @author Suraj Kulkarni
 * 
 */
@SuppressWarnings("serial")
public class tblSodokuRenderer extends DefaultTableCellRenderer {
	private JTable tblSodoku;

	/**
	 * Initializes the Custom Renderer
	 * 
	 * @param tblSodoku
	 * @param tableData
	 */
	public tblSodokuRenderer(JTable tblSodoku) {
		this.tblSodoku = tblSodoku;
	}

	/**
	 * Customizes the Renderer
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean focused, int row, int column) {
		super.getTableCellRendererComponent(table, value, selected, focused,
				row, column);

		JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(
				table, value, selected, focused, row, column);
		renderedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setBackground(Color.white);
		this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		tblSodoku.setEnabled(true);
		if (!tblSodoku.isCellEditable(row, column))
			setForeground(Color.blue);
		else {
			setForeground(Color.black);
		}
		return this;
	}
}