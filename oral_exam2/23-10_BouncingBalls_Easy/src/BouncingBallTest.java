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
        JFrame place = new JFrame("Bouncing Ball");
        place.setSize(500,500);
        place.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BouncingBall ball = new BouncingBall();
        place.add(ball);
        place.setVisible(true);
        place.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                executorService.execute(ball);
            }
        });
    }
}
