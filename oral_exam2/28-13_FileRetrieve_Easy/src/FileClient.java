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
     */
    private Socket client;
    private JTextField enter_file_name;
    private JTextField contents;
    private String fileText;

    /**
     *
     *
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
     *
     * @throws IOException
     */
    public void downloadFile() throws IOException {
        ///This function should be called after the file path has been sent to the server
        Scanner sc = new Scanner(client.getInputStream());
        String text = "";
        while(sc.hasNextLine()){
            text+= sc.nextLine();
        }
        System.out.println(text);
        contents.setText(text);
    }
    public void runClient() throws IOException {
        client = new Socket("localhost", 4333);
    }
    public static void main(String[] args) throws IOException {
        FileClient Annice = new FileClient();
        Annice.runClient();
        Annice.downloadFile();
    }
}

