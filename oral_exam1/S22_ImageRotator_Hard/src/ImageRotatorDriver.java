import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ImageRotatorDriver {
    public static void main(String [] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        ImageRotator spinner = new ImageRotator();
        spinner.setupImageRotator();
    }
}
