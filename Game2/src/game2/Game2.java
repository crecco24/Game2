
package game2;

import java.util.ArrayList;
import javalib.worldimages.*;

public class Game2 {

    public static void main(String[] args) {
        ArrayList<Consumable> pellets = new ArrayList();
        pellets.add(new Pellet(new Posn(125, 125)));
        
        Field f = new Field(0, 
                            new PacMan(new Posn(250,250), 3, 1),
                            new Ghost(new Posn(50, 50), 1),
                            new Ghost(new Posn(50, 50), 2),
                            new Ghost(new Posn(50, 50), 3),
                            new Ghost(new Posn(50, 50), 4),
                            pellets);
        
        f.bigBang(f.upperBound, f.rightBound, .001);
    }
    
}
