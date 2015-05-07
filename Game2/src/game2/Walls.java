package game2;

import java.util.ArrayList;
import javalib.funworld.World;
import javalib.worldimages.*;

public class Walls {

    int rightEnd;
    int topEnd;
    int bottomEnd;
    int leftEnd;
    ArrayList<Posn> walls;

    public Walls(int rightBound, int upperBound, int lowerBound, int leftBound) {
        this.rightEnd = rightBound;
        this.topEnd = upperBound;
        this.bottomEnd = lowerBound;
        this.leftEnd = leftBound;

        makeWall();
    }

    private void makeWall() {
        for (int i = 0; i < rightEnd; i++) {
            for (int j = 0; j < topEnd; j++) {
                Posn point = new Posn(leftEnd + i, bottomEnd + j);
                walls.add(point);
                System.out.println("contents of walls " + "(" +  point.x + "," + point.y + ")");
            }
        }
    }

    public WorldImage makeImage() {
        
        
        Posn start = new Posn(leftEnd,bottomEnd);
        Posn end = new Posn (rightEnd, topEnd);
        WorldImage wall = new LineImage(start, end, java.awt.Color.BLUE);
//        for (int i = 0; i < walls.size(); i++) {
//            Posn start = new Posn(leftEnd, bottomEnd);
//            Posn end = new Posn(rightEnd, topEnd);
//            wall = new LineImage(start, end, java.awt.Color.BLUE);
//        }
        return wall;
    }

}
