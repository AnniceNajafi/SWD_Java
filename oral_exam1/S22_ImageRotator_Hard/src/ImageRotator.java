/**
 * @Aurthor: Annice Najafi
 * @Date: 9/29/2019
 * LevelOfDifficulty: HARD
 * description: This program
 */
///Add GUI
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Timer;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImageRotator extends JFrame{
    static boolean stopspin;
    static int i=0;
    static Timer timer;// = new Timer();

    public static void setupImageRotator(){
        stopspin=true;
        ///Create a frame that contains the spinning wheel
        JFrame spinWheel = new JFrame("Spinning Wheel");
        BufferedImage wheel = LoadImage("wheel2.png");
        spinWheel.setSize(1000,1000);
        spinWheel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinWheel.setLayout(new FlowLayout());
        spinWheel.getContentPane().setBackground(Color.pink);
        ///VOICE FILES
        File voice1 = new File("src/voice1.wav");
        ///At this point we need to add the spinning wheel and define a function that repaints the wheel in the frame
        // each time it rotates.
        /**
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
        /**
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
        /**
         * Third part: spin dynamically
         */
        JSlider speedSlider = new JSlider(5,100);
        spinWheel.getContentPane().add(speedSlider);

        JButton start = new JButton("Start");
        int speed=speedSlider.getValue();
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                spinWheel.getContentPane().setBackground(Color.YELLOW);
                timer=new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        try {
                            PlaySound(voice1);
                            Paint(spinWheel.getGraphics(), wheel, i++);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, 1, speed);
            }

        });
        /**
         * STOP BUTTON _____________
         */
        JButton stop = new JButton("Stop");

        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                spinWheel.getContentPane().setBackground(Color.PINK);
                speedSlider.setValue(0);
                timer.cancel();

            }
        });
        spinWheel.getContentPane().add(stop);

        spinWheel.getContentPane().add(start);


        /**
         *Add a start frame to show before the main spinning wheel frame
         * START FRAME
         */
        JFrame startFrame = new JFrame("Welcome");
        startFrame.setLayout(new FlowLayout());
        startFrame.setSize(1000, 1000);
        startFrame.add(new JLabel(new ImageIcon("src/Joker.jpg")));
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
        setupImageRotator();
    }


    static BufferedImage LoadImage(String FileName){
        BufferedImage imageToLoad = null;
        try {
            imageToLoad = ImageIO.read(new File(FileName));
        }catch(IOException e){
            System.out.println("Cannot load image");
        }
        return imageToLoad;
    }


    public static void Paint(Graphics g, BufferedImage image, double x) throws IOException {

        AffineTransform at = AffineTransform.getTranslateInstance(100,100);
        at.rotate(Math.toRadians(x), image.getWidth()/2, image.getHeight()/2);
        ///create graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, at, null);
    }
    static void PlaySound(File sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

//            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch(Exception e){

        }
    }


}
/**
 *image reference:
 * https://wheel.fhtl.byu.edu/#/
 * https://www.pinterest.com/pin/35888128251108254/
 * credit given to:
 *  https://stackoverflow.com/questions/8639567/java-rotating-images
 * ---Fall 2019---
 * University of Iowa
 */

