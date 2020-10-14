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

    public Ellipse makeMushroomHead(int mushroomHeight, int mushroomWidth, int x, int y) {
        Ellipse mushroomHead = new Ellipse(x, y, mushroomWidth, mushroomHeight);
        mushroomHead.setFillColor(MUSHROOM_RED);
        mushroomHead.setFilled(true);
        return mushroomHead;
    }

    public Rectangle makeMushroomStem(int stemWidth, int stemHeight, int x, int y) {
        Rectangle mushroomStem = new Rectangle(x + (.3 *MUSHROOM_WIDTH), y + (MUSHROOM_HEIGHT - 1), stemWidth, stemHeight);
        mushroomStem.setFillColor(MUSHROOM_WHITE);
        mushroomStem.setFilled(true);
        return mushroomStem;
    }

    public void makeMushroom(Ellipse head, Rectangle stem) {
        GraphicsGroup mushroom = new GraphicsGroup();
        mushroom.add(stem);
        mushroom.add(head);
        add(mushroom);
    }
    
}
