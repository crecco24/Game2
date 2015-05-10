
package game2;

import javalib.worldimages.*;

public interface Consumable {
    
    Boolean isConsumed(PacMan p);
    Posn getPosn();
    int getScore();
    boolean makeEdible();
    
    WorldImage makeImage();
    
}
