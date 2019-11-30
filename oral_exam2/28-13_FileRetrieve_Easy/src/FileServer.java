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
     * server : type -> ServerSocket
     * connection: type -> Socket
     */
    private ServerSocket server;
    private Socket connection;

    /**
     *
     *
     */
    public FileServer()  {
        try {
            server = new ServerSocket(4333);
            connection = server.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error, Problem occurred connecting client to server.");
        }

    }

    /**
     *
     *
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
                BufferedReader servReader = new BufferedReader(new FileReader(filepath));
                PrintStream pr = new PrintStream(connection.getOutputStream());
                String st;
                while ((st = servReader.readLine()) != null) {
                    pr.println(st);
                }
                System.out.println("Requested File sent to client");
            } catch (FileNotFoundException e) {
                System.out.println("Error, File not found");
            }
        } catch (IOException e) {
            System.out.println("Error, server did not receive filename from client.");
        }





    }
    public static void main(String[] args) {

        FileServer AnniceServer = new FileServer ();
        AnniceServer.runServer();

    }

}
