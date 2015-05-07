package game2;

import java.util.ArrayList;
import javalib.funworld.World;
import javalib.worldimages.*;

public class Field extends World {

    int score;
    PacMan pacMan;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    ArrayList<Consumable> pellets;
    ArrayList<Walls> walls = new ArrayList();
    int upperBound = 500;
    int rightBound = 500;

    Field(int s, PacMan p, Ghost b, Ghost pink, Ghost i, Ghost c, ArrayList pellets) {
        this.score = s;
        this.pacMan = p;
        this.blinky = b;
        this.pinky = pink;
        this.inky = i;
        this.clyde = c;
        this.pellets = pellets;
        
        //upperbound
        walls.add(new Walls(new Posn(5,5), rightBound/2 - 40, 0));
        walls.add(new Walls(new Posn(rightBound/2 + 40,5), rightBound/2 - 45, 0));
        
        //rightbound
        walls.add(new Walls(new Posn(rightBound -5, 5), 0, upperBound/2 - 40));
        walls.add(new Walls(new Posn(rightBound -5, upperBound/2 + 40), 0, upperBound/2 -45));
        
        //lowerbound
        walls.add(new Walls(new Posn(5,upperBound-5), rightBound/2 - 40, 0));
        walls.add(new Walls(new Posn(rightBound/2 + 40,upperBound-5), rightBound/2 - 45, 0));
        
        //leftbound
        walls.add(new Walls(new Posn(5, 5), 0, upperBound/2 - 40));
        walls.add(new Walls(new Posn(5, upperBound/2 + 40), 0, upperBound/2 -45));
        
        //upperleftbox
        walls.add(new Walls(new Posn(50,50), rightBound/2 - 90, 0));
        walls.add(new Walls(new Posn(50,100), rightBound/2 - 90, 0));
        walls.add(new Walls(new Posn(50,50), 0, 50));
        walls.add(new Walls(new Posn(rightBound/2 - 40, 50), 0, 50));
        
        //upperrightbox
        walls.add(new Walls(new Posn(rightBound - rightBound/2 +40, 50), rightBound/2-90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound/2 +40, 100), rightBound/2-90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound/2 +40, 50), 0, 50));
        walls.add(new Walls(new Posn(rightBound - 50, 50), 0, 50));
    }

    public World onTick() {
        PacMan pMan = pacMan.move();
        int newX = pMan.position.x;
        int newY = pMan.position.y;

        Posn newPosn = new Posn(Math.abs(newX) % rightBound, Math.abs(newY) % upperBound);
        pMan = new PacMan(newPosn, pMan.direction, pMan.lives);

        for (int i = 0; i < walls.size(); i++) {
            if (pMan.wallContact(walls.get(i))) {
                pMan = new PacMan(pacMan.position, pacMan.direction, pacMan.lives);
            }
        }

        Ghost b2 = blinky.move(pacMan);
        if (b2.position.x > rightBound || b2.position.x < 0) {
            b2 = blinky;
        }
        if (b2.position.y > upperBound || b2.position.y < 0) {
            b2 = blinky;
        }

        Ghost p2 = blinky.move(pacMan);
        if (p2.position.x > rightBound || p2.position.x < 0) {
            p2 = blinky;
        }
        if (p2.position.y > upperBound || p2.position.y < 0) {
            p2 = blinky;
        }

        Ghost i2 = blinky.move(pacMan);
        if (i2.position.x > rightBound || i2.position.x < 0) {
            i2 = blinky;
        }
        if (i2.position.y > upperBound || i2.position.y < 0) {
            i2 = blinky;
        }

        Ghost c2 = blinky.move(pacMan);
        if (c2.position.x > rightBound || c2.position.x < 0) {
            c2 = blinky;
        }
        if (c2.position.y > upperBound || c2.position.y < 0) {
            c2 = blinky;
        }
        
        for(int i = 0; i < pellets.size(); i++){
            if(pacMan.pelletConsumed(pellets.get(i))){
                increaseScore(pellets.get(i));
            }
        }

        return new Field(score,
                pMan,
                b2,
                p2,
                i2,
                c2,
                pellets);
    }

    public Field increaseScore(Consumable c) {
        pellets.remove(c);
        int increase = c.getScore();
        Field f = new Field(score + increase, pacMan, blinky, pinky, inky, clyde, pellets);
        return f;
    }

    public World onKeyEvent(String key) {
        if (key.equals("right")) {
            PacMan pMan = new PacMan(pacMan.position, 1, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if (key.equals("up")) {
            PacMan pMan = new PacMan(pacMan.position, 2, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if (key.equals("left")) {
            PacMan pMan = new PacMan(pacMan.position, 3, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if (key.equals("down")) {
            PacMan pMan = new PacMan(pacMan.position, 4, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else {
            return this;
        }
    }

    public WorldImage makeImage() {
        WorldImage field = new RectangleImage(new Posn(0, 0), 1500, 1500, java.awt.Color.BLACK);
        field = new OverlayImages(field, pacMan.makeImage());
        field = new OverlayImages(field, blinky.makeImage());
        field = new OverlayImages(field, pinky.makeImage());
        field = new OverlayImages(field, inky.makeImage());
        field = new OverlayImages(field, clyde.makeImage());
        for (int i = 0; i < walls.size(); i++) {
            field = new OverlayImages(field, walls.get(i).makeImage());
        }
        for (int i = 0; i< pellets.size(); i++){
            field = new OverlayImages(field, pellets.get(i).makeImage());
        }
        return field;
    }

}
