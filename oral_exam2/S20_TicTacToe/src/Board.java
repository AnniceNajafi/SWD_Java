import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Board {
    /**
     * instance variable board is a 3x3 matrix that has the positions of the board
     */
    public char [][] board;
    /**
     * checkEmpty Maps each position to a boolean that shows whether the position is empty or not.
     */
    Map<Character, Boolean> checkEmpty =new HashMap<>();
    /**
     * checkEmpty Maps each position to an Integer from 1 to 9 specific to that position.
     */
    private Map<Character, Integer> storePosition = new HashMap<>();
    /**
     * Constructor, initializes the board matrix and checkEmpty and storePosition maps
     */
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

    /**
     * PositionsEmpty goes through all the positions and checks if there are empty positions left in the board
     * @return boolean - true if positions available false otherwise
     */
    public boolean PositionsEmpty(){
        int sumPositions=0;
        for(Boolean bool : checkEmpty.values()){
            if(bool=true){
                sumPositions++;
            }
        }
        return sumPositions != 0;
    }

    /**
     * Goes through positions on the board and stores empty positions in an arrayList
     * @return an arrayList of empty positions in the board
     */
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

    /**
     * fillPosition receives the Player character and Position then fills that position on the board with Player character
     * @param Position  , Char is the specific Position on the board to be filled
     * @param c , char is the Player's character
     */
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

    /**
     * printBoard method Prints the board to the screen based on instance variable
     * no input, uses instance variables
     * no output, void , result is printed to screen
     */
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

    /**
     * checkColumns, checks all columns of the board for winners
     * no input, uses instance variables
     * @return boolean, true if the function finds a winner false otherwise
     */
    public boolean checkColumns(){

        for(int i=0; i<3; i++){
            if(board[0][i]==board[1][i] && board[1][i]==board[2][i]&&(board[0][i]=='X'||board[0][i]=='O')){
              return true;
            }
        }
        return false;
    }

    /**
     * checkRows, checks all Rows of the board for winners
     * no input, uses instance variables
     * @return boolean, true if the function finds a winner false otherwise
     */
    public boolean checkRows(){
        for(int i=0; i<3; i++){
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2]&&(board[i][0]=='X'||board[i][0]=='O')){
                return true;
            }
        }
        return false;
    }

    /**
     * checkDiagonal, checks all diagonals of the board for winners
     * no input, uses instance variables
     * @return boolean, true if the function finds a winner false otherwise
     */
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

    /**
     * function checks for a winner and stores and outputs the character of the winner,null if no winner found
     * no input, uses instance variables
     * @return character of the winner or null if winner not found
     */
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
