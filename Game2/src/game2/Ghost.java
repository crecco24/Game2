package game2;

import java.util.Random;
import javalib.worldimages.*;

public class Ghost {

    Posn position;
    int type;
    boolean edible;
    int direction;

    Ghost(Posn p, int t, boolean e) {
        this.position = p;
        this.type = t;
        this.edible = e;
        this.direction = randInt(1,4);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public boolean changeDirectionHuh() {
        int decider = randInt(0, 100);
        return decider == 1;
    }

    public Ghost changeDirection() {
        int newDir = randInt(1, 4);
        direction = newDir;
        return this;
    }

    public Ghost move() {
        return move2(changeDirectionHuh());
    }

    public Ghost move1() {
        int chooser = randInt(0, 2);
        int picker = randInt(0, 2);
        int dx = 1;
        int dy = 1;
        if (chooser % 2 == 0) {
            if (picker % 2 == 0) {
                Posn positionB = new Posn(position.x + dx, position.y);
                return new Ghost(positionB, type, edible);
            } else if (picker % 2 == 1) {
                Posn positionB = new Posn(position.x - dx, position.y);
                return new Ghost(positionB, type, edible);
            } else {
                return this;
            }
        } else if (picker % 2 == 0) {
            Posn positionB = new Posn(position.x, position.y + dy);
            return new Ghost(positionB, type, edible);
        } else if (picker % 2 == 0) {
            Posn positionB = new Posn(position.x, position.y - dy);
            return new Ghost(positionB, type, edible);
        } else {
            return this;
        }
    }

    public Ghost move2(boolean b) {
//        if(b){
//            changeDirection();
//            System.out.println("changing directions");
//        }
        int currentX = position.x;
        int currentY = position.y;
        int deltaX = 1;
        int deltaY = 1;
        Posn newPosn = position;
        if (direction == 1) {
            newPosn = new Posn(currentX + deltaX, currentY);
        } else if (direction == 2) {
            newPosn = new Posn(currentX, currentY - deltaY);
        } else if (direction == 3) {
            newPosn = new Posn(currentX - deltaX, currentY);
        } else if (direction == 4) {
            newPosn = new Posn(currentX, currentY + deltaY);
        }
        position = newPosn;
        return this;
    }

    public int distance(Posn p) {
        int a = position.x - p.x;
        int b = position.y - p.y;
        int c = (int) Math.sqrt((a * a) + (b * b));
        return c;
    }

    public boolean wallContact(Walls w) {
        for (int i = 0; i < w.points.size(); i++) {
            if (distance(w.points.get(i)) <= 18) {
                return true;
            }
        }
        return false;
    }

    public WorldImage makeImage() {
        if (edible) {
            return new DiskImage(position, 18, java.awt.Color.BLUE);
        }
        if (type == 1) {
            return new DiskImage(position, 18, java.awt.Color.RED);
        }
        if (type == 2) {
            return new DiskImage(position, 18, java.awt.Color.PINK);
        }
        if (type == 3) {
            return new DiskImage(position, 18, java.awt.Color.GREEN);
        } else {
            return new DiskImage(position, 18, java.awt.Color.ORANGE);
        }
    }
}
