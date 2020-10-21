package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private Snake snake;
    private GraphicsGroup gameLayer;
    private Boolean animating;
    private MushroomManager mushroomManager;
    private int lives = 3;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.blue);
        setUpGame(); 
    }

    public void setUpGame() {
        gameLayer = new GraphicsGroup();

        mushroomManager = new MushroomManager(gameLayer);

        snake = new Snake(Color.red, gameLayer);

        canvas.add(gameLayer);

        animating = true;
        
        canvas.animate(() -> {
            if(animating  && !snake.bodyCollision() &&
            !snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) && lives > 0) {
                if (testWin()) {
                    return;
                }
                snake.moveHead(canvas);
                if (mushroomManager.findMushroomAtPosition(snake.getHead().getCenter())) {
                    snake.makeLonger(25);
                }
                snake.moveBody();
            }
            else if (snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) || snake.bodyCollision()) {
                lives--;
                snake.resetSnakeOnDeath();
                System.out.println("lives: " + lives);
                return;
            }
        });

    }

    public boolean testWin() {
        if (!mushroomManager.mushroomsStillExist() && lives > 0) {
            System.out.println("                              You win !");
            canvas.closeWindow();
            return true;
        }
        return false;
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
