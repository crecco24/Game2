package game2;

import java.util.Random;
import javalib.worldimages.*;

public class Ghost {

    Posn position;
    int type;
    boolean edible;

    Ghost(Posn p, int t) {
        this.position = p;
        this.type = t;
        this.edible = false;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public Ghost move(PacMan p) {
        if (type == 1) {
            return move1(p);
        } else if (type == 2) {
            return move2();
        } else if (type == 3) {
            return move3();
        } else if (type == 4) {
            return move4();
        } else {
            return this;
        }
    }

    public Ghost move1(PacMan p) {
        int chooser = randInt(0, 1);
        int picker = randInt(0, 1);
        int dx = 1;
        int dy = 1;
        if (chooser % 2 == 0) {
            if (picker % 2 == 0) {
                Posn positionB = new Posn(position.x + dx, position.y);
                return new Ghost(positionB, type);
            } else if (picker % 2 == 1) {
                Posn positionB = new Posn(position.x - dx, position.y);
                return new Ghost(positionB, type);
            } else {
                return this;
            }
        } else if (picker % 2 == 0) {
            Posn positionB = new Posn(position.x, position.y + dy);
            return new Ghost(positionB, type);
        } else if (picker % 2 == 0) {
            Posn positionB = new Posn(position.x, position.y - dy);
            return new Ghost(positionB, type);
        } else {
            return this;
        }
    }

    public WorldImage makeImage() {
        if (edible) {
            return new DiskImage(position, 10, java.awt.Color.BLUE);
        }
        if (type == 1) {
            return new DiskImage(position, 10, java.awt.Color.RED);
        }
        if (type == 2) {
            return new DiskImage(position, 10, java.awt.Color.PINK);
        }
        if (type == 3) {
            return new DiskImage(position, 10, java.awt.Color.GREEN);
        } else {
            return new DiskImage(position, 10, java.awt.Color.ORANGE);
        }
    }
}
