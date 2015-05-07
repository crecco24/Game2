
package game2;

import javalib.worldimages.*;

public class PowerPellet implements Consumable {
    
    Posn posn;
    int score;
    
    public PowerPellet(Posn p){
        this.posn = p;
    }
    
    public Posn getPosn(){
        return posn;
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
        return new DiskImage(posn, 10, java.awt.Color.YELLOW);
    }
    
}
