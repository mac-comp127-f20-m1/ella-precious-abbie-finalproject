package snakeGame;

import edu.macalester.graphics.CanvasWindow;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 700;
    private CanvasWindow canvas;
    private Mushroom mushroom;

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        
    }

    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        mushroom = new Mushroom(50, 25, 30, 10, 400, 400);
        canvas.add(mushroom);
    }
}
