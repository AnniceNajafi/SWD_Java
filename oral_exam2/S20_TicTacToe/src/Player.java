public class Player {
    protected boolean status;
    protected char PlayerChar;
    static protected int numPlayer;
    public Player(int numPlayer){
        if(numPlayer==1){
            PlayerChar='X';
        }
        else{
            PlayerChar='O';
        }
    }
    public void setStatus(){

    }
    public char makeMove(){
        return 0;
    }

}
