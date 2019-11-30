import java.util.Scanner;

/**
 * @author Annice Najafi
 * Date: 18 Oct 2019
 * Level Of Difficulty: Hard
 */
public class TicTacToe {
    /**
     * instance variable, game is an object of the Board class
     */
    private Board game;
    /**
     * instance variable, first is the first Player
     */
    private Player first;
    /**
     * instance variable, second is the second Player
     */
    private Player second;

    /**
     * constructor, instantiates a new Board and prints it to the screen
     */
    public TicTacToe(){
        game = new Board();
        game.printBoard();
    }

    /**
     * setUpPlayers method initializes the two players by asking the user
     * HH for two human players, HC for one human player and one computer player and CC for two computer players
     * function prints out "wrong request" if
     */
    public void setUpPlayers(){
        System.out.println("Please pick two players, HH for two human players, HC for one human player and one computer player\n" +
                "and CC for two computer players.");
        Scanner promptAnswer = new Scanner(System.in);

        String response=promptAnswer.next();
        if(response.equals("HC")){
            first = new HumanPlayer(1);
            second = new ComputerPlayer(2);

        }
        else if(response.equals("HH")){
            first = new HumanPlayer(1);
            second = new HumanPlayer(2);

        }else if(response.equals("CC")){
                first = new ComputerPlayer(1);
                second = new ComputerPlayer(2);
        }
        else{
            System.out.println("wrong request");
        }

    }

    /**
     * Function lets first player move and second player after that as long as game doesn't have a winner or
     * is not a tie
     * game catches interrupted thread exception
     * no input, uses instance variables
     * no output, changes board instance variable
     */
    public void playGame()  {
        do {
            if (game.checkRows() || game.checkColumns() || game.checkDiagonal() || !game.PositionsEmpty()) {
                break;
            }
            first.makeMove(game);
            game.printBoard();
            if (game.checkRows() || game.checkColumns() || game.checkDiagonal() || !game.PositionsEmpty()) {
                break;
            }
            second.makeMove(game);
            game.printBoard();


        }while(game.checkWinner()==null);
        if(game.checkWinner()==first.getPlayerChar()){
            System.out.println("First player won!");
        }else if(game.checkWinner()==second.getPlayerChar()){
            System.out.println("Second player won!");
        }else{
            System.out.println("Game is a tie!");
        }

    }






    public static void main(String[] args){
        TicTacToe myGame = new TicTacToe();
        myGame.setUpPlayers();
        try {
            myGame.playGame();
        }catch(Exception e){
            System.out.println("Error, cannot continue game");
        }
}
}
