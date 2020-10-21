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

    // public void testHeadAndMushroomCollision() {
    //     if (mushroomManager.findMushroomAtPosition(snake.getHead().getCenter()) != null) {
    //         mushroomManager.removeMushroom(mushroomManager.findMushroomAtPosition(snake.getHead().getCenter()));
    //     }
    // }

    // public boolean testHeadMushroomCollision() {
    //     if (mushroomManager.findMushroomAtPosition(snake.getHead().getCenter()) != null) {
    //         return true;
    //     }
    //     return false;
    // }


    public void setUpGame() {
        gameLayer = new GraphicsGroup();

        mushroomManager = new MushroomManager(gameLayer);

        snake = new Snake(Color.red, canvas);
        for (GraphicsObject part : snake.getBodyGraphics()) {
            gameLayer.add(part);
        }
        gameLayer.add(snake.getHead());

        canvas.add(gameLayer);

        animating = true;
        
        canvas.animate(() -> {
            if(animating  && !snake.bodyCollision() &&
            !snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) && lives > 0) {
                snake.moveHead();
                //mushroomManager.testHit(snake.getHead());
                //testHeadAndMushroomCollision();
                mushroomManager.findMushroomAtPosition(snake.getHead().getCenter());
                // if (testHeadMushroomCollision()) {
                //     mushroomManager.removeMushroom(mushroomManager.findMushroomAtPosition(snake.getHead().getCenter()));
                // }
                snake.moveBody();
            }
            else if (snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) || snake.bodyCollision()) {
                lives--;
                System.out.println("lives: " + lives);
            }
        });

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
