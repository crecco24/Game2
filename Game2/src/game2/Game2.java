
package game2;

import java.util.ArrayList;
import java.util.Random;
import javalib.worldimages.*;

public class Game2 {

    public static void main(String[] args) {  
        
        Field f = new Field(0, 
                            new PacMan(new Posn(250,300), 3, 3),
                            new Ghost(new Posn(250, 250), 1, false, 1),
                            new Ghost(new Posn(250, 250), 2, false, 2),
                            new Ghost(new Posn(250, 250), 3, false, 3),
                            new Ghost(new Posn(250, 250), 4, false, 2),
                            new Pellets().pellets);
        
        f.bigBang(f.rightBound + 200, f.upperBound, .002);
        
    }
    
}
