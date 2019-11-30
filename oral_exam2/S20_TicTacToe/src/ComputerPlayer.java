import java.util.ArrayList;

public class ComputerPlayer implements Player {
    /**
     * instance variable Player Char contains the player's special character
     */
    private Character PlayerChar;

    /**
     * Constructor, creates a player with a certain character 'X' or 'O'
     * @param numPlayer : player number indicates which character is associated with player if 1--> X else O
     */
    public ComputerPlayer(int numPlayer){
        if(numPlayer==1){
            PlayerChar = 'X';
        }else{PlayerChar ='O';}
    }

    /**
     * MakeMove method fills a position of the board
     * @param boardToPlay is passed to the function so that player fills an empty available position
     */
    @Override
    public void makeMove(Board boardToPlay)  {
        System.out.println("Player is thinking...");

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            System.out.println("Error! Thread interrupted");
        }
        ///First check if by filling any positions computer player can win
        int done=0;
        ///Create an arraylist that stores all available positions and checks every single position
        ArrayList<Character> simAvPositions = new ArrayList<>();
        simAvPositions = boardToPlay.availablePositions();
        ///While there is still a position to check
        int k=0;
        while(k<simAvPositions.size() && done==0){
            ///Create a new board that simulates the actual board
            Board simBoard = new Board();
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    simBoard.board[i][j]=boardToPlay.board[i][j];
                }
            }
            ///check position
            char toFill = simAvPositions.get(k);
            ///check if filling that position makes the player win
            simBoard.fillPosition(toFill, PlayerChar);
            ///if so, fill the actual board and break the while loop
            if(simBoard.checkWinner()==PlayerChar){
                    boardToPlay.fillPosition(toFill, PlayerChar);
                    done = 1;
            }
            k++;
        }///If cannot win by one move randomly fill a position
        if(done==0){
            ArrayList<Character> avPositions = new ArrayList<>();
            avPositions = boardToPlay.availablePositions();
            int x = (int) (Math.random() * (avPositions.size()));
            char toFill = avPositions.get(x);
            ///
            boardToPlay.fillPosition(toFill, PlayerChar);
        }
    }

    /**
     * accessor method for instance variable PlayerChar
     * @return Character, the Player's Character 'X' or 'O'
     */
    public Character getPlayerChar() {
        return PlayerChar;
    }
}
