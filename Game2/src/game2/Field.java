
package game2;

import java.util.ArrayList;
import javalib.funworld.World;
import javalib.worldimages.*;


public class Field extends World {
    
    int score;
    PacMan pacMan;
    Ghost blinky;
    Ghost pinky;
    Ghost inky;
    Ghost clyde;
    ArrayList pellets;
    ArrayList walls;
    int upperBound = 720;
    int rightBound = 720;
    
    Field(int s, PacMan p, Ghost b, Ghost pink, Ghost i, Ghost c, ArrayList pellets){
        this.score = s;
        this.pacMan = p;
        this.blinky = b;
        this.pinky = pink;
        this.inky = i;
        this.clyde = c;
        this.pellets = pellets;
        this.walls = new ArrayList();
    }
    
    public World onTick(){
        PacMan pMan = pacMan.move();
        if(pMan.position.x > rightBound || pMan.position.x < 0){
            pMan = new PacMan(pacMan.position, pacMan.direction, pacMan.lives);
        }
        if(pMan.position.y > upperBound || pMan.position.y < 0){
            pMan = pacMan;
        }
        
        Ghost b2 = blinky.move(pacMan);
        if(b2.position.x > rightBound || b2.position.x < 0){
            b2 = blinky;
        }
        if(b2.position.y > upperBound || b2.position.y < 0){
            b2 = blinky;
        }
        
        Ghost p2 = blinky.move(pacMan);
        if(p2.position.x > rightBound || p2.position.x < 0){
            p2 = blinky;
        }
        if(p2.position.y > upperBound || p2.position.y < 0){
            p2 = blinky;
        }
        
        Ghost i2 = blinky.move(pacMan);
        if(i2.position.x > rightBound || i2.position.x < 0){
            i2 = blinky;
        }
        if(i2.position.y > upperBound || i2.position.y < 0){
            i2 = blinky;
        }
        
        Ghost c2 = blinky.move(pacMan);
        if(c2.position.x > rightBound || c2.position.x < 0){
            c2 = blinky;
        }
        if(c2.position.y > upperBound || c2.position.y < 0){
            c2 = blinky;
        }
        
        return new Field(score,
                         pMan,
                         b2,
                         p2,
                         i2,
                         c2,
                         pellets);
        
    }
    
    public World onKeyEvent(String key){
        if(key.equals("right")){
            PacMan pMan = new PacMan(pacMan.position, 1, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if(key.equals("up")){
            PacMan pMan = new PacMan(pacMan.position, 2, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if(key.equals("left")){
            PacMan pMan = new PacMan(pacMan.position, 3, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else if(key.equals("down")){
            PacMan pMan = new PacMan(pacMan.position, 4, pacMan.lives);
            return new Field(score, pMan, blinky, pinky, inky, clyde, pellets);
        } else return this;
    }
    
    public WorldImage makeImage(){
        WorldImage field = new RectangleImage(new Posn(0,0), 1000, 1000, java.awt.Color.BLACK);
        field = new OverlayImages(field, pacMan.makeImage());
        field = new OverlayImages(field, blinky.makeImage());
        field = new OverlayImages(field, pinky.makeImage());
        field = new OverlayImages(field, inky.makeImage());
        field = new OverlayImages(field, clyde.makeImage());
        return field;
    }
    
}
