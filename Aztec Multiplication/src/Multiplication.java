import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * @author Suraj Kulkarni
 *
 */
public class Multiplication {

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		int firstNum = 0, secondNum = 0, sum = 0;
		String output = "";
		firstNum = Integer.parseInt(JOptionPane.showInputDialog("Enter the First Number in the Multiplication"));
		secondNum = Integer.parseInt(JOptionPane.showInputDialog("Enter the second Number in the Multiplication"));
		ArrayList timesTable = new ArrayList();
		timesTable.add(new multiple(firstNum,secondNum));
		int j = secondNum;
		int i = firstNum;
		while(j > 0 && j/2 != 0)
		{
			i = i*2;
			j = (int) Math.floor(j/2);
			timesTable.add(new multiple(i,j));
		}
		for(int k = 0; k < timesTable.size(); k++)
		{
			multiple temp = (multiple) timesTable.get(k);
			output += "\n" +(temp);
			if(!temp.ignore())
				sum += temp.getFirst();
		}
		output += ("\n---------------------------------------");
		output += ("\n" + firstNum + " * " + secondNum + " = " + sum);	
		JOptionPane.showMessageDialog(null,output);
		System.out.println(output);
	}
	static class multiple
	{
		int first, second;
		public int getFirst() {
			return first;
		}
		public int getSecond() {
			return second;
		}
		multiple(int first, int second)
		{
			this.first = first;
			this.second = second;
		}
		boolean ignore()
		{
			if(second%2 == 0)
				return true;
			else 
				return false;
		}
		public String toString()
		{
			String temp = first + " : " + second;
			if(this.ignore())
				temp += "  => Evil Ignore";
			else
				temp += "  => Good Keep";
			return temp;
		}
	}

}
