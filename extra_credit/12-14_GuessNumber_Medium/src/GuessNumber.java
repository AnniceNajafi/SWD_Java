import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GuessNumber extends JPanel {
    /**
     * instance variables
     */
    /**
     * number is the number to be guessed
     */
    private int number;
    /**
     * frame to hold the panel for the game
     */
    private JFrame frame;
    /**
     * textfield for the guess to be entered
     */
    private JTextField enter;
    /**
     * instructions as indicated by the problem statement
     */
    private JLabel instructions;
    /**
     * a button to set the number or restart the game
     */
    private JButton numberPicker;
    /**
     * This JLabel tells the user whether their guess is close to the actual number or not
     */
    private JLabel hotOrCold;
    /**
     * This variable holds the previous guess made by the user to get compared to the new guess
     */
    private int prevGuess;

    /**
     * constructor, creates a new JFrame and adds a JPanel to it and the related buttons and texts
     */
    public GuessNumber(){
        prevGuess=500;
        frame = new JFrame("Guess Number game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ///Set the size of the frame
        frame.setSize(1000, 800);
        ///Set the color of the frame
        this.setBackground(Color.ORANGE);
        ///Add instructions
        enter = new JTextField("Please enter number here");
        instructions=new JLabel("I have a number between 1 and 1000. Can you guess my number?");
        this.add(instructions);
        hotOrCold = new JLabel("Welcome");
        enter.setEditable(false);
        this.setLayout(new GridLayout(3, 1));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateNumber(enter.getText())){
                    checkNumber(Integer.parseInt(enter.getText()));
                    prevGuess=Integer.parseInt(enter.getText());
                }else{
                    hotOrCold.setText("Error, wrong format");
                }
            }
        });
        numberPicker = new JButton("Start Game!");
        numberPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pickNumber();
                resetFrame();
            }
        });
        this.add(hotOrCold);
        this.add(numberPicker);
        frame.add(this);
        this.add(enter);
        frame.setVisible(true);
    }

    /**
     * This function is defined to be called to reset the background of the game and the numeber
     */
    private void resetFrame(){
        enter.setEditable(true);
        this.setBackground(Color.orange);
        instructions.setText("I have a number between 1 and 1000. Can you guess my number?");
        hotOrCold.setText("Welcome");
    }

    /**
     * This function picks a random number between 0 and 1000
     */
    private void pickNumber(){
        number = new Random().nextInt(999)+1 ;
        System.out.println(number);
    }

    /**
     * This function validates that the user enters a valid number
     * @param entry is the user's input as a String
     * @return a boolean , true if it is a valid number false otherwise
     */
    private boolean validateNumber(String entry){
        do
        {
            try {

                int num = Integer.parseInt(entry);
                return num < 1000 && num>0;
            }
            catch (Exception e)
            {
                return false;
            }
        }
        while (true);
    }

    /**
     * checkNumber function checks if the number is higher than the actual number or lower and relative to the previous guess
     * then changes the background color
     * @param num is the number entered by the user to be checked
     */
    private void checkNumber(int num){
        if(Math.abs(number - num) > Math.abs(number - prevGuess)){
            this.setBackground(Color.RED);
            if(number-num<0){
                hotOrCold.setText("Higher than number");
            }else{
                hotOrCold.setText("Lower than number");
            }
        }else if(number == num){
            this.setBackground(Color.green);
            hotOrCold.setText("Correct");
            enter.setEditable(false);
        }
        else{
            this.setBackground(Color.BLUE);
            if(number-num<0){
                hotOrCold.setText("Too High");
            }else{
                hotOrCold.setText("Too Low");
            }
        }
    }
    public static void main(String[] args){
        GuessNumber test = new GuessNumber();

    }
}
