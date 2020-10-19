package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private CanvasWindow canvas;
    private BodyPart part;
    private Head head;
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

        part = new BodyPart(300, 300, Color.green);
        group.add(part);
        head = new Head(200, 200, Color.red);
        group.add(head);

        canvas.add(group);

        animating = true;
        
        canvas.animate(() -> {if(animating){
            moveHead(head);
            mushroomManager.testHit(head);
        }});

    }

    public void moveHead(Head head) {
        head.moveAround(canvas);
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
