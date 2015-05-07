
package game2;

import javalib.worldimages.*;

public class PowerPellet implements Consumable {
    
    Posn posn;
    int score;
    
    public PowerPellet(Posn p){
        this.posn = p;
    }
    
    public Boolean isConsumed(PacMan p){
        if(p.position == posn){
            return true;
        } else
            return false;
    }
    
    public int getScore(){
        return score;
    }
    
    public WorldImage makeImage(){
        return new DiskImage(posn, 5, java.awt.Color.YELLOW);
    }
    
}
