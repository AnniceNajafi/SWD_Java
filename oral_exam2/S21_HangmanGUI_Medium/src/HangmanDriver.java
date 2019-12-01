import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanDriver {
    public static void main(String[] args){
        ///Make a JFrame for the word to be entered
        JFrame startGame = new JFrame("start Game");
        ///set the size of the JFrame
        startGame.setSize(800,800);
        ///make the JFrame close if the close button is pressed
        startGame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ///Set color of the frame to orange
        startGame.getContentPane().setBackground(Color.PINK);
        ///set a new layout for the frame / Flow adds all objects in a row
        startGame.setLayout(new FlowLayout());
        ///receive the word
        JPasswordField wordField = new JPasswordField("word");
        startGame.add(wordField);
        startGame.add(new JLabel("Please enter word to be guessed by user in the field and press enter"));
        startGame.setVisible(true);
        ///start the game once word is received
        wordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = String.valueOf(wordField.getPassword());
                Hangman game = new Hangman(word);
                game.setupProgram();
                ///
            }
        });
    }

}
