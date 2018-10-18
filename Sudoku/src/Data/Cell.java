package Data;

/**
 * This class contains the data required for the gui cells.
 * 
 * @author Suraj Kulkarni
 * 
 */
public class Cell {
	private boolean isEditable;
	private int value;

	/**
	 * Empty Constructor
	 */
	public Cell() {
	}

	/**
	 * Creates a cell with the given value and whether it should be editable or
	 * not.
	 * 
	 * @param value
	 * @param isEditable
	 */
	public Cell(int value, boolean isEditable) {
		this.value = value;
		this.isEditable = isEditable;
	}

	/**
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return isEditable
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * sets isEditable
	 * 
	 * @param isEditable
	 */
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * Sets the value
	 * 
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Prints the contents of the Cell
	 */
	@Override
	public String toString() {
		return "value = " + value + " and isEditable = " + isEditable;
	}
}
