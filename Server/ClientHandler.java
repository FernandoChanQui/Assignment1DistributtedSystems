import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Player player;

    // Constructor to initialize the socket, output stream, and input stream
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Getter method for the player
    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {
        try {
            // Prompt the user to enter their name
            out.println("Enter your name:");
            String name = in.readLine();
            player = new Player(name);

            // Broadcast that the player has entered the game
            TriviaServer.broadcast(player.getName() + " has entered the game!", this);
            System.out.println(player.getName() + " has entered the game!");

            // Welcome the player
            out.println("Welcome to the trivia quiz, " + player.getName() + "!");
            boolean playAgain = true;

            // Main game loop
            while (playAgain) {
                runQuiz(); // Run the quiz
                out.println("Quiz finished! Your final score: " + player.getScore());
                TriviaServer.finishGame(player); // Finish the game for the player
                out.println("Final scores of all players who have finished:");
                TriviaServer.sendLeaderboard(this); // Send the leaderboard

                // Ask if the player wants to play again
                out.println("Do you want to play again? Type 'yes' to play again or 'no' to end.");
                String answer = in.readLine();
                if (answer.equalsIgnoreCase("yes")) {
                    player.resetScore(); // Reset the player's score
                } else {
                    out.println("Thank you for playing!");
                    playAgain = false; // End the game loop
                }
            }

            // Broadcast that the player has left the game
            TriviaServer.broadcast(player.getName() + " has left the game!", this);
            System.out.println(player.getName() + " has left the game!");

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to run the quiz
    private void runQuiz() throws IOException {
        for (int i = 1; i <= TriviaServer.totalQuestions; i++) {
            String[] question = getQuestionForDifficulty(player.getScore()); // Get a question based on the player's score
            out.println("Question " + i + ": " + question[0]);
            String correctAnswer = question[1];
            String answer = in.readLine();

            // Check if the answer is correct
            if (answer.equals(correctAnswer)) {
                player.addScore(10); // Add score for correct answer
                out.println("Correct! Your score: " + player.getScore());
            } else {
                out.println("Incorrect! The correct answer was " + correctAnswer + ". Your score: " + player.getScore());
            }
        }
    }

    // Method to get a question based on the player's score
    private String[] getQuestionForDifficulty(int score) {
        if (score >= 60) {
            return TriviaServer.hardQuestions[new Random().nextInt(TriviaServer.hardQuestions.length)];
        } else if (score >= 30) {
            return TriviaServer.mediumQuestions[new Random().nextInt(TriviaServer.mediumQuestions.length)];
        } else {
            return TriviaServer.easyQuestions[new Random().nextInt(TriviaServer.easyQuestions.length)];
        }
    }

    // Method to send a message to the client
    public void sendMessage(String message) {
        out.println(message);
    }
}
