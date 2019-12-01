import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The fileClient class will implement GUI to ask for a file path and then connects to the server
 * by sockets and receives the file from the server and outputs to the client frame
 */

public class FileClient extends JFrame{
    /**
     * instance variables
     * client, Socket , for the client to connect to server
     * enter_file_name, JTextField to enter file name in graphical user interface
     * contents, JTextField to show requested file or an error message
     * fileText, String is the name of the requested file
     */
    private Socket client;
    private JTextField enter_file_name;
    private JTextField contents;
    private String fileText;

    /**
     * Constructor, Makes a frame with two textfields
     * for user to request a file and receive the file contents found by server
     */
    public FileClient() {
        ///make a frame for the client
        super("RetrieveFile program");
        ///add a textfield on the frame for the client to enter a file name
        enter_file_name = new JTextField("Please enter file name here");
        ///add an action listener to the textfield to receive the file path and send it to the server
        enter_file_name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                ///store the text entered in text field
                fileText = enter_file_name.getText();
                ///use printstream to transfer text to server
                PrintStream pr = null;
                try {
                    ///transfer the text using printstream to server
                    pr = new PrintStream(client.getOutputStream());
                    pr.println(fileText);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        ///close frame if the closed button is pressed
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ///set the size of the frame
        this.setSize(400,500);
        ///add textfield with action listener to frame
        this.add(enter_file_name);
        ///add a JEditorPane to the frame to show the contents of the file
        contents = new JTextField();
        contents.setEditable(false);
        this.add(contents);
        this.setLayout(new GridLayout());
        this.setVisible(true);
    }

    /**
     * This function should be called after the file path has been sent to the server to receive the contents of
     * the file requested that has been sent to the client by server
     */
    public void downloadFile(){
        ///This function should be called after the file path has been sent to the server
        Scanner sc = null;
        try {
            sc = new Scanner(client.getInputStream());
        } catch (IOException e) {
            System.out.println("Error! No file requested");
        }
        String text = "";
        while(sc.hasNextLine()){
            text+= sc.nextLine();
        }
        System.out.println(text);
        contents.setText(text);
    }

    /**
     *this function makes the client establish connection with the server otherwise print a message saying that
     * establishing connection failed
     */
    public void runClient() {
        try {
            client = new Socket("localhost", 23600);
        } catch (IOException e) {
            System.out.println("Failed to establish connection with server");
        }
    }
    public static void main(String[] args){
        FileClient Annice = new FileClient();
        Annice.runClient();
        Annice.downloadFile();
    }
}

