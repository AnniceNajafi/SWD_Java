import java.util.HashMap;
import java.util.Map;

public class Board {
    private char [][] board;
    ///Map each position to a boolean that shows whether the position is empty or not.
    Map<Character, Boolean> checkEmpty =new HashMap<>();
    public Map<Character, Integer> storePosition = new HashMap<>();
    Board(){
        board = new char[3][3];
    }
    public void initializeBoard(){
        char num='1';
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j]=num;
                num++;
                checkEmpty.put(board[i][j], true);
                storePosition.put(board[i][j], Character.getNumericValue(num));
            }
        }
    }
    public boolean PositionsEmpty(){
        int sumPositions=0;
        for(Boolean bool : checkEmpty.values()){
            if(bool=true){
                sumPositions++;
            }
        }
        return sumPositions != 0;
    }
    public boolean checkPosition(int position){
        int check=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j]==position){
                    check++;
                }
            }
        }
        if(check==0){
            return false;
        }
        else{
            return true;
        }
    }
    public void fillPosition(char position, char specialCharacter){
           for(int i=0; i<3; i++){
               for(int j=0; j<3; j++){
                   if(board[i][j]==position){
                       board[i][j]=specialCharacter;
                   }
               }
           }
    }

    public void printBoard(){
        for(int i=0; i<3; i++){
            System.out.print("|");
            for(int j=0; j<3; j++){
                System.out.print(board[i][j]+"|");
            }
            System.out.println(" ");
        }
    }
    public boolean checkColumns(){

        for(int i=0; i<3; i++){
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i]&&(board[0][i]=='X'||board[0][i]=='O')){
              return true;
            }
        }
        return false;
    }
    public boolean checkRows(){
        for(int i=0; i<3; i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2]&&(board[i][0]=='X'||board[i][0]=='O')){
                return true;
            }
        }
        return false;
    }
    public boolean checkDiagonal(){
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&(board[0][0]=='X'||board[0][0]=='O')){
            return true;
        }
        else if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&(board[0][0]=='X'||board[0][0]=='O')){
            return true;
        }
        else{
            return false;
        }
    }


}
