package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.terrains.Bonfire;

import java.util.ArrayList;
import java.util.HashMap;

public class BonfiresManager {
    private static BonfiresManager instance;

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
}
