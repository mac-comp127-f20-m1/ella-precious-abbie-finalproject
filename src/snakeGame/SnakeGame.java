package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

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
    // private int lives = 3;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(213, 236, 227));
        this.player = new Player();
        setUpGame(); 
    }

    public void setUpGame() {
        gameLayer = new GraphicsGroup();

        mushroomManager = new MushroomManager(gameLayer);

        snake = new Snake(Color.red, gameLayer);

        canvas.add(gameLayer);

        animating = true;
        
        canvas.animate(() -> {if(animating) {
            moveSnake();
        }});
    }
    
    public void moveSnake() {
        if(!snake.bodyCollision() &&
        !snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) && player.showLives() > 0) {
            if (testWin()) {
                return;
            }
            snake.moveHead(canvas);
            if (mushroomManager.findMushroomAtPosition(snake.getHead().getCenter())) {
                player.addPoint();
                System.out.println("                                       score:" + player.showScore());
                snake.makeLonger(25);
            }
            snake.moveBody();
        }
        else if (snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) || snake.bodyCollision()) {
            snake.resetSnakeOnDeath(canvas);
            player.loseLife();
            System.out.println("                                       lives: " + player.showLives());
            return;
        }
    }

    public boolean testWin() {
        if (!mushroomManager.mushroomsStillExist() && player.showLives() > 0) {
            if (!mushroomManager.mushroomsStillExist() && player.getLives() > 0 && levels < 2) {
                levels += 1;
                System.out.println("                                               Level " + levels);
                mushroomManager.placeNewMushroomsOnGameReset();
                // System.out.println("                              You win !");
                // canvas.closeWindow();
                return true;
            }
            if (levels >= 2) {
                System.out.println("                              You win !");
                canvas.closeWindow();
                return true;
            }
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
