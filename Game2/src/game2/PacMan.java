
package game2;
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
            int xB = position.x - dx;
            Posn positionB = new Posn(xB, position.y);
            return new PacMan(positionB, direction, lives);
        } else if(direction == 4){
            int yB = position.y + dy;
            Posn positionB = new Posn(position.x, yB);
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
            return new DiskImage(position, 10, java.awt.Color.YELLOW);
            
        } else 
            mouthCounter++;
            return new DiskImage(position, 10, java.awt.Color.RED);
    }
    
}
