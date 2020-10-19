package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import java.util.Random;

public class MushroomManager {

    private CanvasWindow canvas;
    private List<Mushroom> mushrooms;
    private static final int MUSHROOM_HEIGHT = 12;
    private static final int MUSHROOM_WIDTH = 25;
    private static final int STEM_HEIGHT = 5;
    private static final int STEM_WIDTH = 15;
    private static final int NUM_MUSHROOMS = 5;
    private Random randXGenerator = new Random();
    private Random randYGenerator = new Random();
    private int randX;
    private int randY;

    public MushroomManager(CanvasWindow canvas) {
        mushrooms = new ArrayList<>();
        this.canvas = canvas;
        placeMushrooms();
    }

    public void placeMushrooms() {
        for (int i = 0; i < NUM_MUSHROOMS; i++) {
            randX = randXGenerator.nextInt(SnakeGame.getCanvasWidth() - MUSHROOM_WIDTH);
            randY = randYGenerator.nextInt(SnakeGame.getCanvasHeight() - (MUSHROOM_HEIGHT + STEM_HEIGHT));
            Mushroom mushroom = new Mushroom(MUSHROOM_WIDTH, MUSHROOM_HEIGHT, STEM_WIDTH, STEM_HEIGHT, randX, randY);
            canvas.add(mushroom);
            mushrooms.add(mushroom);
        }
    }

    private void removeMushroom(Mushroom mushroom) {
        canvas.remove(mushroom);
        mushrooms.remove(mushroom);
    }

    public void removeAllMushrooms() {
        for (Mushroom m : mushrooms) {
            canvas.remove(m);
        }
        mushrooms.clear();
    }

    public boolean mushroomsStillExist() {
        return mushrooms.size() > 0;
    }

    public int getNumberOfMushrooms() {
        return mushrooms.size();
    }
}