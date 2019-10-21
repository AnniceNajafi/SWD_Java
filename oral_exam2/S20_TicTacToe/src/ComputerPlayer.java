public class ComputerPlayer extends Player {
    boolean smart;
    Character signature;
    public ComputerPlayer(int numPlayer, boolean smart){
        super(numPlayer);
        this.smart=smart;
    }

    @Override
    public char makeMove(){
        if(smart){

        }
        else{

        }
        return 0;
    }
}
