import javax.swing.*;
import java.awt.*;


public class BouncingBall extends JPanel implements Runnable{
    /**
     * Instance variables x, y, dx, dy
     * Type: int
     * x and y store the position of the ball, dx and dy are the change in position in each axis
     */
    private int x, y, dx, dy;
    /**
     * panel_Width and panel_Height store store the size of the panel
     */
    private static final int panel_WIDTH = 640;
    private static final int panel_HEIGHT = 480;

    /**
     * CONSTRUCTOR
     * sets the initial position of the ball and the change in position
     */
    public BouncingBall(){
        x=0;
        y=0;
        dx=1;
        dy=1;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        this.setPreferredSize(new Dimension(panel_WIDTH, panel_HEIGHT));
        g.setColor(Color.BLUE);
        g.fillOval(x,y,20,20);
    }

    /**
     *
     */
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
    }

    /**
     *
     */
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
