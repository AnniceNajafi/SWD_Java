
///Add GUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Annice Najafi
 * Date: 9/27/2019
 * LevelOfDifficulty: HARD
 * description: This program has two textfields which can receive a number in Roman and show the equivalent Arabic number
 * or vice versa.
 */

public class ArabicToRoman extends JFrame{
    //instance variables
        private JFrame inputFrame;
        private Font myFont;
    /**
     * constructor
     * sets the name of the frame, the layout, font, size of the frame, etc
     */
    public ArabicToRoman(){
        inputFrame = new JFrame("UI - number converter");;
        myFont = new Font("TimesRoman",2, 20);
        ///set the size of the frame
        inputFrame.setSize(500,500);
        ///specify that we want the program to close if the close button is pressed
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///set the frame's background color
        ///Note --> contentPane is the layer that we can add objects to
        inputFrame.getContentPane().setBackground(Color.YELLOW);
        ///set a new layout for the frame / Flow adds all objects in a row
        inputFrame.setLayout(new FlowLayout());

    }

    /**
     *adds two textfields with action listeners to the frame
     */
    public void setUpProgram(){
        ///Create a frame that contains two text fields for numbers to be converted

        ///add an image to the frame
        inputFrame.add(new JLabel(new ImageIcon("download.png")));
        ///add a label for description to the frame
        inputFrame.add(new JLabel("<html>Hello, this program can convert Arabic numbers to Roman and Roman to Arabic<br/>" +
                "Please enter a number in either format to get converted</html>"), BorderLayout.NORTH);
        ///Define the two text fields
        JTextField romanEnter = new JTextField("Roman");
        JTextField arabicEnter = new JTextField("Arabic");
        ///Use the define Font
        romanEnter.setFont(myFont);
        arabicEnter.setFont(myFont);
        ///Add them to the contentPane
        inputFrame.getContentPane().add(arabicEnter);
        inputFrame.getContentPane().add(romanEnter);
        ///Add action listener which performs the functions each time the enter key is pressed
        romanEnter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) throws NumberFormatException, NullPointerException{
                String romanTe = romanEnter.getText();
                arabicEnter.setText(null);
                arabicEnter.setText(romanToArabic(romanTe));
            }
        });
        arabicEnter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) throws NumberFormatException, NullPointerException{
                String arabTe = arabicEnter.getText();
                romanEnter.setText(null);
                romanEnter.setText(arabicToRoman(arabTe));
            }
        });

        ///Make the frame with textfields and everything visible
        inputFrame.setVisible(true);
    }
    //main class
public static void main(String[] args){

}


    /**
     * receives an Arabic number and returns the equivalent Roman number
     * @param s String - Arabic number
     * @return String - the equivalent of a Roman number
     * @throws NullPointerException
     * @throws NumberFormatException
     */
    public String arabicToRoman(String s) throws NullPointerException, NumberFormatException{

    String arabicNum =s;
     ///check if number entered is negative
    String romanNum="";
    if(Integer.parseInt(arabicNum)<0){
        romanNum="Your input is not valid, there are no negative Roman numbers.";
    }
    ///otherwise, convert the String to an integer
    else{
        ///Two arrays are used in this function. One contains the Arabic numbers and the other has equivalent
        ///Roman numbers
        int intArabicNum = Integer.parseInt(arabicNum);
        int[] arabicRef = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romanEq = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        ///function loops through the arrays and relates the Arabic number to the equivalent Roman number
        while (intArabicNum > 0) {
            for (int i = (arabicRef.length) - 1; i >= 0; i--) {
                if (intArabicNum >= arabicRef[i]) {
                    romanNum = romanNum + romanEq[i];
                    intArabicNum -= arabicRef[i];
                }
                if(intArabicNum>=arabicRef[i]){
                    i++;
                }
            }
        }


    }
    return romanNum;
}


    /**
     * receives a Roman number and returns the equivalent Arabic number
     * @param romanNum String Roman number
     * @return The equivalent Arabic number
     * @throws NullPointerException
     */
    public String romanToArabic(String romanNum) throws NullPointerException{
    ///A map relates the reference Roman numbers to Arabic numbers. This way every digit in the Roman number can be
    ///easily converted to an integer
    if(!validateRomanNum(romanNum)){
       return "Invalid input, Roman number rules violated";
    }
    else {
        Map<Character, Integer> numbers = new HashMap<Character, Integer>();
        numbers.put('I', 1);
        numbers.put('V', 5);
        numbers.put('X', 10);
        numbers.put('L', 50);
        numbers.put('C', 100);
        numbers.put('D', 500);
        numbers.put('M', 1000);

        ///The arabic number will be stored in the variable arabicNum which is initialized to zero
        int arabicNum = 0;
        ///The function loops through the Roman number and evaluates every letter of the Roman number
        for (int i = 0; i < romanNum.length(); i++) {
            ///Check if the number entered is valid
            ///If the character after the character is less, subtract otherwise add
            if (i != romanNum.length() - 1 && numbers.get(romanNum.charAt(i)) < numbers.get(romanNum.charAt(i + 1))) {
                arabicNum -= numbers.get(romanNum.charAt(i));
            } else {
                arabicNum += numbers.get(romanNum.charAt(i));
            }

        }


        return Integer.toString(arabicNum);
    }


}

    /**
     * Validates a Roman number, The roman number has to pass three tests, if violated returns a string letting the user know
     * @param roman the Roman Number
     * @return String - The Arabic Number
     */

