package game2;

import java.util.ArrayList;
import java.util.Random;
import javalib.funworld.World;
import javalib.worldimages.Posn;

public class Tester {

    public static String randomKey() {
        Random rand = new Random();
        int nextInt = Math.abs(rand.nextInt());
        if (nextInt % 4 == 0) {
            return "up";
        } else if (nextInt % 4 == 1) {
            return "right";
        } else if (nextInt % 4 == 2) {
            return "left";
        } else if (nextInt % 4 == 3) {
            return "down";
        } else {
            return "1";
        }
    }

    public static boolean inBounds(Posn p, Field f) {
        int upperBound = f.upperBound;
        int rightBound = f.rightBound;
        int x = p.x;
        int y = p.y;

        if (y <= upperBound && y >= 0) {
            if (x <= rightBound && x >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //characterInBoundTest
    public static boolean characterInBoundTest(Field f) {
        for (int i = 0; i < 1000; i++) {
            f = (Field) f.onTick();

            Posn pacPosn = f.pacMan.position;
            Posn bPosn = f.blinky.position;
            Posn pPosn = f.pinky.position;
            Posn iPosn = f.inky.position;
            Posn cPosn = f.clyde.position;

            if (!inBounds(pacPosn, f)
                    || !inBounds(bPosn, f)
                    || !inBounds(pPosn, f)
                    || !inBounds(iPosn, f)
                    || !inBounds(cPosn, f)) {
                return false;
            }
        }
        return true;
    }

    public static boolean touchingWall(Posn p, Field f) {
        for (int i = 0; i < f.walls.size(); i++) {
            for (int j = 0; j < f.walls.get(i).points.size(); j++) {
                if (p.equals(f.walls.get(i).points.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    //characterWallTest
    public static boolean characterWallTest(Field f) {
        for (int i = 0; i < 1000; i++) {
            f = (Field) f.onTick();

            Posn pacPosn = f.pacMan.position;
            Posn bPosn = f.blinky.position;
            Posn pPosn = f.pinky.position;
            Posn iPosn = f.inky.position;
            Posn cPosn = f.clyde.position;

            if (touchingWall(pacPosn, f)
                    || touchingWall(bPosn, f)
                    || touchingWall(pPosn, f)
                    || touchingWall(iPosn, f)
                    || touchingWall(cPosn, f)) {
                return false;
            }
        }
        return true;
    }

    //livesTest
    public static boolean livesTest(Field f) {
        for (int i = 0; i < 1000; i++) {
            f = (Field) f.onTick();
            Field f2 = (Field) f.onTick();

            int lives1 = f.pacMan.lives;
            int lives2 = f2.pacMan.lives;

            if (lives1 < 0 || lives2 < 0) {
                return false;
            }
            if (lives2 != lives1 + 1) {
                if (lives2 != lives1 - 1) {
                    if (lives2 != lives1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean ghostContact(Field f) {
        PacMan fPac = f.pacMan;
        Ghost fBlinky = f.blinky;
        Ghost fPinky = f.pinky;
        Ghost fInky = f.inky;
        Ghost fClyde = f.clyde;

        if (fPac.ghostContact(fBlinky)
                || fPac.ghostContact(fPinky)
                || fPac.ghostContact(fInky)
                || fPac.ghostContact(fClyde)) {
            return true;
        }
        return false;
    }

    public static boolean livesDec(Field f1, Field f2) {
        int lives1 = f1.pacMan.lives;
        int lives2 = f2.pacMan.lives;

        if (lives2 == lives1 - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean edibleGhosts(Field f) {
        boolean blinky = f.blinky.edible;
        boolean pinky = f.pinky.edible;
        boolean inky = f.inky.edible;
        boolean clyde = f.clyde.edible;

        if (blinky || pinky || inky || clyde) {
            return true;
        } else {
            return false;
        }
    }

    //ghostContactTest
    public static boolean ghostContactTest(Field f) {
        for (int i = 0; i < 1000; i++) {
            f = (Field) f.onTick();
            Field f2 = (Field) f.onTick();

            if (ghostContact(f)) {
                if (!livesDec(f, f2)) {
                    return false;
                } else if (edibleGhosts(f)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean illegalScoreChange(Field f1, Field f2) {
        int score1 = f1.score;
        int score2 = f2.score;

        if (score1 == score2
                || score2 == score1 + 10
                || score2 == score1 + 70
                || score2 == score1 + 200) {
            return false;
        } else {
            return true;
        }
    }

    //scoringTest
    public static boolean scoringTest(Field f) {
        for (int i = 0; i < 1000; i++) {
            f = (Field) f.onTick();
            Field f2 = (Field) f.onTick();

            if (illegalScoreChange(f, f2)) {
                return false;
            }
            if (f.score < 0 || f2.score < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean improperMovement(PacMan p1, PacMan p2, String key) {
        int p1x = p1.position.x;
        int p1y = p1.position.y;
        int p2x = p2.position.x;
        int p2y = p2.position.y;

        if (key.equals("left")) {
            if (p2x != p1x - 1) {
                return true;
            }
        }
        if (key.equals("right")) {
            if (p2x != p1x + 1) {
                return true;
            }
        }
        if (key.equals("up")) {
            if (p2y != p1y - 1) {
                return true;
            }
        }
        if (key.equals("down")) {
            if (p2y != p1y + 1) {
                return true;
            }
        }
        if (key.equals("1")) {
            if (p2x != p1x && p2y != p1y){
                return true;
            }
        }
        return false;
    }

    //movementTest
    public static boolean movementTest(PacMan p) {
        for (int i = 0; i < 1000; i++) {
            String key = randomKey();

            PacMan p2 = p.changeDirection(key);
            p2 = p2.move();

            if (improperMovement(p, p2, key)) {
                System.out.println("Failed on " + i + " " + key);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Field f = new Field(0,
                new PacMan(new Posn(250, 300), 3, 3),
                new Ghost(new Posn(250, 250), 1, false, 1),
                new Ghost(new Posn(250, 250), 2, false, 2),
                new Ghost(new Posn(250, 250), 3, false, 3),
                new Ghost(new Posn(250, 250), 4, false, 2),
                new Pellets().pellets);

        boolean characterInBoundTester = characterInBoundTest(f);
        if (characterInBoundTester) {
            System.out.println("Character In Bounds Test passed on 1000 tests");
        } else {
            System.out.println("Character In Bound Test failed");
        }

        Field f1 = new Field(0,
                new PacMan(new Posn(250, 300), 3, 3),
                new Ghost(new Posn(250, 250), 1, false, 1),
                new Ghost(new Posn(250, 250), 2, false, 2),
                new Ghost(new Posn(250, 250), 3, false, 3),
                new Ghost(new Posn(250, 250), 4, false, 2),
                new Pellets().pellets);

        boolean characterWallTester = characterWallTest(f1);
        if (characterWallTest(f1)) {
            System.out.println("Character Wall Test passed on 1000 tests");
        } else {
            System.out.println("Charcter Wall Test fails");
        }

        Field f2 = new Field(0,
                new PacMan(new Posn(250, 300), 3, 3),
                new Ghost(new Posn(250, 250), 1, false, 1),
                new Ghost(new Posn(250, 250), 2, false, 2),
                new Ghost(new Posn(250, 250), 3, false, 3),
                new Ghost(new Posn(250, 250), 4, false, 2),
                new Pellets().pellets);

        boolean livesTester = livesTest(f2);
        if (livesTester) {
            System.out.println("Lives test passed on 1000 tests");
        } else {
            System.out.println("Lives test failed");
        }

        Field f3 = new Field(0,
                new PacMan(new Posn(250, 300), 3, 3),
                new Ghost(new Posn(250, 250), 1, false, 1),
                new Ghost(new Posn(250, 250), 2, false, 2),
                new Ghost(new Posn(250, 250), 3, false, 3),
                new Ghost(new Posn(250, 250), 4, false, 2),
                new Pellets().pellets);

        boolean ghostContactTester = ghostContactTest(f3);
        if (ghostContactTester) {
            System.out.println("Ghost Contact Test passed on 1000 tests");
        } else {
            System.out.println("Ghost Contact Test failed");
        }

        Field f4 = new Field(0,
                new PacMan(new Posn(250, 300), 3, 3),
                new Ghost(new Posn(250, 250), 1, false, 1),
                new Ghost(new Posn(250, 250), 2, false, 2),
                new Ghost(new Posn(250, 250), 3, false, 3),
                new Ghost(new Posn(250, 250), 4, false, 2),
                new Pellets().pellets);

        boolean scoringTester = scoringTest(f4);
        if (scoringTester) {
            System.out.println("Scoring test passed on 1000 tests");
        } else {
            System.out.println("Scoring test failed");
        }
        
        PacMan p = new PacMan(new Posn (250, 300),
                              1,
                              3);
        boolean movementTester = movementTest(p);
        if(movementTester){
            System.out.println("Movement Test passed on 1000 tests");
        } else
            System.out.println("Movement Test failed");
    }
}
