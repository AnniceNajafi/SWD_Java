import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GuessNumber extends JPanel {

    private int number;
    private JFrame frame;
    private JTextField enter;
    private JLabel instructions;
    private JButton numberPicker;
    private JLabel hotOrCold;
    private int prevGuess;

    public GuessNumber(){
        prevGuess=500;
        frame = new JFrame("Guess Number game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ///Set the size of the frame
        frame.setSize(1000, 800);
        ///Set the color of the frame
        this.setBackground(Color.ORANGE);
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
    private void resetFrame(){
        enter.setEditable(true);
        this.setBackground(Color.orange);
        instructions.setText("I have a number between 1 and 1000. Can you guess my number?");
        hotOrCold.setText("Welcome");
    }
    private void pickNumber(){
        number = new Random().nextInt(1000) ;
        System.out.println(number);
    }

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
