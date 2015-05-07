
package game2;

import javalib.worldimages.*;

public interface Consumable {
    
    Boolean isConsumed(PacMan p);
    
    int getScore();
    
}
