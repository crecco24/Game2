
package game2;

import java.util.ArrayList;
import javalib.worldimages.*;

public class Game2 {

    public static void main(String[] args) {
        Field f = new Field(0, 
                            new PacMan(new Posn(10,10), 3, 1),
                            new Ghost(new Posn(50, 50), 1),
                            new Ghost(new Posn(50, 50), 2),
                            new Ghost(new Posn(50, 50), 3),
                            new Ghost(new Posn(50, 50), 4),
                            new ArrayList());
        
        f.bigBang(f.upperBound, f.rightBound, .001);
        System.out.println("blinky" + f.blinky.type);
        System.out.println("pinky" + f.pinky.type);
        System.out.println("inky" + f.inky.type);
        System.out.println("clyde" + f.clyde.type);
        System.out.println("mod test 1 " + (1 % 10));
        System.out.println("mod test 9 " + (9 % 10));
        System.out.println("mod test 10 " + (10 % 10));
        System.out.println("mod test 11 " + (11 % 10));
        System.out.println("mod test -1 " + (-1 % 10));
        System.out.println("mod test 0 " + (0 % 10));
        System.out.println("pacman position " + f.pacMan.position.x);
    }
    
}
