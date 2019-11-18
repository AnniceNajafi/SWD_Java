import java.util.Map;
import java.util.Scanner;

public class HumanPlayer implements Player {
    Character PlayerChar;

//public HumanPlayer(int numPlayer){
//
//}


    @Override
    public void makeMove(Board boardToPlay){
    System.out.println("Please enter position to fill");
    Scanner Sc = new Scanner(System.in);
    char position = Sc.next().charAt(0);
        boardToPlay.fillPosition(position, PlayerChar);

    }
}
