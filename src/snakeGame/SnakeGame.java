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
    private GraphicsGroup group;
    private Boolean animating;
    private MushroomManager mushroomManager;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.blue);
        setUpGame(); 
    }


    public void setUpGame() {
        group = new GraphicsGroup();

        mushroomManager = new MushroomManager(canvas);
        for (Mushroom mushroom : mushroomManager.getMushrooms()) {
            group.add(mushroom);
        }

        snake = new Snake(Color.red, canvas);
        for (GraphicsObject part : snake.getBodyGraphics()) {
            group.add(part);
        }
        group.add(snake.getHead());

        canvas.add(group);

        animating = true;
        
        canvas.animate(() -> {
            if(animating  && !snake.bodyCollision() &&
            !snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT)) {
                snake.moveHead();
                mushroomManager.testHit(snake.getHead());
                snake.moveBody();
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
