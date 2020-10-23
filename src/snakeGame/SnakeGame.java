package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;

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

    private GraphicsText endMessage;
    private GraphicsText score;


    public SnakeGame() {
        canvas = new CanvasWindow("Snake Game!", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(213, 236, 227));
        this.player = new Player();
        initialScore();
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
                System.out.println("                                       score:" + player.showScore());
                snake.makeLonger(15);
            }
            snake.moveBody();
        }
        else if (snake.wallCollision(CANVAS_WIDTH, CANVAS_HEIGHT) || snake.bodyCollision()) {
            snake.resetSnakeOnDeath(canvas);
            player.loseLife();
            System.out.println("                                       lives: " + player.showLives());
            return;
        }
        if (player.getLives() == 0) {
            loseMessage();
        }
    }

    public boolean testWin() {
        if (!mushroomManager.mushroomsStillExist() && player.showLives() > 0) {
            if (!mushroomManager.mushroomsStillExist() && player.getLives() > 0 ) {
                levels += 1;
                System.out.println("                                               Level " + levels);
                mushroomManager.placeNewMushroomsOnGameReset();
                return true;
            }
        }
    return false;
    }

    public void winMessage() {
        endMessage = new GraphicsText();

        endMessage.setText("YOU WIN!");
        endMessage.setPosition(50, 300);
        endMessage.setFontStyle(FontStyle.BOLD);
        endMessage.setFontSize(100);
        endMessage.setFillColor(Color.BLUE);
        canvas.add(endMessage);
    }

    public void loseMessage() {
        endMessage = new GraphicsText();

        endMessage.setText("GAME OVER");
        endMessage.setPosition(10,300);
        endMessage.setFontStyle(FontStyle.BOLD);
        endMessage.setFontSize(95);
        endMessage.setFillColor(Color.BLUE);
        canvas.add(endMessage);
    }

    public void initialScore() {
        score = new GraphicsText();
        score.setText("Score " + String.valueOf(player.showScore()));
    }

    public void updateScore() {
        if (player.showScore() > 0) {
            score.setText("Score " + String.valueOf(player.showScore()));
            if (!score.getText().isEmpty()) {
                score.setText("Score " + String.valueOf(player.showScore()));
                score.setPosition(10,20);
                score.setFontStyle(FontStyle.BOLD);
                score.setFontSize(20);
                score.setFillColor(Color.GRAY);
                canvas.add(score);
            }
        }
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
