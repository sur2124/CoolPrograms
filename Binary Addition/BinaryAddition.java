   import javax.swing.JOptionPane;

/**
 * This program adds two binary 
 * numbers of ANY length and 
 * returns an answer in binary,
 * decimal and Hex
 * @author Suraj Kulkarni
 */
    public class BinaryAddition {
       public static void main(String[] args){
         JOptionPane.showMessageDialog(null, "This Program adds two binary" +
                " numbers of ANY length\nand outputs the" +
                " answer in binary, decimal, and Hex format" +
                "\n\n\tCoded and Developed by:\n                                  Suraj Kulkarni");
         String firstNum = JOptionPane.showInputDialog("Input the first binary number");
         firstNum = isBinary(firstNum); 
         while(firstNum.length() == 0)
         {
            firstNum = JOptionPane.showInputDialog("You did not type in a binary number, please try again\n    Input the first binary number");
            firstNum = isBinary(firstNum);
         }
         String secondNum = JOptionPane.showInputDialog("Input the second binary number");
         secondNum = isBinary(secondNum);
         while(secondNum.length() == 0)
         {
            secondNum = JOptionPane.showInputDialog("You did not type in a binary number, please try again\n     Input the second binary number");
            secondNum = isBinary(secondNum);
         }
         String binary = addBinary(firstNum, secondNum);
         JOptionPane.showMessageDialog(null, "The Binary representation is:\n\t" +
                binary + "\nThe Decimal representation is:\n\t" + binaryToDigit(binary) +
                "\nThe HEX representation is:\n\t0x" + binaryToHex(binary) + "\n");
         for(int i = 0; i < 80; i++)
         {
            System.out.print("*");
         }
         System.out.println();
         System.out.println("*");
         System.out.print("*");
         for(int i = 0; i < 20; i++)
            System.out.print(" ");
         System.out.println("Thank You for using Suraj Kulkarni's program");
         System.out.println("*");
         for(int i = 0; i < 80; i++)
         {
            System.out.print("*");
         }	
      }
       
       /**
        * Checks if a string is 
        * a binary number or not
        * @param oneZero
        * @return 
        */
       public static String isBinary(String oneZero)
      {
         for(int i = 0; i < oneZero.length(); i++)
         {
            if(oneZero.charAt(i) != '0' && oneZero.charAt(i) != '1')
            {
               return new String();
            }
         }
         return oneZero;
      }
       
    /**
     * Adds two binary numbers 
     * and returns the result
     * @param a
     * @param b
     * @return
     */
       public static String addBinary(String a, String b) {
         String temp = "";
         if (a.length() > b.length()) {
            b = reverseString(b);
            for (int i = 0; i < a.length() - b.length(); i++) {
               temp += 0;
            }
            b = b + temp;
            b = reverseString(b);
         } 
         else if (b.length() > a.length()) {
            a = reverseString(a);
            for (int i = 0; i < b.length() - a.length(); i++) {
               temp += 0;
            }
            a = a + temp;
            a = reverseString(a);
         }
         int[] answer = new int[a.length()];
         int sum;
         String x, y;
         carry = false;
         for (int i = b.length() - 1; i >= 0; i--) {
            x = a.charAt(i) + "";
            y = b.charAt(i) + "";
            sum = Integer.parseInt(x) + Integer.parseInt(y);
            if (sum == 0) {
               if (carry == true) {
                  answer[i] = 1;
                  carry = false;
               } 
               else {
                  answer[i] = 0;
                  carry = false;
               }
            } 
            else if (sum == 1) {
               if (carry == true) {
                  answer[i] = 0;
                  carry = true;
               } 
               else {
                  answer[i] = 1;
                  carry = false;
               }
            } 
            else {
               if (carry == true) {
                  answer[i] = 1;
                  carry = true;
               } 
               else {
                  answer[i] = 0;
                  carry = true;
               }
            }
         }
         String ans = "";
         for (int i = 0; i < answer.length; i++) {
            ans = ans + answer[i];
         }
         if (carry == true) {
            ans = 1 + ans;
         }
         return ans;
      }
   
    /**
     * Converts the binary number 
     * to decimal format
     * @param binary
     * @return
     */
       public static String binaryToDigit(String binary) {
         String decimal = "0";
         for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
               decimal = addDigit(decimal, getVal(binary.length() - 1 - i, "1"));
            }
         }
         return decimal;
      }
   
    /**
     * recursively finds 
     * the value of an index
     * @param i
     * @param value
     * @return
     */
       public static String getVal(int i, String value) {
         if (i == 0) {
            return value;
         }
         value = addDigit(value, value);
         return getVal(i - 1, value);
      }
   
    /**
     * reverses a string
     * @param line
     * @return
     */
       private static String reverseString(String line) {
         String temp = "";
         for (int i = line.length() - 1; i >= 0; i--) {
            temp += line.charAt(i);
         }
         line = temp;
         return line;
      }
   
    /**
     * Adds two digits together
     * @param a
     * @param b
     * @return
     */
       public static String addDigit(String num1, String num2) {
         int maxLengthOfInput = num1.length();
         if (num2.length() > num1.length()) {
            maxLengthOfInput = num2.length();
         }
         char[] arrayNum1 = new char[maxLengthOfInput];
         char[] arrayNum2 = new char[maxLengthOfInput];
         for (int i = 0; i < maxLengthOfInput; i++) {
            char c1;
            char c2;
            if (num1.length() <= i) {
               c1 = '0';
            } 
            else {
               c1 = num1.charAt((num1.length() - i) - 1);
            }
            if (num2.length() <= i) {
               c2 = '0';
            } 
            else {
               c2 = num2.charAt((num2.length() - i) - 1);
            }
            arrayNum1[(maxLengthOfInput - i) - 1] = c1;
            arrayNum2[(maxLengthOfInput - i) - 1] = c2;
         }
         StringBuffer result = new StringBuffer();
         boolean isPrevCarryOver = false;
         for (int i = 0; i < maxLengthOfInput; i++) {
            int pos = (maxLengthOfInput - i) - 1;
            boolean isCurrentCarryOver = false;
            if (isPrevCarryOver) {
               if (arrayNum1[pos] != '9') {
                  arrayNum1[pos] = (addDecimalDigitsAsCharacters(
                            arrayNum1[pos], '1')).charAt(0);
                  isPrevCarryOver = false;
               } 
               else if (arrayNum2[pos] != '9') {
                  arrayNum2[pos] = (addDecimalDigitsAsCharacters(
                            arrayNum2[pos], '1')).charAt(0);
                  isPrevCarryOver = false;
               }
            }
            String s = addDecimalDigitsAsCharacters(arrayNum1[pos],
                    arrayNum2[pos]);
            if (s.length() > 1) {
               isCurrentCarryOver = true;
            }
            if (isPrevCarryOver) {
               if (isCurrentCarryOver) {
                  result.insert(0, addDecimalDigitsAsCharacters(s.charAt
                            (s.length() - 1), '1'));
               } 
               else if (s.equals("9")) {
                  result.insert(0, '0');
                  isCurrentCarryOver = true;
               }
               isPrevCarryOver = false;
            } 
            else {
               result.insert(0, s.charAt(s.length() - 1));
            }
            if (isCurrentCarryOver) {
               isPrevCarryOver = true;
            }
         }
         if (isPrevCarryOver) {
            result.insert(0, '1');
         }
         return result.toString();
      }
   
    /**
     * adds two digits together
     * @param digit1
     * @param digit2
     * @return
     */
       private static String addDecimalDigitsAsCharacters(char digit1, char digit2) {
         assert digit1 <= 9 && digit1 >= 0 : "erroroneous input 1";
         assert digit2 <= 9 && digit2 >= 0 : "erroroneous input 2";
         String s1 = digit1 + "";
         String s2 = digit2 + "";
         int i = Integer.parseInt(s1) + Integer.parseInt(s2);
         return i + "";
      }
   
    /**
     * Converts 4-bits 
     * into a hex 
     * @param bit
     * @return
     */
       public static String hexHelper(String digit) {
         if (digit.equals("0000")) {
            return "0";
         } 
         else if (digit.equals("0001")) {
            return "1";
         } 
         else if (digit.equals("0010")) {
            return "2";
         } 
         else if (digit.equals("0011")) {
            return "3";
         } 
         else if (digit.equals("0100")) {
            return "4";
         } 
         else if (digit.equals("0101")) {
            return "5";
         } 
         else if (digit.equals("0110")) {
            return "6";
         } 
         else if (digit.equals("0111")) {
            return "7";
         } 
         else if (digit.equals("1000")) {
            return "8";
         } 
         else if (digit.equals("1001")) {
            return "9";
         } 
         else if (digit.equals("1010")) {
            return "A";
         } 
         else if (digit.equals("1011")) {
            return "B";
         } 
         else if (digit.equals("1100")) {
            return "C";
         } 
         else if (digit.equals("1101")) {
            return "D";
         } 
         else if (digit.equals("1110")) {
            return "E";
         //else if(digit.equals("1111"))
         }
         return "F";
      }
   
    /**
     * Converts a binary number 
     * to Hexadecimal format
     * @param binary
     * @return
     */
       public static String binaryToHex(String binary) {
         int length = binary.length() % 4;
         for (int i = 0; i < 4 - length; i++) {
            binary = '0' + binary;
         }
         String hex = "";
         for (int i = 0; i < binary.length(); i += 4) {
            hex = hex + hexHelper(binary.substring(i, i + 4));
         }
         return hex;
      }
      private static boolean carry;
   }
