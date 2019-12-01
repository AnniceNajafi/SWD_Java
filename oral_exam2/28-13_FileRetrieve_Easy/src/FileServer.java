import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Annice Najafi
 * Description: Use a socket connection to allow a client to specify a filename of a text file and have the server
 * send the contents of the file or indicate that the file does not exist.
 * Please first run the server then the client
 * Level of difficulty: easy
 */

public class FileServer{
    /**
     * instance variables
     * server : type -> ServerSocket, create a socket between the server and client
     * connection: type -> Socket, for the requests to be received here
     */
    private ServerSocket server;
    private Socket connection;

    /**
     * Constructor --> establishes connection with client, if could not prints out an error message
     *
     */
    public FileServer()  {
        try {
            ///using port number between 23500 to 23999 as indicated in lecture slides
            server = new ServerSocket(23600);
            connection = server.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Error, Problem occurred connecting client to server.");
        }

    }

    /**
     * Receives request from client, looks for the file on the computer read the file into a String and
     * sends the String to the client.
     */
    public void runServer() {
        ///scanner to read file path from the client that has been sent to the server
        Scanner rec;
        try {
            rec = new Scanner(connection.getInputStream());
            String filepath = rec.next();
            System.out.println(filepath);
            ///print the file path to check if server has received it
            try {
                ///Find the file
                FileInputStream fileX = new FileInputStream(filepath);
                ///Read the file into a String
                BufferedReader servReader = new BufferedReader(new FileReader(filepath));
                ///Use printStream to send the String to client
                PrintStream pr = new PrintStream(connection.getOutputStream());
                String st;
                while ((st = servReader.readLine()) != null) {
                    pr.println(st);
                }
                ///print out a message when you sent the String
                System.out.println("Requested File sent to client");
            } catch (FileNotFoundException e) {
                ///If you could not find the requested send an error message
                PrintStream pr = new PrintStream(connection.getOutputStream());
                String st = "Error! server could not find the requested file";
                pr.println(st);

            }

        } ///catch the times when you don't receive a request from client
        catch (IOException e) {
            System.out.println("Error, server did not receive filename from client.");
        }

    }
    public static void main(String[] args) {

        FileServer AnniceServer = new FileServer ();
        AnniceServer.runServer();

    }

}
