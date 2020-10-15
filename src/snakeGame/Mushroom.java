package snakeGame;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Mushroom extends GraphicsGroup {
    private static final Color MUSHROOM_RED = new Color(237,120,131);
    private static final Color MUSHROOM_WHITE = new Color(247, 250, 250);
    private int MUSHROOM_WIDTH;
    private int MUSHROOM_HEIGHT;
    private int STEM_WIDTH;
    private int STEM_HEIGHT;
    private int x;
    private int y;

    public Mushroom(int mushroomWidth, int mushroomHeight, int stemWidth, int stemHeight, int x, int y) {
        this.MUSHROOM_HEIGHT = mushroomHeight;
        this.MUSHROOM_WIDTH = mushroomWidth;
        this.STEM_HEIGHT = stemHeight;
        this.STEM_WIDTH = stemWidth;
        this.x = x;
        this.y = y;
        makeMushroom(makeMushroomHead(mushroomHeight, mushroomWidth, x, y), makeMushroomStem(stemWidth, stemHeight, x, y));
    }

    public GraphicsGroup makeMushroomHead(int mushroomHeight, int mushroomWidth, int x, int y) {
        Ellipse mushroomHead = new Ellipse(x, y, mushroomWidth, mushroomHeight);
        Rectangle flatHead = new Rectangle(x, y + (mushroomHeight *.5), mushroomWidth, (mushroomHeight *.5));
        GraphicsGroup head = new GraphicsGroup();
        Ellipse bigCircle1 = new Ellipse(x + (MUSHROOM_WIDTH * .2), y + (MUSHROOM_HEIGHT *.15), MUSHROOM_HEIGHT *.5, MUSHROOM_HEIGHT *.5);
        Ellipse bigCircle2 = new Ellipse(x + (MUSHROOM_WIDTH * .55), y + (MUSHROOM_HEIGHT *.5), MUSHROOM_HEIGHT *.5, MUSHROOM_HEIGHT *.5);
        Ellipse smallCircle1 = new Ellipse(x + (MUSHROOM_WIDTH * .56), y + (MUSHROOM_HEIGHT *.03), MUSHROOM_HEIGHT *.3, MUSHROOM_HEIGHT *.3);
        Ellipse smallCircle2 = new Ellipse(x + (MUSHROOM_WIDTH * .03), y + (MUSHROOM_HEIGHT *.7), MUSHROOM_HEIGHT *.2, MUSHROOM_HEIGHT *.2);
        Ellipse smallCircle3 = new Ellipse(x + (MUSHROOM_WIDTH * .85), y + (MUSHROOM_HEIGHT *.3), MUSHROOM_HEIGHT *.2, MUSHROOM_HEIGHT *.2);
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

    public Rectangle makeMushroomStem(int stemWidth, int stemHeight, int x, int y) {
        Rectangle mushroomStem = new Rectangle(x + (.2 *MUSHROOM_WIDTH), y + (MUSHROOM_HEIGHT - (MUSHROOM_HEIGHT * .2)), stemWidth, stemHeight);
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
