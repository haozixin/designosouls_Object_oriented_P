package game.actors;


import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.ResurrectBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;
import game.weapons.MeleeWeapon;
import java.util.ArrayList;
import java.util.Random;



public class Skeleton extends Enemy {



    /**
     * The success rate of this ability(RESURRECT itself)
     */
    public static final int RESURRECT_RATE =50;

    /**
     * initial location - x
     * as required, skeleton needs to know its initial location
     */
    private int initialX;

    /**
     * initial location - y
     */
    private int initialY;
    //carry one random weapon
    MeleeWeapon weapon;


    /**
     * Constructor. they could have different name
     * @param name        the name of the Actor
     */
    public Skeleton(String name,int initialX, int initialY) {

        //@param displayChar the character that will represent the Actor in the display
        //@param hitPoints   the Actor's starting hit points
        super(name, 's', 100);
        //get initial location
        this.initialX = initialX;
        this.initialY = initialY;
        souls = 200;

        setBehaviours();
        addCapabilities();
        // carry random Weapon
        initializeWeapon();

    }

    @Override
    protected void addCapabilities() {
        addCapability(Abilities.RESURRECT);
    }


    /**
     * the function makes sure the skeleton can have a change to resurrect
     */
    @Override
    protected void setBehaviours() {
        super.setBehaviours();
        behaviours.add(0, new ResurrectBehaviour());
    }

    /**
     * initialize weapon for Skeleton - get a random weapon
     * For this game, only two weapon has to been considered
     * @return return a Weapon Item
     */
    private void initializeWeapon(){
        Random r = new Random();
        if(r.nextInt(100)<50){
            weapon = new Broadsword();
        }else{
            weapon = new GiantAxe();
        }
    }



    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Soul asSoul() {
        return null;
    }

    private ArrayList<Behaviour> getBehaviours() {
        return behaviours;
    }

    private int getInitialX() {
        return initialX;
    }

    private int getInitialY() {
        return initialY;
    }

    /**
     * override toString to show some basic information for each skeleton, such as hitPoints, weapon that the skeleton holds and so on
     * @return
     */
    @Override
    public String toString() {
        if (this.hasCapability(Abilities.RESURRECT)){
            return name+"("+hitPoints+"/"+maxHitPoints+") with (might)2 lives(holding:"+weapon+")";
        }else{
            return name+"("+hitPoints+"/"+maxHitPoints+") with 1 life(holding:"+weapon+")";
        }
    }



    /**
     * Add points to the current Actor's hitpoint total.
     *
     * This cannot take the hitpoints over the Actor's maximum. If there is an
     * overflow, hitpoints are silently capped at the maximum.
     *
     * Does not check for consciousness: unconscious Actors can still be healed
     * if the game client allows.
     *
     * @param points number of hitpoints to add.
     */
    @Override
    public void heal(int points) {
        super.heal(points);
    }

    /** Remove a capability from this Actor.
     *
     * @param capability the Capability to remove
     */
    @Override
    public void removeCapability(Enum<?> capability) {
        super.removeCapability(capability);
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }
}
