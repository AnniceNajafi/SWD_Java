
///Add GUI
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

/**
 * @author: Annice Najafi
 * Date: 9/29/2019
 * LevelOfDifficulty: HARD
 * description: This program
 */

public class ImageRotator extends JFrame{

    private static int i=0;
    private static Timer timer;
    private static JFrame spinWheel;
    private static Clip clip;
    private static int speed;
    private static boolean stopme;
    /**
     * constructor
     */
    public ImageRotator() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        ///Create a frame that contains the spinning wheel
        spinWheel = new JFrame("Spinning Wheel");
        spinWheel.setSize(1000,1000);
        spinWheel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinWheel.setLayout(new FlowLayout());
        spinWheel.getContentPane().setBackground(Color.pink);

        ///VOICE FILES
        File voice1 = new File("voice1.wav");
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(voice1));

    }

    /**
     * Adds the image and the related buttons to the frame
     * @throws  IOException
     */
    public void setupImageRotator() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        ///At this point we need to add the spinning wheel and define a function that repaints the wheel in the frame
        // each time it rotates.
        File JokerLaugh =new File("427574__brainclaim__joker-laugh-1.wav");
        Clip clip1;
        clip1 = AudioSystem.getClip();
        clip1.open(AudioSystem.getAudioInputStream(JokerLaugh));
        clip1.start();
        JLabel here = new JLabel("↓");
        spinWheel.add(here);
        BufferedImage wheel = LoadImage("wheel2.png");
        /*
         * First part: rotate by degrees specified by the user
         */
        JTextField receive = new JTextField("Rotate by degrees");
        receive.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double degrees = Double.parseDouble(receive.getText());
                try {
                    Paint(spinWheel.getGraphics(), wheel, degrees);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        /*
         * Second part: rotate the wheel by random degrees
         */
        spinWheel.getContentPane().add(receive);
        JButton rotate = new JButton("rotate");
        JButton spin = new JButton("Rotate Randomly");
        spin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double randdegrees = Math.random()*360+1;
                try {
                    Paint(spinWheel.getGraphics(), wheel, randdegrees);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
        spinWheel.getContentPane().add(spin);
        spinWheel.getContentPane().add(rotate);
        rotate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double degrees = Double.parseDouble(receive.getText());
                try {
                    Paint(spinWheel.getGraphics(), wheel, degrees);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        /*
         * Third part: spin dynamically
         */
        //////STOP BUTTON
        JButton stop = new JButton("Stop");

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopme = true;
                spinWheel.getContentPane().setBackground(Color.PINK);
            }
        });
        spinWheel.getContentPane().add(stop);
        //////START BUTTON
        JButton start = new JButton("Start");

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopme = false;
            }
        });
        spinWheel.getContentPane().add(start);
        //////THE SLIDER
        JSlider speedSlider = new JSlider(10,1000, 50);
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                        timer = new Timer();
                        speed = speedSlider.getValue();
                        spinWheel.getContentPane().setBackground(Color.YELLOW);
                        timer.scheduleAtFixedRate(new TimerTask() {
                            public void run() {
                                try {
                                    if(!stopme) {
                                        Paint(spinWheel.getGraphics(), wheel, i++);
                                        clip.start();
                                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                                    }
                                    else if(stopme){
                                        timer.cancel();
                                        clip.stop();
                                        clip.loop(0);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 100, speed);
            }
        });
        spinWheel.getContentPane().add(speedSlider);





        /*
         *Add a start frame to show before the main spinning wheel frame
         * START FRAME
         */
        JFrame startFrame = new JFrame("Welcome");
        startFrame.setLayout(new FlowLayout());
        startFrame.setSize(1000, 1000);
        startFrame.add(new JLabel(new ImageIcon("Joker.jpg")));
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.getContentPane().setBackground(Color.ORANGE);
        startFrame.add(new JLabel("Welcome, click this button to start spinning"));
        JButton play = new JButton("Play");
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startFrame.dispose();
                spinWheel.setVisible(true);
            }
        });
        startFrame.add(play);
        startFrame.setVisible(true);

    }

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

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
     * rotates an image by x degrees and draws it to the screen
     * @param g - graphics g
     * @param image - the loaded image to be rotated
     * @param x double - the degrees to rotate
     * @throws IOException
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
/*
 *image reference:
 * https://wheel.fhtl.byu.edu/#/
 * https://www.pinterest.com/pin/35888128251108254/
 * credit given to:
 *  https://stackoverflow.com/questions/8639567/java-rotating-images
 * All sound tracks reference:
 * https://freesound.org
 * ---Fall 2019---
 * University of Iowa
 */

