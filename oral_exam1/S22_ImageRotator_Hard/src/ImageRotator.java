/**
 * @Aurthor: Annice Najafi
 * @Date: 9/29/2019
 * LevelOfDifficulty: HARD
 * description: This program
 */
///Add GUI
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class ImageRotator {
    static boolean stopspin=true;
    public static void main(String[] args) throws IOException {

        ///Create a frame that contains the spinning wheel
        JFrame spinWheel = new JFrame("Spinning Wheel");
        BufferedImage wheel = LoadImage("src/wheel2.png");
        spinWheel.setSize(1000,1000);
        spinWheel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spinWheel.setLayout(new FlowLayout());
//        ImageIcon pointer = new ImageIcon("src/BT.png");
//        JLabel point = new JLabel(pointer);
//        point.setLayout(null);
//        point.setBounds(500,100,700,200);
//        spinWheel.add(point);
        spinWheel.getContentPane().setBackground(Color.pink);
        ///At this point we need to add the spinning wheel and define a function that repaints the wheel in the frame
        // each time it rotates.
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
        spinWheel.getContentPane().add(receive);
        JButton rotate = new JButton("rotate");
        JButton spin = new JButton("spin");
        spin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                double randdegrees = Math.random()*360+1;
                stopspin=false;
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

        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopspin=true;
            }
        });
        JTextField speed = new JTextField("Enter speed here.");
        JButton start = new JButton("Start");

        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int i=0;
                stopspin=false;
                while(!stopspin){
                    try {
                        Paint(spinWheel.getGraphics(), wheel, i++);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        spinWheel.getContentPane().add(start);
//        speed.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                int i=0;
//                while(!stopspin){
//                    try {
//                        Paint(spinWheel.getGraphics(), wheel, i++);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
        spinWheel.getContentPane().add(speed);
        spinWheel.getContentPane().add(stop);
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

