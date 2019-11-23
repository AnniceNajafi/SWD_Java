import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Hangman extends JFrame {
    private JTable wordTable;
    private String word;
    public Hangman(){
            super("UI - Hangman");
            System.out.println("Please enter a word to be guessed by the player");
            Scanner sc = new Scanner(System.in);
            word = sc.next();
            ///set the size of the frame
            this.setSize(500,500);
            ///specify that we want the program to close if the close button is pressed
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ///set the frame's background color
            ///Note --> contentPane is the layer that we can add objects to
            this.getContentPane().setBackground(Color.PINK);
            ///set a new layout for the frame / Flow adds all objects in a row
            this.setLayout(new FlowLayout());
            wordTable = new JTable(1,word.length());
            wordTable.setGridColor(Color.BLACK);
            this.getContentPane().add(wordTable);
            this.setVisible(true);



    }
    public void setUpProgram(){


    }
    public void addGuessTextField(){
        JTextField guess = new JTextField("Enter letter here");
        this.getContentPane().add(guess);
        guess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Character guessChar = guess.getText().charAt(0);
                System.out.println(guessChar);
                ArrayList<Integer> keep = checkLetter(guessChar);
                for(int i=0; i<keep.size(); i++){
                    wordTable.setValueAt(guessChar, 0, keep.get(i));
                }
            }
        });
        this.setVisible(true);

    }

    /**
     *
     * @param guess
     * @return Location of the correct letter
     * @throws NumberFormatException
     */
    private ArrayList<Integer> checkLetter(Character guess) throws NumberFormatException{
        ArrayList<Integer> keep = new ArrayList<>();
            try {
                for (int i = 0; i < word.length(); i++) {
                    Character store = word.charAt(i);
                    if (store.equals(guess)) {
                        keep.add(i);
                    }
                }
            }
            catch(Exception e){

            }
            return keep;
    }
}
