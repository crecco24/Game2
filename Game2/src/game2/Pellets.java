
package game2;

import java.util.ArrayList;
import javalib.worldimages.Posn;

public class Pellets {
    
    ArrayList<Consumable> pellets;
    
    public Pellets(){
        pellets = new ArrayList();
        for(int i = 0; i < 23; i++){
            pellets.add(new Pellet( new Posn(25, 25 + (20 * i))));
        }
        for(int i = 0; i < 23; i ++){
            pellets.add(new Pellet( new Posn(25 + (20 * i), 25)));
        }
        for(int i = 0; i< 23; i++){
            pellets.add(new Pellet( new Posn(25 + (20 * i), 475)));
        }
        for(int i = 0; i< 23; i++){
            pellets.add(new Pellet( new Posn(475, 25 + (20 * i))));
        }
        for(int i = 0; i< 23; i++){
            pellets.add(new Pellet( new Posn(250, 25 + (20 * i))));
        }
        for(int i = 0; i< 23; i++){
            pellets.add(new Pellet( new Posn (25 + (20 * i), 125)));
        }
        for(int i = 0; i< 23; i++){
            pellets.add(new Pellet( new Posn (25 + (20 * i), 375)));
        }
        pellets.add(new PowerPellet(new Posn (25, 250)));
        pellets.add(new PowerPellet(new Posn (475, 250)));
    }
}
