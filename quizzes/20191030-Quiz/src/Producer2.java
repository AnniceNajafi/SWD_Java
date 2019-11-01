import java.security.SecureRandom;

public class Producer2 implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation; // reference to shared object

    // constructor
    public Producer2(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    // store values from 1 to 10 in sharedLocation
    public void run() {
        int sum = 0;

        for (int count = 11; count <= 15; count++) {
            try // sleep 0 to 3 seconds, then place value in Buffer
            {
                Thread.sleep(generator.nextInt(3000)); // random sleep
                sharedLocation.blockingPut(count); // set value in buffer
                sum += count; // increment sum of values
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.printf(
                "Producer done producing%nTerminating Producer%n");
    }
} // end class Producer
