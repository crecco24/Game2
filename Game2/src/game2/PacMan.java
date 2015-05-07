
package game2;
import java.util.ArrayList;
import javalib.worldimages.*;

public class PacMan {
    
    Posn position;
    int direction;
    int lives;
    int mouthCounter;
    
    PacMan(Posn p, int d, int l){
        this.position = p;
        this.direction = d;
        this.lives = l;
        this.mouthCounter = 0;
    }
    
    public PacMan move(){
        int dx = 1;
        int dy = 1;
        if(direction == 1){
            int xB = position.x + dx;
            Posn positionB = new Posn(xB, position.y);
            return new PacMan(positionB, direction, lives);
        } else if(direction == 2){
            int yB = position.y - dy;
            Posn positionB = new Posn(position.x, yB);
            return new PacMan(positionB, direction, lives);
        } else if(direction == 3){
            int xB2 = position.x - dx;
            Posn positionB = new Posn(xB2, position.y);
            return new PacMan(positionB, direction, lives);
        } else if(direction == 4){
            int yB2 = position.y + dy;
            Posn positionB = new Posn(position.x, yB2);
            return new PacMan(positionB, direction, lives);
        } else return this;
    }
    
    public PacMan changeDirection(String key){
        if(key.equals("right")){
            return new PacMan(position, 1, lives);
        } else if (key.equals("up")){
            return new PacMan(position, 2, lives);
        } else if (key.equals("left")){
            return new PacMan(position, 3, lives);
        } else if (key.equals("down")){
            return new PacMan(position, 4, lives);
        } else return this;
    }
    
    public boolean pelletConsumed(Consumable c){
        if(distance(c.getPosn()) <= 20){
            return true;
        } else 
            return false;
    }
    
    public int distance(Posn p){
        int a = position.x - p.x;
        int b = position.y - p.y;
        int c = (int) Math.sqrt((a * a) + (b * b));
        return c;
    }
    
    public boolean wallContact(Walls w){
        for (int i = 0; i < w.points.size(); i++) {
            if (distance(w.points.get(i)) <= 20) {
                return true;
            }
        }
        return false;
    }
    
    public boolean ghostContact(Ghost g){
        return this.position == g.position;
    }
    
    public PacMan loseLife(){
        return new PacMan(position, direction, lives - 1);
    }
    
    public PacMan addLife(){
        return new PacMan(position, direction, lives + 1);
    }
    
    public WorldImage makeImage(){
        if(mouthCounter % 2 == 0){
            mouthCounter++;
            return new DiskImage(position, 20, java.awt.Color.YELLOW);
            
        } else 
            mouthCounter++;
            return new DiskImage(position, 10, java.awt.Color.RED);
    }
    
}
