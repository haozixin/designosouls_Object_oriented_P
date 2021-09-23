package game.terrains;

import edu.monash.fit2099.engine.*;
import game.CemeteryHelper;
import game.actors.Player;
import game.actors.Undead;
import game.enums.Abilities;
import game.interfaces.CemeteryHelperInt;

import java.util.Random;

/**
 * Cemetery -- is a kind of Ground
 * If you want to change its initial place Just need to change the map ArrayList on Application class
 */
public class Cemetery extends Ground {
    public static final char CEMETERY_CHAR = 'c';
    private int successRate;
    private CemeteryHelperInt cemeteryHelper;

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
     * Constructor with a parameter which is cemeteryHelperInterface
     * @param cemeteryHelper is the helper that we created on Application Class(it contains gameMap, "Stirng map")
     */
    public Cemetery(CemeteryHelperInt cemeteryHelper) {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        setSuccessRate(25);
        setCemeteryHelper(cemeteryHelper);
    }

    /**
     *Creating Undead at the location of each cemetery with a success rate
     * @param location the location of this Cemetery object. (each ground(character in the map) has its own location)
     */
    private void createUndead(Location location){
        //Double check the capability (maybe in future the game might have a kind of mechanism to let it loose the ability)
        if (this.hasCapability(Abilities.CREATE_UNDEAD)){
            Random r = new Random();
            Actor player = cemeteryHelper.getPlayer();
            if (r.nextInt(100)<successRate){
                cemeteryHelper.getGameMap().at(location.x(), location.y()).addActor(new Undead("Undead",player));
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

    private boolean setSuccessRate(int successRate) {
        boolean isSuccessful = false;
        if (successRate>0){
            this.successRate = successRate;
            isSuccessful = true;
        }
        return isSuccessful;
    }

    private boolean setCemeteryHelper(CemeteryHelperInt cemeteryHelper) {
        boolean isSuccessful = false;
        if (successRate>0){
            this.cemeteryHelper = cemeteryHelper;
            isSuccessful = true;
        }
        return isSuccessful;
    }
}
