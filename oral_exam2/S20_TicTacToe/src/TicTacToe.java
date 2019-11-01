import java.util.Scanner;

/**
 * @author Annice Najafi
 * Date: 18 Oct 2019
 * Level Of Difficulty: Hard
 */
public class TicTacToe {
    private Board game;
    private Player first;
    private Player second;
    public TicTacToe(){
        game = new Board();
        game.printBoard();
    }
    public void setUpPlayers(){
        System.out.println("Please pick two players, HH for two human players, HC for one human player and one computer player\n" +
                "and CC for two computer players.");
        Scanner promptAnswer = new Scanner(System.in);
        String response=promptAnswer.next();
        if(response.equals("HC")){
            first = new HumanPlayer(1);
            System.out.println("Please specify if you want the computer player dumb or smart");
            Scanner dumbOrSmart = new Scanner(System.in);
            String responseToDumb=dumbOrSmart.next();
            if(responseToDumb=="smart"){
                second = new ComputerPlayer(2, true);
            }
            else{
                second = new ComputerPlayer(2, false);
            }
        }
        else if(response.equals("HH")){
            first = new HumanPlayer(1);
            second = new HumanPlayer(2);

        }else if(response.equals("CC")){
            System.out.println("Please specify if you want the computer player1 dumb or smart");
            Scanner dumbOrSmart1 = new Scanner(System.in);
            String responseToDumb1=dumbOrSmart1.next();
            if(responseToDumb1=="smart"){
                first = new ComputerPlayer(1, true);
            }
            else{
                first = new ComputerPlayer(1, false);
            }

            System.out.println("Please specify if you want the computer player2 dumb or smart");
            Scanner dumbOrSmart2 = new Scanner(System.in);
            String responseToDumb2=dumbOrSmart2.next();
            if(responseToDumb2=="smart"){
                second = new ComputerPlayer(2, true);
            }
            else{
                second = new ComputerPlayer(2, false);
            }
        }
        else{
            System.out.println("wrong request");
        }

    }
    public void playGame() throws InterruptedException {
        while(!(game.checkRows() == game.checkColumns() == game.checkDiagonal()) && game.PositionsEmpty()) {
            first.makeMove(game);
            game.printBoard();
            second.makeMove(game);
            game.printBoard();

        }
        checkWinner();

    }
    public void checkWinner(){
        if(game.PositionsEmpty()){
            System.out.println("Game is a draw");
        }
        else{

        }
    }





    public static void main(String[] args) throws InterruptedException {
        TicTacToe myGame = new TicTacToe();
        myGame.setUpPlayers();
        myGame.playGame();

}
}
