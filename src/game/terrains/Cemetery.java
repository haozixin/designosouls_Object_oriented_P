package game.terrains;

import edu.monash.fit2099.engine.*;
import game.actors.Undead;
import game.enums.Abilities;

import java.util.Random;

/**
 * Cemetery -- is a kind of Ground
 * If you want to change its initial place Just need to change the map ArrayList on Application class
 */
public class Cemetery extends Ground {
    /**
     * char 'c' represents Cemetery
     */
    public static final char CEMETERY_CHAR = 'c';

    /**
     * As required:
     * Each cemetery has a 25% success rate to spawn/create Undead (Hollow soldier)(@see Enemies) at every turn
     */
    private int successRate;


    /**
     * Constructor without any parameter
     * It is for FancyGroundFactory
     */
    public Cemetery() {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        setSuccessRate(25);
    }


    /**
     *Creating Undead at the location of each cemetery with a success rate
     * @param location the location of this Cemetery object. (each ground(character in the map) has its own location)
     */
    private void createUndead(Location location){
        //Double check the capability (maybe in future the game might have a kind of mechanism to let it loose the ability)
        if (this.hasCapability(Abilities.CREATE_UNDEAD)){
            Random r = new Random();
            //if the location has an actor, the cemetery cannot generate an Undead here
            if (r.nextInt(100)<successRate && !location.containsAnActor()){
                location.addActor(new Undead("Undead"));
            }
        }else{
        }
    }

    /**
     * ground will execute the function at each turn/round
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        createUndead(location);
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * setter -- set successRate attribute
     * @param successRate success rate to spawn/create Undead at each turn
     * @return boolean value - if setting is successful
     */
    private boolean setSuccessRate(int successRate) {
        boolean isSuccessful = false;
        if (successRate>0){
            this.successRate = successRate;
            isSuccessful = true;
        }
        return isSuccessful;
    }

}
