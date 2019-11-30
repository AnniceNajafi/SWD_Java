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
     * panel_Width and panel_Height store the size of the panel
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
     * the paint function Overriding the defined function for the super class, JPanel
     * paints a circle on the JPanel
     * @param g is Graphics
     * void function does not return anything but paints on the JPanel
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        ///set the size of the JPanel
        this.setPreferredSize(new Dimension(panel_WIDTH, panel_HEIGHT));
        ///Set the color of the circle
        g.setColor(Color.BLUE);
        ///Draw a circle with radius 10
        g.fillOval(x,y,20,20);
    }

    /**
     * moves the location of the ball, in case it hits the edges it makes it bounce back
     * does not receive anything uses class attributes
     * does not return anything, repaints the ball on the JPanel
     */
    public void move() {
        ///In case the ball hits the left corner of the JPanel, bounce back
        if(x+dx<0){
            dx=1;
        }
        ///In case the ball hits the right corner of the JPanel, bounce back
        else if(x+dx>getWidth() - 20){
            dx=-1;
        }
        ///In case the ball hits the top of the page
        else if(y+dy<0){
            dy=1;
        }
        ///In case the ball hits the bottom of the page
        else if(y+dy>getHeight() - 20){
            dy=-1;
        }
        ///otherwise just move the ball
          x=x+dx;
          y=y+dy;
    }

    /**
     * run, the overriden method from Runnable which is defined to be executed in the BouncingBallTest class
     */
    @Override
    public void run(){
        ///repeat moving the ball over and over
        while(true){
            ///move then repaint on JPanel
            move();
            repaint();
            ///make the thread sleep between each repaint, we can basically set the speed of the
            ///ball by making the thread sleep between each move
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Error!, thread is interrupted!");
            }
        }
    }
}
