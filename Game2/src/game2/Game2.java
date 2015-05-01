
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
        
        f.bigBang(720, 720, .001);
        System.out.println("blinky" + f.blinky.type);
        System.out.println("pinky" + f.pinky.type);
        System.out.println("inky" + f.inky.type);
        System.out.println("clyde" + f.clyde.type);
    }
    
}
