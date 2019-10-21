import java.util.Map;
import java.util.Scanner;

public class HumanPlayer extends Player {
public HumanPlayer(int numPlayer){
    super(numPlayer);
}


    @Override
    public char makeMove(){
    System.out.println("Please enter position to fill");
    Scanner Sc = new Scanner(System.in);
    return Sc.next().charAt(0);

    }
}
