import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
