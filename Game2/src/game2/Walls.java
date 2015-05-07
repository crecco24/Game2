package game2;

import java.util.ArrayList;
import javalib.funworld.World;
import javalib.worldimages.*;

public class Walls {

    Posn start;
    int width;
    int height;

    ArrayList<Posn> points;

    public Walls(Posn start, int width, int height) {
        this.start = start;
        this.width = width;
        this.height = height;
        
        this.points = makeWall();
    }

    public ArrayList<Posn> makeWall() {
        ArrayList<Posn> returner = new ArrayList();
        if (width == 0) {
            for (int i = 0; i < height; i++) {
                Posn point = new Posn(start.x, start.y + i);
                returner.add(point);
            }
        } else {
            for (int i = 0; i < width; i++) {
                Posn point = new Posn(start.x + i, start.y);
                returner.add(point);
            }
        }
        return returner;
    }

    public WorldImage makeImage() {

        if (width == 0) {
            Posn end = new Posn(start.x, start.y + height);
            WorldImage wall = new LineImage(start, end, java.awt.Color.BLUE);
            return wall;
        } else {
            Posn end = new Posn(start.x + width, start.y);
            WorldImage wall = new LineImage(start, end, java.awt.Color.BLUE);
            return wall;
        }
    }

}
