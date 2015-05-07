
package game2;

import javalib.worldimages.*;

public class Pellet implements Consumable{
    
    Posn posn;
    int score;
    
    public Pellet(Posn p){
        this.posn = p;
        this.score = 10;
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
        return new DiskImage(posn, 5, java.awt.Color.YELLOW);
    }
    
    
}
