package snakeGame;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/** This is a class representing the mushrooms, the food the snake will eat to get longer. */

public class Mushroom extends GraphicsGroup {
    private static final Color MUSHROOM_RED = new Color(237,120,131);
    private static final Color MUSHROOM_WHITE = new Color(247, 250, 250);
    private int mushroomWidth;
    private int mushroomHeight;
    private int stemWidth;
    private int stemHeight;

    public Mushroom(int mushroomWidth, int mushroomHeight, int stemWidth, int stemHeight, int x, int y) {
        this.mushroomHeight = mushroomHeight;
        this.mushroomWidth = mushroomWidth;
        this.stemHeight = stemHeight;
        this.stemWidth = stemWidth;
        // x and y are set to 0 in makeMushroomHead and makeMushroomStem to avoid conflicting 
        // coordinates within the GraphicsGroup and main gameLayer
        makeMushroom(makeMushroomHead(mushroomHeight, mushroomWidth, 0, 0), makeMushroomStem(stemWidth, stemHeight, 0, 0));
        this.setPosition(x, y);
    }

    /**
     * 
     * @param mushroomHeight
     * @param mushroomWidth
     * @param x
     * @param y
     * @return A graphics group of a red ellipse with white dots, representing the top half of the 
     *         mushroom.
     */
    public GraphicsGroup makeMushroomHead(int mushroomHeight, int mushroomWidth, int x, int y) {
        Ellipse mushroomHead = new Ellipse(x, y, mushroomWidth, mushroomHeight);
        Rectangle flatHead = new Rectangle(x, y + (mushroomHeight *.5), mushroomWidth, (mushroomHeight *.5));
        GraphicsGroup head = new GraphicsGroup();
        Ellipse bigCircle1 = new Ellipse(x + (mushroomWidth * .2), y + (mushroomHeight *.15), mushroomHeight *.5, mushroomHeight *.5);
        Ellipse bigCircle2 = new Ellipse(x + (mushroomWidth * .55), y + (mushroomHeight *.5), mushroomHeight *.5, mushroomHeight *.5);
        Ellipse smallCircle1 = new Ellipse(x + (mushroomWidth * .56), y + (mushroomHeight *.03), mushroomHeight *.3, mushroomHeight *.3);
        Ellipse smallCircle2 = new Ellipse(x + (mushroomWidth * .03), y + (mushroomHeight *.7), mushroomHeight *.2, mushroomHeight *.2);
        Ellipse smallCircle3 = new Ellipse(x + (mushroomWidth * .85), y + (mushroomHeight *.3), mushroomHeight *.2, mushroomHeight *.2);
        mushroomHead.setFillColor(MUSHROOM_RED);
        mushroomHead.setFilled(true);
        flatHead.setFillColor(MUSHROOM_RED);
        flatHead.setFilled(true);
        bigCircle1.setFillColor(MUSHROOM_WHITE);
        bigCircle1.setFilled(true);
        bigCircle2.setFillColor(MUSHROOM_WHITE);
        bigCircle2.setFilled(true);
        smallCircle1.setFillColor(MUSHROOM_WHITE);
        smallCircle1.setFilled(true);
        smallCircle2.setFillColor(MUSHROOM_WHITE);
        smallCircle2.setFilled(true);
        smallCircle3.setFillColor(MUSHROOM_WHITE);
        smallCircle3.setFilled(true);
        head.add(flatHead);
        head.add(mushroomHead);
        head.add(bigCircle1);
        head.add(bigCircle2);
        head.add(smallCircle1);
        head.add(smallCircle2);
        head.add(smallCircle3);
        return head;
    }

    /**
     * 
     * @param stemWidth
     * @param stemHeight
     * @param x
     * @param y
     * @return A white rectangle representing the base of the mushroom.
     */
    public Rectangle makeMushroomStem(int stemWidth, int stemHeight, int x, int y) {
        Rectangle mushroomStem = new Rectangle(x + (.2 *mushroomWidth), y + (mushroomHeight - (mushroomHeight * .2)), stemWidth, stemHeight);
        mushroomStem.setFillColor(MUSHROOM_WHITE);
        mushroomStem.setFilled(true);
        return mushroomStem;
    }

    public void makeMushroom(GraphicsGroup head, Rectangle stem) {
        GraphicsGroup mushroom = new GraphicsGroup();
        mushroom.add(stem);
        mushroom.add(head);
        add(mushroom);
    }
}
