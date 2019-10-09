package RequestInheritance;

import java.util.Random;
import java.util.Stack;

public class Server {
    private static final Integer NUM_REQ_TYPES = 9;

    public static void main(String argv[]) {
        Stack<Request> requestStack = new Stack<>();
        Random random = new Random();
        DataGenerator data = new DataGenerator();

        // Request instantiation should be more specific for each type, but you get the idea

        //load stack with random requests
        for (int i = 0; i < NUM_REQ_TYPES; i++) {
            switch (i) {
                case 0:
                    requestStack.push(new Request(data.getRandUUID()));
                    break;
                case 1:
                    requestStack.push(new Request.GetRequest(data.getRandUUID(), data.getRandURL()));
                    break;
                case 2:
                    requestStack.push(new Request.PostRequest(data.getRandUUID(), data.getRandIP()));
                    break;
                case 3:
                    requestStack.push(new Request.PostFormRequest(data.getRandUUID(), data.getRandIP(), data.getRandForm()));
                    break;
                case 4:
                    requestStack.push(new Request.PostEncryptedFormRequest(data.getRandUUID(), data.getRandIP(), data.getRandForm(), data.getRandEncryptionScheme()));
                    break;
                case 5:
                    requestStack.push(new Request.PostPaymentRequest(data.getRandUUID(), data.getRandIP(), data.getRandPayment()));
                    break;
                case 6:
                    requestStack.push(new Request.PostEncryptedPaymentRequest(data.getRandUUID(), data.getRandIP(), data.getRandPayment(), data.getRandEncryptionScheme()));
                    break;
                case 7:
                    requestStack.push(new Request.GetFileRequest(data.getRandUUID(), data.getRandFile()));
                    break;
                case 8:
                    requestStack.push(new Request.GetVideoRequest(data.getRandUUID(), data.getRandVideo()));
                    break;
                default:
                    System.out.println("\n\n\nERROR: RANDOM GENERATOR FAILED\n\n\n");
            }
        }

        // print diagnostic info about each request in the stack
        while (!requestStack.isEmpty()) {
            Request req = requestStack.pop();
            System.out.println(req.toString());
            System.out.println();
        }

        // Server summary:
        System.out.println("Server summary:");
        System.out.println("Total Requests: " + Request.count());
        System.out.println("Get Requests: " + Request.GetRequest.count());
        System.out.println("Post Requests: " + Request.PostRequest.count());
        System.out.println("Post Form Requests: " + Request.PostFormRequest.count());
        System.out.println("Post Encrypted Form Requests: " + Request.PostEncryptedFormRequest.count());
        System.out.println("Post Payment Requests: " + Request.PostPaymentRequest.count());
        System.out.println("Post Encrypted Payment Requests: " + Request.PostEncryptedPaymentRequest.count());
        System.out.println("Get File Requests: " + Request.GetFileRequest.count());
        System.out.println("Get Video Requests: " + Request.GetVideoRequest.count());
    }
}
