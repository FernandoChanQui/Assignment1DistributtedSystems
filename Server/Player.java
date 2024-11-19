public class Player {
    // Fields to store the player's name and score
    private String name;
    private int score;

    // Constructor to initialize the player's name and set the initial score to 0
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Getter method for the player's name
    public String getName() {
        return name;
    }

    // Getter method for the player's score
    public int getScore() {
        return score;
    }

    // Method to add points to the player's score
    public void addScore(int points) {
        score += points;
    }

    // Method to set the player's score to a specific value
    public void setScore(int score) {
        this.score = score;
    }

    // Method to reset the player's score to 0
    public void resetScore() {
        score = 0;
    }
}
