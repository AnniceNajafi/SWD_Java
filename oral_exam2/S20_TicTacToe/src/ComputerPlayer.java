import java.util.ArrayList;

public class ComputerPlayer implements Player {
    boolean smart;

    Character PlayerChar;
    public ComputerPlayer(int numPlayer, boolean smart){
        if(numPlayer==1){
            PlayerChar = 'X';
        }else{PlayerChar ='O';}
        this.smart=smart;
    }

    @Override
    public void makeMove(Board boardToPlay) throws InterruptedException {
        System.out.println("Player is thinking...");
        Thread.sleep(5000);
        if(!smart){
            ArrayList<Character> avPositions = new ArrayList<>();
            avPositions = boardToPlay.availablePositions();
            char toFill = avPositions.get(0);
           ///
            boardToPlay.fillPosition(toFill, PlayerChar);
            avPositions.remove(0);
        }
        else{
            ArrayList<Character> avPositions = new ArrayList<>();
            Board sim = boardToPlay;
            avPositions = sim.availablePositions();
            while(avPositions.size()!=0){

            }
        }
    }


    public void minimax(Board boardToPlay) {
        int result;
        ArrayList<Character> avPositions = new ArrayList<>();
        avPositions = boardToPlay.availablePositions();
        if(boardToPlay.checkWinner()==PlayerChar){
            result =10;
        }else if(boardToPlay.checkWinner()==null){
            result=0;
        }else{
            result= -10;
        }
    }
}
