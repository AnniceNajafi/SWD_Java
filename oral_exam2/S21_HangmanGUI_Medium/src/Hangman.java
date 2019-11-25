import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman extends JFrame {
    private JLabel gLabel;
    private JTable wordTable;
    private String word;
    private BufferedImage hg1;
    private static int j;
    public Hangman() throws IOException {
            super("UI - Hangman");
            System.out.println("Please enter a word to be guessed by the player");
            Scanner sc = new Scanner(System.in);
            word = sc.next();
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
            this.getContentPane().add(wordTable);
            this.setVisible(true);
            hg1 = LoadImage("hangman_gui/hangman_1.png");
            j=1;

    }
    public void setUpProgram(){


    }
    public void addGuessTextField() throws IOException {
        ////
        gLabel = new JLabel();
//        g1 = (Graphics2D) hg1.getGraphics();
        JTextField guess = new JTextField("Enter letter here");
        this.getContentPane().add(guess);
        guess.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Character guessChar = guess.getText().charAt(0);
                System.out.println(guessChar);
                ArrayList<Integer> keep = checkLetter(guessChar);

            if(keep.size()==0){
                    j++;
                try {
                    paintHangMan();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
                for(int i=0; i<keep.size(); i++){
                    wordTable.setValueAt(guessChar, 0, keep.get(i));

                }
            }
        });


          hg1 = LoadImage("hangman_gui/hangman_" + Integer.toString(j) + ".png");
          Paint(this.getGraphics(), hg1, 0);

          this.getContentPane().add(gLabel);
          this.setVisible(true);

    }
    private void paintHangMan() throws IOException {
        hg1 = LoadImage("hangman_gui/hangman_" + Integer.toString(j) + ".png");
        Paint(this.getGraphics(), hg1, 0);
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
    private static Image getImage(String FileName) throws IOException {
        Image image = null;
       try{
           image=ImageIO.read(new File(FileName));
//            URL imageURL = Hangman.class.getResource(FileName);
//            image = Toolkit.getDefaultToolkit().getImage(imageURL);
       }catch(Exception e){
           System.out.println("Error! loading image");
       }
        return image;
    }

    public void draw(Image img, Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(img, 100, 300, 100, 100, gLabel);
    }
    /**
     * Loads an image and stores it as a buffered image
     * @param FileName - receives the file path as a String
     * @return returns a BufferedImage
     * @throws IOException if path not found
     */
    private static BufferedImage LoadImage(String FileName) throws IOException{
        BufferedImage imageToLoad = null;
        imageToLoad = ImageIO.read(new File(FileName));
        return imageToLoad;
    }

    /**
     * Note: function copied from my ^^image rotator program^^ in this case I don't need to rotate the image
     * rotates an image by x degrees and draws it to the screen
     * @param g - graphics g
     * @param image - the loaded image to be rotated
     * @param x double - the degrees to rotate
     * @throws IOException if path was not valid
     */
    private static void Paint(Graphics g, BufferedImage image, double x) throws IOException {
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
        /*
        NOTE --> see references for the reference code
         */
    }
}
