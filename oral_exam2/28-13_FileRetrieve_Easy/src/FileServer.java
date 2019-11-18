import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class FileServer{
    private ServerSocket server;
    private Socket connection;
    public FileServer() throws IOException {
        server = new ServerSocket(4333);
        connection = server.accept();
        System.out.println("Client connected");
    }

    public void runServer() throws IOException {
        ///Read files
        FileInputStream file1 = new FileInputStream("oral_exam2/28-13_FileRetrieve_Easy/networking_java/sample1.txt");
        FileInputStream file2 = new FileInputStream("oral_exam2/28-13_FileRetrieve_Easy/networking_java/seq_1.txt");
        byte c[]= new byte[2002];
        file2.read(c,0, c.length);
        Scanner rec = new Scanner(connection.getInputStream());
        String filepath = rec.next();
        System.out.println(filepath);
        try {
            FileInputStream fileX = new FileInputStream(filepath);
            byte b[]= new byte[2002];
            fileX.read(b, 0, b.length);
            OutputStream send = connection.getOutputStream();
            send.write(b,0,b.length);
            System.out.println("Requested File sent to client");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




    }
    public static void main(String[] args) throws IOException {

        FileServer AnniceServer = new FileServer ();
        AnniceServer.runServer();

    }

}
