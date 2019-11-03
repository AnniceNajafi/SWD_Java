public class HangmanDriver {
    public static void main(String[] args){
        Hangman game = new Hangman();
        game.receiveWord();
        game.addTable();
        game.addGuessTextField();
    }

}
