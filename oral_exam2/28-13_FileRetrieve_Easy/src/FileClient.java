import sun.security.util.IOUtils;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * The fileClient class will implement GUI to ask for a file path and then connects to the server
 * by sockets and receives the file from the server and outputs to the client frame
 */

public class FileClient extends JFrame{
    Socket client;
    JTextField enter_file_name;
    JEditorPane contents;
    String fileText;
    public FileClient() throws IOException {
        super("RetrieveFile program");
        enter_file_name = new JTextField("Please enter file name here");
        enter_file_name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                fileText = enter_file_name.getText();
                PrintStream pr = null;
                try {
                    pr = new PrintStream(client.getOutputStream());
                    pr.println(fileText);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.add(enter_file_name);
        contents = new JEditorPane();
        contents.setEditable(false);
        this.add(contents);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }
    public void downloadFile() throws IOException {
        byte[] b = new byte[20002];
        InputStream fileX = client.getInputStream();
        String store = String.valueOf(fileX.read(b, 0, b.length));
        String result = IOUtils.toString(fileX, StandardCharsets.UTF_8);
        System.out.println(store);
    }
    public void runClient() throws IOException {
        client = new Socket("localhost", 4333);
//        System.out.println("Please Enter name of the file");
//        Scanner sc = new Scanner(System.in);
//        String filePath = sc.next();
//        PrintStream pr = new PrintStream(client.getOutputStream());
//        pr.println(filePath);
//        System.out.println(filePath);
    }
    public static void main(String[] args) throws IOException {
        FileClient Annice = new FileClient();
        Annice.runClient();
        Annice.downloadFile();
    }
}

