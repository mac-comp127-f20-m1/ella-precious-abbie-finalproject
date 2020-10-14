package snakeGame;

import edu.macalester.graphics.CanvasWindow;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 700;
    private static CanvasWindow canvas;

    public static void main(String[] args) {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        
    }
}
