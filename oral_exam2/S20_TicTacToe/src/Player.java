public class Player {
    protected char PlayerChar;
    public Player(int numPlayer){
        if(numPlayer==1){
            PlayerChar='X';
        }
        else{
            PlayerChar='O';
        }
    }
    public void makeMove(Board board) throws InterruptedException {

    }

}
