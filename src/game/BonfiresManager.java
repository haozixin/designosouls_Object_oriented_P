package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.BonfireInterface;
import game.terrains.Bonfire;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that contains a Collection for all Bonfires and manage them
 */
public class BonfiresManager {
    private static BonfiresManager instance;

    /**
     * The last bonfire to interact with
     */
    private BonfireInterface lastBonfireToI;

    //private HashMap<Bonfire,Location> bonfires = new HashMap<>();
    private ArrayList<Bonfire> bonfires = new ArrayList<>();

    public static BonfiresManager getInstance(){
        if(instance == null){
            instance = new BonfiresManager();
        }
        return instance;
    }

    public void collectLocation(Bonfire bonfire){
        bonfires.add(bonfire);
    }

    public ArrayList<Bonfire> getBonfires() {
        return bonfires;
    }

    public BonfireInterface getLastBonfireToI() {
        return lastBonfireToI;
    }

    public void setLastBonfireToI(BonfireInterface lastBonfire) {
        this.lastBonfireToI = lastBonfire;
    }
}
