import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BouncingBall extends JPanel implements Runnable{
    int x, y, dx, dy;
    private static final int panel_WIDTH = 640;
    private static final int panel_HEIGHT = 480;
    public BouncingBall(){
        x=0;
        y=0;
        dx=1;
        dy=1;
    }
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        this.setPreferredSize(new Dimension(panel_WIDTH, panel_HEIGHT));
        g.setColor(Color.BLUE);
        g.fillOval(x,y,20,20);
    }
    public void move() {
        if(x+dx<0){
            dx=1;
        }else if(x+dx>getWidth() - 20){
            dx=-1;
        }else if(y+dy<0){
            dy=1;
        }else if(y+dy>getHeight() - 20){
            dy=-1;
        }
          x=x+dx;
          y=y+dy;
          repaint();
    }
    @Override
    public void run(){
        while(true){
            move();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
