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
        walls.add(new Walls(new Posn(5, 5), rightBound / 2 - 40, 0));
        walls.add(new Walls(new Posn(rightBound / 2 + 40, 5), rightBound / 2 - 45, 0));

        //rightbound
        walls.add(new Walls(new Posn(rightBound - 5, 5), 0, upperBound / 2 - 40));
        walls.add(new Walls(new Posn(rightBound - 5, upperBound / 2 + 40), 0, upperBound / 2 - 45));

        //lowerbound
        walls.add(new Walls(new Posn(5, upperBound - 5), rightBound / 2 - 40, 0));
        walls.add(new Walls(new Posn(rightBound / 2 + 40, upperBound - 5), rightBound / 2 - 45, 0));

        //leftbound
        walls.add(new Walls(new Posn(5, 5), 0, upperBound / 2 - 40));
        walls.add(new Walls(new Posn(5, upperBound / 2 + 40), 0, upperBound / 2 - 45));

        //upperleftbox
        walls.add(new Walls(new Posn(50, 50), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, 100), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, 50), 0, 50));
        walls.add(new Walls(new Posn(rightBound / 2 - 40, 50), 0, 50));

        //upperrightbox
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, 50), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, 100), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, 50), 0, 50));
        walls.add(new Walls(new Posn(rightBound - 50, 50), 0, 50));

        //lowerleftbox
        walls.add(new Walls(new Posn(50, upperBound - 50), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, upperBound - 100), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, upperBound - 100), 0, 50));
        walls.add(new Walls(new Posn(rightBound / 2 - 40, upperBound - 100), 0, 50));

        //lowerrightbox
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, upperBound - 50), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, upperBound - 100), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, upperBound - 100), 0, 50));
        walls.add(new Walls(new Posn(rightBound - 50, upperBound - 100), 0, 50));

        //upperleftL
        walls.add(new Walls(new Posn(50, 150), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, 150), 0, 50));

        //upperrightL
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, 150), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 200, 150), 0, 50));

        //lowerleftL
        walls.add(new Walls(new Posn(50, upperBound - 150), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(50, upperBound - 200), 0, 50));

        //lowerrightL
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 40, upperBound - 150), rightBound / 2 - 90, 0));
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 200, upperBound - 200), 0, 50));

        //leftI
        walls.add(new Walls(new Posn(100, upperBound / 2 - 25), 0, 50));

        //rightI
        walls.add(new Walls(new Posn(rightBound - rightBound / 2 + 150, upperBound / 2 - 25), 0, 50));
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

        Ghost b2 = blinky.move();
        int bX = b2.position.x;
        int bY = b2.position.y;

        Posn bPosn = new Posn(Math.abs(bX) % rightBound, Math.abs(bY) % upperBound);
        b2 = new Ghost(bPosn, blinky.type, blinky.edible);

        for (int i = 0; i < walls.size(); i++) {
            if (b2.wallContact(walls.get(i))) {
                b2 = new Ghost(blinky.position, blinky.type, blinky.edible);
            }
        }

        Ghost p2 = pinky.move();
        int pX = p2.position.x;
        int pY = p2.position.y;

        Posn pPosn = new Posn(Math.abs(pX) % rightBound, Math.abs(pY) % upperBound);
        p2 = new Ghost(pPosn, pinky.type, pinky.edible);

        for (int i = 0; i < walls.size(); i++) {
            if (p2.wallContact(walls.get(i))) {
                p2 = new Ghost(pinky.position, pinky.type, pinky.edible);
            }
        }

        Ghost i2 = inky.move();
        int iX = i2.position.x;
        int iY = i2.position.y;

        Posn iPosn = new Posn(Math.abs(iX) % rightBound, Math.abs(iY) % upperBound);
        i2 = new Ghost(iPosn, inky.type, inky.edible);

        for (int i = 0; i < walls.size(); i++) {
            if (i2.wallContact(walls.get(i))) {
                i2 = new Ghost(inky.position, inky.type, inky.edible);
            }
        }

        Ghost c2 = clyde.move();
        int cX = c2.position.x;
        int cY = c2.position.y;

        Posn cPosn = new Posn(Math.abs(cX) % rightBound, Math.abs(cY) % upperBound);
        c2 = new Ghost(cPosn, clyde.type, clyde.edible);

        for (int i = 0; i < walls.size(); i++) {
            if (c2.wallContact(walls.get(i))) {
                c2 = new Ghost(clyde.position, clyde.type, clyde.edible);
            }
        }

        for (int i = 0; i < pellets.size(); i++) {
            if (pacMan.pelletConsumed(pellets.get(i))) {
                return increaseScore(pellets.get(i));
            }
        }

        if ((pacMan.ghostContact(b2) && !b2.edible)
                || (pacMan.ghostContact(p2) && !p2.edible)
                || (pacMan.ghostContact(i2) && !i2.edible)
                || (pacMan.ghostContact(c2) && !c2.edible)) {
            System.out.println("life lost");
            return loseLife();
        }

        if (pacMan.ghostContact(b2) && b2.edible) {
            return consumeGhost(b2);
        }
        if (pacMan.ghostContact(p2) && p2.edible) {
            return consumeGhost(p2);
        }
        if (pacMan.ghostContact(i2) && i2.edible) {
            return consumeGhost(i2);
        }
        if (pacMan.ghostContact(c2) && c2.edible) {
            return consumeGhost(c2);
        }

        if (pellets.size() == 0) {
            return new Field(score,
                    new PacMan(new Posn(250, 300), pacMan.direction, pacMan.lives),
                    new Ghost(new Posn(250, 250), 1, false),
                    new Ghost(new Posn(250, 250), 2, false),
                    new Ghost(new Posn(250, 250), 3, false),
                    new Ghost(new Posn(250, 250), 4, false),
                    new Pellets().pellets);
        }

        if (score % 5000 == 0 && score != 0) {
            PacMan nuevoP = new PacMan(pacMan.position, pacMan.direction, pacMan.lives + 1);
            pMan.lives++;
//            return new Field(score,
//                            nuevoP,
//                            blinky,
//                            pinky,
//                            inky,
//                            clyde,
//                            pellets);
        }

        return new Field(score,
                pMan,
                b2,
                p2,
                i2,
                c2,
                pellets);
    }

    public Field consumeGhost(Ghost g) {
        int newScore = score + 200;
        Ghost newG = new Ghost(new Posn(250, 250), g.type, false);
        if (g.type == 1) {
            return new Field(newScore, pacMan, newG, pinky, inky, clyde, pellets);
        } else if (g.type == 2) {
            return new Field(newScore, pacMan, blinky, newG, inky, clyde, pellets);
        } else if (g.type == 3) {
            return new Field(newScore, pacMan, blinky, pinky, newG, clyde, pellets);
        } else if (g.type == 4) {
            return new Field(newScore, pacMan, blinky, pinky, inky, newG, pellets);
        } else {
            return this;
        }

    }

    public Field loseLife() {
        PacMan pMan = new PacMan(new Posn(250, 300), pacMan.direction, pacMan.lives - 1);
        Posn startPosn = new Posn(250, 250);
        Ghost b2 = new Ghost(startPosn, blinky.type, blinky.edible);
        Ghost p2 = new Ghost(startPosn, pinky.type, pinky.edible);
        Ghost i2 = new Ghost(startPosn, inky.type, inky.edible);
        Ghost c2 = new Ghost(startPosn, clyde.type, clyde.edible);

        return new Field(score, pMan, b2, p2, i2, c2, pellets);

    }

    public Field increaseScore(Consumable c) {
        int increase = c.getScore();
        if (c.makeEdible()) {
            blinky.edible = true;
            pinky.edible = true;
            inky.edible = true;
            clyde.edible = true;
        }
        pellets.remove(c);
        int newScore = score + increase;
        Field f = new Field(newScore, pacMan, blinky, pinky, inky, clyde, pellets);
        return f;
    }

    public World onKeyEvent(String key) {
        PacMan pMan = pacMan.changeDirection(key);
        return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
    }

    public WorldImage makeImage() {
        WorldImage text = new TextImage(new Posn(rightBound + 100, 50),
                "Lives: " + pacMan.lives,
                30,
                java.awt.Color.YELLOW);
        text = new OverlayImages(text,
                new TextImage(new Posn(rightBound + 100, 100),
                        "Score: " + score,
                        30,
                        java.awt.Color.YELLOW));
        WorldImage field = new RectangleImage(new Posn(0, 0), 1500, 1500, java.awt.Color.BLACK);
        for (int i = 0; i < pellets.size(); i++) {
            field = new OverlayImages(field, pellets.get(i).makeImage());
        }
        field = new OverlayImages(field, pacMan.makeImage());
        field = new OverlayImages(field, blinky.makeImage());
        field = new OverlayImages(field, pinky.makeImage());
        field = new OverlayImages(field, inky.makeImage());
        field = new OverlayImages(field, clyde.makeImage());
        for (int i = 0; i < walls.size(); i++) {
            field = new OverlayImages(field, walls.get(i).makeImage());
        }
        field = new OverlayImages(field, text);

        return field;
    }

    public WorldImage lastImage(String s) {
        WorldImage returner = new RectangleImage(new Posn(0, 0), 1500, 1500, java.awt.Color.BLACK);
        WorldImage text = new TextImage(new Posn(rightBound / 2, upperBound / 2),
                s,
                30,
                java.awt.Color.YELLOW);
        returner = new OverlayImages(returner, text);
        return returner;
    }

    public WorldEnd worldEnds() {
        if (pacMan.lives == 0) {
            return new WorldEnd(true, this.lastImage("Game over! Score is " + score));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }
}
