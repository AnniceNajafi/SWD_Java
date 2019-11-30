import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Annice Najafi
 * Date: 29 Nov 2019
 * Description: Write a program that bounces a blue ball inside a JPanel. The ball should begin moving with
 * a mousePressed event. When the ball hits the edge of the JPanel, it should bounce off the edge and continue in
 * the opposite direction. The ball should be updated using a runnable.
 */
public class BouncingBallTest {
    public static void main(String[] args) {
        ///Define a frame for the JPanel to be placed in.
        JFrame place = new JFrame("Bouncing Ball");
        ///set the size of the frame
        place.setSize(500,500);
        ///make the frame close if the closed button is pressed
        place.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ///instantiate a bouncing ball object
        BouncingBall ball = new BouncingBall();
        ///place the bouncing ball JPanel on the JFrame
        place.add(ball);
        ///set the frame visible
        place.setVisible(true);
        //add a mouse listener to the frame to make the ball move when the screen is pressed
        place.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ///run the thread when pressed
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.execute(ball);
            }
        });
    }
}
