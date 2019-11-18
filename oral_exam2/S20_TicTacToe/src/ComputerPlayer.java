import java.util.ArrayList;

public class ComputerPlayer implements Player {
    boolean smart;

    Character PlayerChar;
    public ComputerPlayer(int numPlayer, boolean smart){
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
            if(boardToPlay.positionisEmpty('5')){
                boardToPlay.fillPosition('5', PlayerChar);
            }
            else if(boardToPlay.positionisEmpty('1')){
                boardToPlay.fillPosition('1', PlayerChar);
            }
            else if(boardToPlay.positionisEmpty('3')){
                boardToPlay.fillPosition('3', PlayerChar);
            }
            else if(boardToPlay.positionisEmpty('7')){
                boardToPlay.fillPosition('7', PlayerChar);
            }
            else if(boardToPlay.positionisEmpty('9')){
                boardToPlay.fillPosition('9', PlayerChar);
            }
            else{
                ArrayList<Character> avPositions = new ArrayList<>();
                avPositions = boardToPlay.availablePositions();
                char toFill = avPositions.get(0);
                boardToPlay.fillPosition(toFill, PlayerChar);
                avPositions.remove(0);
            }
        }
    }
}
