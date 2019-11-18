import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BouncingBallPlace extends JFrame implements Runnable, MouseListener {
    JFrame frame;
    JPanel place;
    ImageIcon BlueBall;
    public BouncingBallPlace() throws IOException {
        frame = new JFrame("Bouncing Balls Game");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        place = new JPanel();
        frame.add(place);
        frame.setVisible(true);


    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {



    }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void run() {

    }
    public class Ball extends Thread{
        private int PositionX;
        private int PositionY;
        public Ball(int x, int y){
            PositionX=x;
            PositionY=y;
        }
    }
}
