import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
    public char [][] board;
    ///Map each position to a boolean that shows whether the position is empty or not.
    Map<Character, Boolean> checkEmpty =new HashMap<>();
    public Map<Character, Integer> storePosition = new HashMap<>();
    ///Board Score to find a win, lose or tie
    int BoardScore;

    Board(){
        board = new char[3][3];
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
    public boolean positionisEmpty(char Position){
        int find=0;
        for(Map.Entry<Character, Boolean> i: checkEmpty.entrySet()){
            if(i.getKey()==Position && i.getValue()){
                find++;
            }
        }
        if(find==0){
            return false;
        }
        else{
            return true;
        }
    }
    public ArrayList <Character> availablePositions(){
        ArrayList<Character> emptyPositions = new ArrayList<Character>();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j]!='X' && board[i][j]!='O'){
                    emptyPositions.add(board[i][j]);
                }
            }
        }
        return emptyPositions;
    }
    public void fillPosition(char Position, char c){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j]==Position){
                    board[i][j]=c;
                    checkEmpty.put(board[i][j], false);
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
    ///Functions to check columns, rows and diagonals for winners
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
    public Character checkWinner(){
        for(int i=0; i<3; i++){
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i]&&(board[0][i]=='X'||board[0][i]=='O')){
                return board[0][i];
            }
        }
        for(int i=0; i<3; i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2]&&(board[i][0]=='X'||board[i][0]=='O')){
                return board[i][0];
            }
        }
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&(board[0][0]=='X'||board[0][0]=='O')){
            return board[0][0];
        }
        else if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&(board[0][0]=='X'||board[0][0]=='O')){
            return board[0][2];
        }
        return null;
    }




}
