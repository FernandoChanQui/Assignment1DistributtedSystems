import java.io.*;
import java.net.*;

// The TriviaClient class is responsible for connecting to the trivia server, handling user input, and receiving server messages.
public class TriviaClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader stdIn;
    private Listener listener;

    // Constructor that establishes a connection to the server and sets up input/output streams.
    public TriviaClient(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, 1000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        stdIn = new BufferedReader(new InputStreamReader(System.in));

        listener = new Listener(in);
        new Thread(listener).start();
        handleInput();
    }

    // Method to handle user input and send it to the server.
    public void handleInput() throws IOException {
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
        }
    }

    // Main method to run the TriviaClient.
    public static void main(String[] args) throws IOException {
        String serverAddress = args.length == 0 ? "localhost" : args[0];
        new TriviaClient(serverAddress);
    }
}
