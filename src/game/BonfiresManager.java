package game;

import game.interfaces.BonfireTerrain;
import game.terrains.Bonfire;

import java.util.ArrayList;

/**
 * A class that contains a Collection for all Bonfires and manage them
 *
 */
public class BonfiresManager {
    private static BonfiresManager instance;

    /**
     * The last bonfire to interact with
     */
    private BonfireTerrain lastBonfireToI;

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

    /**
     * get the last bonfire the player interact with
     * @return last bonfire instance
     */
    public BonfireTerrain getLastBonfireToI() {
        return lastBonfireToI;
    }

    public void setLastBonfireToI(BonfireTerrain lastBonfire) {
        this.lastBonfireToI = lastBonfire;
    }
}
