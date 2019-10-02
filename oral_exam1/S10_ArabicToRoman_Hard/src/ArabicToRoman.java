/**
 * @Author: Annice Najafi
 * @Date: 9/27/2019
 * LevelOfDifficulty: HARD
 * description: This program has two textfields which can receive a number in Roman and show the equivalent Arabic number
 * or vice versa.
 */

///Add GUI
import org.w3c.dom.Text;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ArabicToRoman {
    //main class
public static void main(String[] args){
    ///Create a frame that contains two text fields for numbers to be converted
    JFrame inputFrame= new JFrame("UI - number converter");
    ///set the size of the frame
    inputFrame.setSize(500,500);
    ///specify that we want the program to close if the close button is pressed
    inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ///set the frame's background color
    ///Note --> contentPane is the layer that we can add objects to
    inputFrame.getContentPane().setBackground(Color.YELLOW);
    ///set a new layout for the frame / Flow adds all objects in a row
    inputFrame.setLayout(new FlowLayout());
    ///define a Font for the program to be added to the text fields.
    Font myFont = new Font("TimesRoman",2, 20);
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
/*
function name: arabicToRoman
function: receives an Arabic number and returns the equivalent Roman number
function input:String - Arabic number
function output:String - Roman number
****NOTE: this function receives the Arabic number as a string
 */
public static String arabicToRoman(String s) throws NullPointerException, NumberFormatException{

    String arabicNum =s;
     ///check if number entered is negative
    String romanNum="";
    if(Integer.parseInt(arabicNum)<0){
        romanNum="Your input is not valid, there are no negative Roman numbers.";
    }
    ///otherwise, convert teh String to an integer
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
            }
        }


    }
    return romanNum;
}
/*
function name: romanToArabic
function: receives a Roman number and returns the equivalent Arabic number
function input:String - Roman number
function output:String - Arabic number
****NOTE: this function returns the Arabic number as a string
 */

public static String romanToArabic(String romanNum) throws NullPointerException{
    ///A map relates the reference Roman numbers to Arabic numbers. This way every digit in the Roman number can be
    ///easily converted to an integer
    Map <Character, Integer> numbers = new HashMap<Character, Integer>();
    numbers.put('I', 1);
    numbers.put('V', 5);
    numbers.put('X', 10);
    numbers.put('L', 50);
    numbers.put('C', 100);
    numbers.put('D', 500);
    numbers.put('M', 1000);
    ///The arabic number will be stored in the variable arabicNum which is initialized to zero
    int arabicNum=0;
    ///The function loops through the Roman number and evaluates every letter of the Roman number
    for(int i=0; i<romanNum.length(); i++){
        ///Check if the number entered is valid
//        if(i!=romanNum.length()-1 && numbers.get(romanNum.charAt(i)).equals(numbers.get(romanNum.charAt(i+1)))){
//            System.out.println("Error not a valid number");
//        }
        ///If the character after the character is less, subtract otherwise add
        if(i!=romanNum.length()-1 && numbers.get(romanNum.charAt(i))<numbers.get(romanNum.charAt(i+1))){
            arabicNum-=numbers.get(romanNum.charAt(i));
        }
        else{
            arabicNum+=numbers.get(romanNum.charAt(i));
        }

    }


    return Integer.toString(arabicNum);
}

}
