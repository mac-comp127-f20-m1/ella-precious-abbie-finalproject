package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

/** This is the main class that will set up and run a game of "Snake". */

public class SnakeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Snake snake;
    private GraphicsGroup gameLayer;
    private Boolean animating;
    private MushroomManager mushroomManager;
    private Player player;
    private int levels = 1;
    private GraphicsText score;
    private GraphicsText lives;
    private GraphicsText level;

    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(213, 236, 227));
        this.player = new Player();
        score = new GraphicsText();
        lives = new GraphicsText();
        level = new GraphicsText();
        initialLives();
        initialScore();
        initialLevel();
        setUpGame(); 
    }

    public void setUpGame() {
        gameLayer = new GraphicsGroup();

        mushroomManager = new MushroomManager(gameLayer);

        snake = new Snake(Color.red, gameLayer);

        canvas.add(gameLayer);

        animating = true;

        snake.getHead().moveAroundHelper(canvas);
        
        canvas.animate(() -> {if(animating) {
            moveSnake();
        }});
    }
    /**
     * Moves snake head if it is in bounds and not touching body
     */
    public void moveSnake() {
        if(!snake.bodyCollision() &&
        !snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) && player.showLives() > 0) {
            if (testWin()) {
                return;
            }
            snake.moveHead(canvas);
            if (mushroomManager.findMushroomAtPosition(snake.getHead().getCenter())) {
                player.addPoint();
                updateScore();
                snake.makeLonger(15);
            }
            snake.moveBody();
        }
        else if (snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) || snake.bodyCollision()) {
            snake.resetSnakeOnDeath(canvas);
            player.loseLife();
            updateLives();
            return;
        }
        if (player.showLives()== 0) {
            loseMessage();
        }
    }

    /**
     * Updates the screen by resetting mushrooms upon clearing all of them, and increases the level
     * (while the snake retains all of its length).
     * @return Boolean representing whether or not the player has cleared the current level and
     *         should then move on to the next one.
     */
    public boolean testWin() {
        if (!mushroomManager.mushroomsStillExist() && player.showLives() > 0) {
            if (!mushroomManager.mushroomsStillExist() && player.showLives()> 0 ) {
                levels += 1;
                updateLevel();
                mushroomManager.placeNewMushroomsOnGameReset();
                return true;
            }
        }
    return false;
    }

    /**
     * Generates "GAME OVER" graphics text when the player loses
     */
    public void loseMessage() {
        GraphicsText endMessage = new GraphicsText();

        endMessage.setText("GAME OVER");
        endMessage.setPosition(10,300);
        endMessage.setFontStyle(FontStyle.BOLD);
        endMessage.setFontSize(95);
        endMessage.setFillColor(Color.BLUE);
        canvas.add(endMessage);
    }

    public void initialScore() {
        score.setText("Score " + String.valueOf(player.showScore()));
        score.setPosition(CANVAS_WIDTH - 350,20);
        score.setFontStyle(FontStyle.BOLD);
        score.setFontSize(20);
        score.setFillColor(Color.GRAY);
        canvas.add(score); 
    }

    public void updateScore() {
        if (player.showScore() > 0) {
            score.setText("Score " + String.valueOf(player.showScore()));
            if (!score.getText().isEmpty()) {
                score.setText("Score " + String.valueOf(player.showScore()));
                score.setPosition(CANVAS_WIDTH - 350,20);
                score.setFontStyle(FontStyle.BOLD);
                score.setFontSize(20);
                score.setFillColor(Color.GRAY);
                canvas.add(score);
            }
        }
    }

    public void initialLives() {
        lives.setText("Lives " + String.valueOf(player.showLives()));
        lives.setPosition(10,20);
        lives.setFontStyle(FontStyle.BOLD);
        lives.setFontSize(20);
        lives.setFillColor(Color.GRAY);
        canvas.add(lives);
    }

    public void updateLives() {
        if (player.showLives() < 3) {
            lives.setText("Lives " + String.valueOf(player.showLives()));
            lives.setPosition(10,20);
            lives.setFontStyle(FontStyle.BOLD);
            lives.setFontSize(20);
            lives.setFillColor(Color.GRAY);
            canvas.add(lives); 
        }
    }

    public void initialLevel() {
        level.setText("Level " + String.valueOf(levels));
        level.setPosition(CANVAS_WIDTH - 90,20);
        level.setFontStyle(FontStyle.BOLD);
        level.setFontSize(20);
        level.setFillColor(Color.GRAY);
        canvas.add(level);
    }

    public void updateLevel() {
        level.setText("Level " + String.valueOf(levels));
        level.setPosition(CANVAS_WIDTH - 90,20);
        level.setFontStyle(FontStyle.BOLD);
        level.setFontSize(20);
        level.setFillColor(Color.GRAY);
        canvas.add(level);
    }

    public static int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public static int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();  
    }

}
