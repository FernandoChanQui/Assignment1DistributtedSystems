import java.io.*;

// Listener class implements Runnable to allow it to be run by a thread
public class Listener implements Runnable {
    private BufferedReader in;

    // Constructor to initialize the BufferedReader
    public Listener(BufferedReader in) {
        this.in = in;
    }

    // Override the run method from Runnable interface
    @Override
    public void run() {
        try {
            String fromServer;
            // Continuously read lines from the BufferedReader until null is encountered
            while ((fromServer = in.readLine()) != null) {
                // Print the received line to the console
                System.out.println(fromServer);
            }
        } catch (IOException e) {
            // Print stack trace if an IOException occurs
            e.printStackTrace();
        }
    }
}
