package game2;

import java.util.ArrayList;
import javalib.funworld.World;
import javalib.worldimages.*;

public class Walls {

    int rightEnd;
    int topEnd;
    int bottomEnd;
    int leftEnd;
    ArrayList walls = new ArrayList();

    public Walls(int rightBound, int upperBound, int lowerBound, int leftBound) {
        this.rightEnd = rightBound;
        this.topEnd = upperBound;
        this.bottomEnd = lowerBound;
        this.leftEnd = leftBound;

        this.walls.add(makeWall());
    }

    public ArrayList makeWall() {
        ArrayList walls = new ArrayList();
        for (int i = 0; i < (rightEnd - leftEnd); i++) {
            for (int j = 0; j < (topEnd - bottomEnd); j++) {
                Posn point = new Posn(leftEnd + i, bottomEnd + j);
                walls.add(point);
            }
        }
        return walls;
    }

    public void addWall(Walls w) {
        this.walls.add(w.walls);
    }

    public WorldImage makeImage() {
        WorldImage wall = new LineImage(new Posn(0,0), new Posn(0,0), java.awt.Color.BLUE);
        for (int i = 0; i < walls.size(); i++) {
            Posn start = new Posn(leftEnd, bottomEnd);
            Posn end = new Posn(rightEnd, topEnd);
            wall = new LineImage(start, end, java.awt.Color.BLUE);
        }
        return wall;
    }

}
