package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.ResurgenceAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

import java.util.ArrayList;
import java.util.Random;

public class Skeleton extends Actor {
    public static final int SOULS = 250;
    public static final int resurrectRate=50;
    private ArrayList<Behaviour> behaviours = new ArrayList<>();
    //skeleton needs to know its initial location
    private int initialX;
    private int initialY;
    //carry one random weapon
    WeaponItem weapon;





    /**
     * Constructor. they could have different name
     * @param name        the name of the Actor
     */
    public Skeleton(String name,int initialX, int initialY, Actor target) {

        //@param displayChar the character that will represent the Actor in the display
        //@param hitPoints   the Actor's starting hit points
        super(name, 's', 100);
        //get initial location
        this.initialX = initialX;
        this.initialY = initialY;
        behaviours.add(new FollowBehaviour(target));
        behaviours.add(new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        // carry random Weapon
        weapon = initializeWeapon();
        this.addCapability(Abilities.RESURRECT);

    }


    /**
     * initialize weapon for Skeleton - get a random weapon
     * For this game, only two weapon has to been considered
     * @return return a Weapon Item
     */
    public WeaponItem initializeWeapon(){
        Random r = new Random();
        if(r.nextInt(100)<50){
            return new Broadsword();
        }else{
            return new GiantAxe();
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


    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {


        // if the skeleton is going to die and has the capability of RESURRECT, do ResurgenceAction this turn
        // else: use for loop to get all actions in the behaviours arraylist,
        // the sequence would be: can do attackAction?-->can do followBehaviour? --> can do wanderBehaviour?
        if (!this.isConscious() && this.hasCapability(Abilities.RESURRECT)){
           return new ResurgenceAction(this);
        }
        else{
            for(Behaviour behaviour : behaviours) {
                Action action = behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }


        return new DoNothingAction();
    }

    @Override
    public void hurt(int points) {
        hitPoints -= points;
        hitPoints = Math.max(hitPoints, 0);
    }

    public static int getSOULS() {
        return SOULS;
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

    @Override
    public String toString() {
        if (this.hasCapability(Abilities.RESURRECT)){
            return name+"("+hitPoints+"/"+maxHitPoints+") with (might)2 lives - holding:"+weapon;
        }else{
            return name+"("+hitPoints+"/"+maxHitPoints+") with 1 life - holding:"+weapon;
        }
    }

    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(revive());
    }

    private Enum<?> revive() {
        Random r = new Random();
        if (r.nextInt(100)<=50) {
            hitPoints = maxHitPoints;
        }
        return null;
    }
}
