
import java.util.Scanner;

public class HumanPlayer implements Player {
    /**
     * instance variable Player Char contains the player's special character
     */
    private Character PlayerChar;

    /**
     * Constructor, creates a player with a certain character 'X' or 'O'
     * @param numPlayer : player number indicates which character is associated with player if 1--> X else O
     */
    public HumanPlayer(int numPlayer){
    if(numPlayer==1){
        PlayerChar = 'X';
    }else{PlayerChar ='O';}
    }

    /**
     * MakeMove method fills a position of the board
     * @param boardToPlay is passed to the function so that player fills an empty available position
     */
    @Override
    public void makeMove(Board boardToPlay){
    System.out.println("Please enter position to fill");
    Scanner Sc = new Scanner(System.in);
    char position = Sc.next().charAt(0);
        boardToPlay.fillPosition(position, PlayerChar);

    }
    /**
     * accessor method for instance variable PlayerChar
     * @return Character, the Player's Character 'X' or 'O'
     */
    public Character getPlayerChar() {
        return PlayerChar;
    }
}
