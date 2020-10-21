package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

import java.util.Random;

public class MushroomManager {

    private GraphicsGroup parentGroup;
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

    public MushroomManager(GraphicsGroup parentGroup) {
        this.parentGroup = parentGroup;
        mushrooms = new ArrayList<>();
        placeMushrooms();
    }

    public void placeMushrooms() {
        for (int i = 0; i < NUM_MUSHROOMS; i++) {
            randX = randXGenerator.nextInt(SnakeGame.getCanvasWidth() - MUSHROOM_WIDTH);
            randY = randYGenerator.nextInt(SnakeGame.getCanvasHeight() - (MUSHROOM_HEIGHT + STEM_HEIGHT));
            Mushroom mushroom = new Mushroom(MUSHROOM_WIDTH, MUSHROOM_HEIGHT, STEM_WIDTH, STEM_HEIGHT, randX, randY);
            parentGroup.add(mushroom);
            mushrooms.add(mushroom);
        }
    }

    public List<Mushroom> getMushrooms() {
        return mushrooms;
    }

    public void removeMushroom(Mushroom mushroom) {
        parentGroup.remove(mushroom);
        mushrooms.remove(mushroom);
    }

    public void removeAllMushrooms() {
        for (Mushroom m : mushrooms) {
            parentGroup.remove(m);
        }
        mushrooms.clear();
    }

    public boolean mushroomsStillExist() {
        return mushrooms.size() > 0;
    }

    public int getNumberOfMushrooms() {
        return mushrooms.size();
    }

    public void placeNewMushroomsOnGameReset() {
        placeMushrooms();
    }

    public boolean findMushroomAtPosition(Point point) {
        for (Mushroom mushroom : List.copyOf(mushrooms)) {

            // Alternative approach: pick one and delete the other
            // eatingDistance = (snakeHeadWidth + mushroom.getHeight()) / 2
            // if (mushroom.getCenter().distance(point) < eatingDistance) {

            if (   point.getX() > mushroom.getCenter().getX() - .5*mushroom.getWidth()
                && point.getX() < mushroom.getCenter().getX() + .5*mushroom.getWidth()
                && point.getY() > mushroom.getCenter().getY() - .5*mushroom.getHeight()
                && point.getY() < mushroom.getCenter().getY() + .5*mushroom.getHeight()) {
                removeMushroom(mushroom);
                return true;
            }
        }
        return false;
    }
}