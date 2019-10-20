import java.util.Scanner;

/**
 * @author Annice Najafi
 * Date: 18 Oct 2019
 * Level Of Difficulty: Hard
 */
public class TicTacToe {
    private Board game;
    private Player player1;
    private Player player2;
    public TicTacToe(){
        game = new Board();
        game.initializeBoard();
        game.printBoard();
    }
    public void setUpPlayers(){
        System.out.println("Please pick two players, HH for two human players, HC for one human player and one computer player\n" +
                "and CC for two computer players.");
        Scanner promptAnswer = new Scanner(System.in);
        String response=promptAnswer.next();
        if(response.equals("HC")){
            player1 = new HumanPlayer(1);
            System.out.println("Please specify if you want the computer player dumb or smart");
            Scanner dumbOrSmart = new Scanner(System.in);
            String responseToDumb=dumbOrSmart.next();
            if(responseToDumb=="smart"){
                player2 = new ComputerPlayer(2, true);
            }
        }
        else if(response.equals("HH")){
            player1 = new HumanPlayer(1);
            player2 = new HumanPlayer(2);

        }else if(response.equals("CC")){
            System.out.println("Please specify if you want the computer player1 dumb or smart");
            Scanner dumbOrSmart1 = new Scanner(System.in);
            String responseToDumb1=dumbOrSmart1.next();
            if(responseToDumb1=="smart"){
                player1 = new ComputerPlayer(2, true);
            }
            else{
                player1 = new ComputerPlayer(2, false);
            }

            System.out.println("Please specify if you want the computer player2 dumb or smart");
            Scanner dumbOrSmart2 = new Scanner(System.in);
            String responseToDumb2=dumbOrSmart2.next();
            if(responseToDumb2=="smart"){
                player2 = new ComputerPlayer(2, true);
            }
            else{
                player2 = new ComputerPlayer(2, false);
            }
        }
        else{
            System.out.println("wrong request");
        }

    }

    public void startGame(){
        while(!(game.checkRows() == game.checkColumns() == game.checkDiagonal()) && game.PositionsEmpty()){
            player1.makeMove();
            player2.makeMove();
        }
    }


public static void main(String[] args){
        TicTacToe myGame = new TicTacToe();
        myGame.setUpPlayers();
}
}
