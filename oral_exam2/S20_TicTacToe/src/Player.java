public abstract class Player {
    
    /**
     * fills a position on the board given to the function
     * @param board, Board received as input
     * no output, changes the board given to it
     */
    public abstract void makeMove(Board board);

    public abstract Character getPlayerChar();
}
