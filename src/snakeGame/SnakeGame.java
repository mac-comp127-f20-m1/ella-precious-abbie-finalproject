package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 700;
    private static final int CANVAS_HEIGHT = 700;
    private CanvasWindow canvas;
    private Mushroom mushroom;
    private BodyPart part;
    private Head head;
    private GraphicsGroup group;
    private Boolean animating;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.blue);
        setUpGame(); 
    }


    public void setUpGame() {
        group = new GraphicsGroup();

        mushroom = new Mushroom(50, 25, 30, 10, 400, 400);
        group.add(mushroom);

        part = new BodyPart(300, 300, Color.green);
        group.add(part);
        head = new Head(200, 200, Color.red);
        group.add(head);

        canvas.add(group);

        animating = true;
        
        canvas.animate(() -> {if(animating){
            moveHead(head);
        }});

    }

    public void moveHead(Head head) {
        head.moveAround(canvas);
    }


    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
        
    }








}
