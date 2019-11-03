import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame {
    private JFrame inputFrame;
    private JFrame receivingFrame;
    private JTable wordTable;
    private String word;
    public Hangman(){

            inputFrame = new JFrame("UI - Hangman");;
            ///set the size of the frame
            inputFrame.setSize(500,500);
            ///specify that we want the program to close if the close button is pressed
            inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ///set the frame's background color
            ///Note --> contentPane is the layer that we can add objects to
            inputFrame.getContentPane().setBackground(Color.PINK);
            ///set a new layout for the frame / Flow adds all objects in a row
            inputFrame.setLayout(new FlowLayout());
            ///////////////////////////////////////
            receivingFrame = new JFrame("UI - Hangman");

            ///set the size of the frame
            receivingFrame.setSize(500,500);
            ///specify that we want the program to close if the close button is pressed
            receivingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ///set the frame's background color
            ///Note --> contentPane is the layer that we can add objects to
            receivingFrame.getContentPane().setBackground(Color.BLUE);
            ///set a new layout for the frame / Flow adds all objects in a row
            receivingFrame.setLayout(new FlowLayout());



    }
    public void addTable(){

    }
    public void receiveWord(){
        JTextField wordToBeGuessed = new JTextField("Please enter a word");
        receivingFrame.add(wordToBeGuessed);
        JButton receive = new JButton("Submit");
        receivingFrame.getContentPane().add(receive);
        receivingFrame.setVisible(true);
        receive.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) throws NumberFormatException, NullPointerException{
                word = wordToBeGuessed.getText();
                receivingFrame.dispose();
                wordTable = new JTable(1,word.length());
                inputFrame.getContentPane().add(wordTable);
                inputFrame.setVisible(true);
            }
        });



    }
    public void setUpProgram(){


    }
    public void addGuessTextField(){
        JTextField guess = new JTextField("Enter letter here");
        inputFrame.getContentPane().add(guess);
        Character guessChar = guess.getText().charAt(0);
        inputFrame.setVisible(true);
        guess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(int i=0; i<word.length(); i++){
                    checkLetter(guessChar);
                }
            }
        });

    }
    private void checkLetter(Character guess) throws NumberFormatException{
            for(int i=0; i<word.length(); i++){
                if(word.charAt(i)==guess){
                    wordTable.setValueAt(word.charAt(i), 1, i);
                    inputFrame.setVisible(true);
                }
            }

    }
}
