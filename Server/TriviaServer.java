import java.io.*;
import java.net.*;
import java.util.*;

public class TriviaServer {
    // List to keep track of connected clients
    private static List<ClientHandler> clients = new ArrayList<>();
    // List to keep track of players who have finished the game
    private static List<Player> finishedPlayers = new ArrayList<>();
    // Total number of questions in the trivia game
    public static int totalQuestions = 10;

    // Define easy questions and their answers
    static final String[][] easyQuestions = {
        {"What is the capital of Canada?", "Ottawa"},
        {"How many planets are in our solar system?", "Eight"},
        {"Who painted the Mona Lisa?", "Leonardo da Vinci"},
        {"What is the largest ocean on Earth?", "Pacific Ocean"},
        {"What is the chemical symbol for gold?", "Au"},
        {"Who wrote the play 'Romeo and Juliet'?", "William Shakespeare"},
        {"What is the square root of 144?", "12"},
        {"What is the opposite of hot?", "Cold"},
        {"How many sides does a triangle have?", "Three"},
        {"What is the largest country in the world by land area?", "Russia"}
    };

    // Define medium questions and their answers
    static final String[][] mediumQuestions = {
        {"What is the longest river in North America?", "The Mississippi River"}, 
        {"Who was the first president of the United States?", "George Washington"}, 
        {"What is the chemical symbol for gold?", "Au"}, 
        {"Who wrote the famous novel Pride and Prejudice?", "Jane Austen"}, 
        {"What is the square root of 169?", "13"}, 
        {"Who painted the Mona Lisa?", "Leonardo da Vinci"}, 
        {"What is the most famous musical composition by Ludwig van Beethoven?", "Symphony No. 9"}, 
        {"What is the philosophical concept of cogito ergo sum often associated with?", "René Descartes"}, 
        {"Who is considered the father of psychoanalysis?", "Sigmund Freud"}, 
        {"In which sport is the term ace used?", "Tennis"}
    };

    // Define hard questions and their answers
    static final String[][] hardQuestions = {
        {"What is the largest freshwater lake in the world?", "Lake Superior"}, 
        {"Who was the first person to walk on the moon?", "Neil Armstrong"}, 
        {"What is the smallest unit of matter?", "Atom"}, 
        {"Who wrote the play Hamlet?", "William Shakespeare"}, 
        {"What is the value of pi to 10 decimal places?", "3.1415926536"}, 
        {"Who is the composer of the opera The Magic Flute?", "Wolfgang Amadeus Mozart"}, 
        {"What is the famous theory proposed by Albert Einstein?", "Theory of relativity"}, 
        {"Who is the founder of modern psychology?", "Wilhelm Wundt"}, 
        {"What is the term for the study of the human mind and behavior?", "Psychology"}, 
        {"What is the name of the largest muscle in the human body?", "Gluteus Maximus"}
    };

    public static void main(String[] args) throws IOException {
        // Create a server socket listening on port 12345
        ServerSocket serverSocket = new ServerSocket(1000);
        System.out.println("Trivia server started...");
        
        // Continuously accept new client connections
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    // Broadcast a message to all clients except the one specified
    public static synchronized void broadcast(String message, ClientHandler excludeClient) {
        for (ClientHandler client : clients) {
            if (client != excludeClient) {
                client.sendMessage(message);
            }
        }
    }

    // Handle the end of the game for a player
    public static synchronized void finishGame(Player player) {
        boolean playerExists = false;

        // Check if the player already exists in the finished players list
        for (int i = 0; i < finishedPlayers.size(); i++) {
            Player p = finishedPlayers.get(i);
            if (p.getName().equals(player.getName())) {
                // Update the score if the new score is higher
                if (player.getScore() > p.getScore()) {
                    p.setScore(player.getScore());
                }
                playerExists = true;
                break;
            }
        }

        // Add the player to the finished players list if they don't already exist
        if (!playerExists) {
            finishedPlayers.add(player);
        }

        // Sort the finished players list by score in descending order
        finishedPlayers.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        // Remove the client from the clients list
        clients.removeIf(client -> client.getPlayer().getName().equals(player.getName()));
        // Display the updated leaderboard
        displayLeaderboard();
    }

    // Display the leaderboard in the console
    public static void displayLeaderboard() {
        System.out.println("\n--- Leaderboard ---");
        for (Player player : finishedPlayers) {
            System.out.println(player.getName() + ": " + player.getScore() + " points");
        }
    }

    // Send the leaderboard to a specific client
    public static synchronized void sendLeaderboard(ClientHandler client) {
        StringBuilder leaderboard = new StringBuilder("\n--- Leaderboard ---\n");
        for (Player player : finishedPlayers) {
            leaderboard.append(player.getName()).append(": ").append(player.getScore()).append(" points\n");
        }
        client.sendMessage(leaderboard.toString());
    }
}
