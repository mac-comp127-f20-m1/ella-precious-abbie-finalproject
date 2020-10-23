package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

import java.util.Random;

/** This a class that handles all of the mushrooms' interactions and is responsible for placing them
 *  on the canvas.
 */

public class MushroomManager {

    private GraphicsGroup parentGroup;
    private List<Mushroom> mushrooms;
    private static final int MUSHROOM_HEIGHT = 12;
    private static final int MUSHROOM_WIDTH = 25;
    private static final int STEM_HEIGHT = 5;
    private static final int STEM_WIDTH = 15;
    private static final int NUM_MUSHROOMS = 5;
    private static final int MUSHROOM_BORDER_OFFSET = 15;
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
            randX = randXGenerator.nextInt(SnakeGame.getCanvasWidth() - MUSHROOM_WIDTH - MUSHROOM_BORDER_OFFSET);
            if (randX < MUSHROOM_BORDER_OFFSET) {
                randX += MUSHROOM_BORDER_OFFSET;
            }
            randY = randYGenerator.nextInt(SnakeGame.getCanvasHeight() - (MUSHROOM_HEIGHT + STEM_HEIGHT) - MUSHROOM_BORDER_OFFSET);
            if (randY < MUSHROOM_BORDER_OFFSET) {
                randY += MUSHROOM_BORDER_OFFSET;
            }
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

    // public int getNumberOfMushrooms() {
    //     return mushrooms.size();
    // }

    public void placeNewMushroomsOnGameReset() {
        placeMushrooms();
    }

    /**
     * Takes a point and will cycle through each mushroom left on the screen to determine
     * if it is at the same location as the point. If it is within the confines of the point,
     * the mushroom will be removed from the screen and the list.
     * @param point
     * @return A boolean that represents whether or not there is a mushroom at the given position.
     */
    public boolean findMushroomAtPosition(Point point) {
        for (Mushroom mushroom : List.copyOf(mushrooms)) {
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