
package game2;

import java.util.ArrayList;
import javalib.worldimages.*;

public class Game2 {

    public static void main(String[] args) {
        Field f = new Field(0, 
                            new PacMan(new Posn(100,100), 3, 1),
                            new Ghost(new Posn(50, 50), 1),
                            new Ghost(new Posn(50, 50), 2),
                            new Ghost(new Posn(50, 50), 3),
                            new Ghost(new Posn(50, 50), 4),
                            new ArrayList());
        
        f.bigBang(f.upperBound, f.rightBound, .001);
    }
    
}
