package snakeGame;

/**
 * Manages score and lives
 */
public class Player {
    private int score;
    private int lives;

    public Player() {
        score = 0;
        lives = 3;
    }
    
    public void addPoint() {
        score += 10;
    }

    public void loseLife() {
        lives -= 1;
    }

    public int showScore() {
        return score;
    }

    public int showLives() {
        return lives;
    }

    public void gameOver() {
        score = 0;
        lives = 0;
    }
}
