import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Hangman extends JFrame {
    /**
     * instance variable, gLabel is a Label on which the hangman is drawn
     */
    private JLabel gLabel;
    /**
     * instance variable, wordTable is a table that contains places for the letter to be compared with the
     * actual word in the game
     */
    private JTable wordTable;
    /**
     * instance variable, word is the hidden word in the game
     */
    private String word;
    /**
     * instance variable, hg1 is the image if the hangman that gets updated each time the user has a wrong guess
     */
    private BufferedImage hg1;
    /**
     * instance variable, integer j keeps track of the wrong guesses
     */
    private static int j;
    /**
     * instance variable, integer correct keeps track of the correct guesses to let the user know if they win
     */
    private static int correct;
    /**
     * instance variable, JLabel lives prints the number of wrong guesses left for the user to make and the
     * number of wrong guesses made so far
     */
    private JLabel lives;
    /**
     * instance variable, String wGuesses shows the user what wrong characters they have guessed so far
     */
    private String wGuesses;
    /**
     * instance variable, JLabel wrongGuesses is a label containing the wrong guesses the user has made
     */
    private JLabel wrongGuesses;

    /**
     * constructor, creates a JFrame with a certain size and adds table according to the word given to it as an input
     * @param word, String is passed to the function as input
     */
    public Hangman(String word) {

            super("UI - Hangman");
            ///import scanner and uncomment the three lines below to check program
//            System.out.println("Please enter a word to be guessed by the player");
//            Scanner sc = new Scanner(System.in);
//            word = sc.next();
        this.word=word;
            ///set the size of the frame
            this.setSize(800,800);
            ///specify that we want the program to close if the close button is pressed
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ///set the frame's background color
            ///Note --> contentPane is the layer that we can add objects to
            this.getContentPane().setBackground(Color.PINK);
            ///set a new layout for the frame / Flow adds all objects in a row
            this.setLayout(new FlowLayout());

        wordTable = new JTable(1,word.length());
        wordTable.setGridColor(Color.BLACK);
        ///set the initial number of j to 1 and allow user to have up to 6 wrong guesses before gameover
        j=1;
        this.getContentPane().add(wordTable);
        this.setVisible(true);

    }

    /**
     * function adds a textfield for user to enter letters also loads the hangman picture and updates it each
     * time user enters a wrong guess
     */
    public void setupProgram()  {
        ////instantiate gLabel
        gLabel = new JLabel();
        ///add a textfield for the user to enter letter
        JTextField guess = new JTextField("Enter letter here");
        ///add textfield to frame
        this.getContentPane().add(guess);
        lives = new JLabel();
        wrongGuesses = new JLabel();
        wGuesses="Wrong guesses:";
        this.getContentPane().add(lives);
        ///add action listener to textfield to receive the letter
        guess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                ///in case user enters a word instead of a character let the user no that this is not allowed
                if (guess.getText().length() != 1) {
                    guess.setText("This is not allowed");
                } else {
                    ///store the first character of the word entered and compare to the actual hidden word
                    Character guessChar = guess.getText().charAt(0);
                    System.out.println(guessChar);
                    ArrayList<Integer> keep = checkLetter(guessChar);
                    ///if guess was wrong update hangman picture
                    if (keep.size() == 0) {
                        wGuesses += guess.getText().charAt(0);
                        wrongGuesses.setText(wGuesses);
                        String checkLives = "number of lives left:" + (6 - j) + "\n incorrect guesses" + (j);
                        lives.setText(checkLives);
                        j++;
                        paintHangMan();
                    }
                    ///if it was right update the table and place the correct guessed character on the table
                    for (int i = 0; i < keep.size(); i++) {
                        wordTable.setValueAt(guessChar, 0, keep.get(i));
                        correct++;
                    }
                    ///check to see if user wins
                    if (correct == word.length()) {
                        paintWonMessage();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            System.out.println("Error! interrupted thread");
                        }
                        System.exit(0);
                    }
                }
            }
        });

        ///Only load the first image for hangman and add to frame
        try {
            hg1 = LoadImage("hangman_gui/hangman_1.png");
            Paint(this.getGraphics(), hg1, 0);
        } catch (Exception e) {
            System.out.println("Error, could not open file");
        }

          this.getContentPane().add(wrongGuesses);
          this.getContentPane().add(gLabel);
          this.setVisible(true);

    }
    /**
     *function paints the hangman on the screen and terminates the program once six wrong guesses have been made
     */
    private void paintHangMan() {
        try {
            hg1 = LoadImage("hangman_gui/hangman_" + Integer.toString(j) + ".png");
            Paint(this.getGraphics(), hg1, 0);
        } catch (Exception e) {
            System.out.println("Error! could not open file.");
        }
        ///Once gameover all wrong guesses used, terminate the program.
        if(j==7){
            try {

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error, thread interrupted");
            }
            System.exit(0);
        }
    }

    /**
     * function paints winning message to screen or an error message in case it cannot open the image file
     * no input, it load a certain image
     * no output, result is printed to screen
     */
    private void paintWonMessage(){
        try {
            hg1 = LoadImage("hangman_gui/game_won.png");
            Paint(this.getGraphics(), hg1, 0);
        } catch (Exception e) {
            System.out.println("Error! could not open file.");
        }
    }
    /**
     * function compares the letter given to it to the actual word
     * @param guess is passed to the function to be compared to the letters in the word
     * @return Location of the correct letter
     */
    private ArrayList<Integer> checkLetter(Character guess){
        ArrayList<Integer> keep = new ArrayList<>();
        guess = Character.toLowerCase(guess);
            try {
                for (int i = 0; i < word.length(); i++) {
                    Character store = Character.toLowerCase(word.charAt(i));
                    if (store.equals(guess)) {

                        keep.add(i);
                    }
                }
            }
            catch(Exception e){
                System.out.println("Error, format violated");
            }
            return keep;
    }

    /**
     * Loads an image and stores it as a buffered image
     * @param FileName - receives the file path as a String
     * @return returns a BufferedImage
     */
    private static BufferedImage LoadImage(String FileName){
        BufferedImage imageToLoad = null;
        try {
            imageToLoad = ImageIO.read(new File(FileName));
        } catch (IOException e) {
            System.out.println("Error, failed to find image.");
        }
        return imageToLoad;
    }

    /**
     * Note: function copied from my ^^image rotator program^^ in this case I don't need to rotate the image
     * rotates an image by x degrees and draws it to the screen
     * @param g - graphics g
     * @param image - the loaded image to be rotated
     * @param x double - the degrees to rotate
     */
    private static void Paint(Graphics g, BufferedImage image, double x) {
        //AffineTransform is basically a matrix multiplication which can be used to rotate an image because it
        //preserves the distance between points in the image
        AffineTransform at = AffineTransform.getTranslateInstance(100,100);
        //rotate the affine transform by the degree x passed to the method
        at.rotate(Math.toRadians(x), image.getWidth()/2, image.getHeight()/2);
        ///create graphics 2d
        // The Graphics2D class extends the Graphics class to provide more sophisticated control over geometry,
        // coordinate transformations, color management,
        // and text layout.
        Graphics2D g2d = (Graphics2D) g;
        ///draw the graphics to the
        g2d.drawImage(image, at, null);
    }
}