private static boolean validateRomanNum(String roman){
    /*
    "In order for a number written in Roman numerals to be considered valid there are three basic rules that must be followed.
    I. Numerals must be arranged in descending order of size.
    II. M, C, and X cannot be equalled or exceeded by smaller denominations.
    III. D, L, and V can each only appear once."
    SOURCE: https://projecteuler.net/about=roman_numerals
    */
    int valid=0;
    int[] arabicRef = {1, 5, 10, 50, 100, 500, 1000};
    String[] romanEq = {"I", "V", "X", "L", "C", "D", "M"};
    String[] romanEqExtended = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    Map<Character, Integer> numbers = new HashMap<Character, Integer>();
    numbers.put('I', 1);
    numbers.put('V', 5);
    numbers.put('X', 10);
    numbers.put('L', 50);
    numbers.put('C', 100);
    numbers.put('D', 500);
    numbers.put('M', 1000);

    ///rule #1
    int check1=0;
    ///go through the roman number and check if each character is higher than the one after it, unless in the romanEqExtended list.

        ///for as long as the character before the last character is reached
        ///and the value of that character is smaller than the other character
        // if the character is in the romanEqExtended array it is valid, else it is not and violates the first rule
        int k=0;
        while (k!=roman.length()-1){
            if(numbers.get(roman.charAt(k))>numbers.get(roman.charAt(k+1))){
                k++;
            }
            else if(numbers.get(roman.charAt(k))==numbers.get(roman.charAt(k+1))){
                if(k<roman.length()-2 && numbers.get(roman.charAt(k))<numbers.get(roman.charAt(k+2))){
                    valid--;
                }
                k++;
            }
            else{

                    for (int j = 0; j < romanEqExtended.length; j++) {
                        if (romanEqExtended[j].equals((String.valueOf(roman.charAt(k)) + String.valueOf(roman.charAt(k + 1))))) {
                            check1++;
                        }
                    }
                    k += 1;

                if(check1==0){
                    valid--;
                }
            }

        }


    ///rule #2: X, I and C should not be repeated more than three times in a row.
    for(int i=0; i<roman.length()-3; i++){
        if(roman.charAt(i)=='I'&&roman.charAt(i+1)=='I'&&roman.charAt(i+2)=='I'&&roman.charAt(i+3)=='I'){
            valid--;
        }
        else if(roman.charAt(i)=='X'&&roman.charAt(i+1)=='X'&&roman.charAt(i+2)=='X'&&roman.charAt(i+3)=='X'){
            valid--;
        }
        else if(roman.charAt(i)=='C'&&roman.charAt(i+1)=='C'&&roman.charAt(i+2)=='C'&&roman.charAt(i+3)=='C'){
            valid--;
        }
    }


    ///rule #3 : Symbols V, D and L should not appear more than once
    int checkd=0;
    int checkl=0;
    int checkv=0;
    for(int i=0; i<roman.length(); i++){
        if(roman.charAt(i)=='D'){
            checkd++;
        }
        else if(roman.charAt(i)=='L'){
            checkl++;
        }
        else if(roman.charAt(i)=='V'){
            checkv++;
        }
    }
    if(checkv>1 || checkl>1 || checkd>1){
        valid--;
    }
    /////////////return
    if(valid<0){
        return false;
    }
    else{
        return true;
    }

}


}
