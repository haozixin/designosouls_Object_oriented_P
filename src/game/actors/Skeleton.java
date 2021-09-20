package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.ResurgenceAction;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GameWeaponItem;
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
    GameWeaponItem weapon;





    /**
     * Constructor. they could have different name
     * @param name        the name of the Actor
     */
    public Skeleton(String name,int initialX, int initialY) {

        //@param displayChar the character that will represent the Actor in the display
        //@param hitPoints   the Actor's starting hit points
        super(name, 's', 10);
        //get initial location
        this.initialX = initialX;
        this.initialY = initialY;
        behaviours.add(new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        // carry random Weapon
        weapon = initializeWeapon();
        obtainResurrectAbility();


    }

    private void obtainResurrectAbility(){
        Random r =new Random();
        if (r.nextInt(100)<Skeleton.resurrectRate){
            this.addCapability(Abilities.RESURRECT);
        }
    }


    public GameWeaponItem initializeWeapon(){
        Random r = new Random();
        if(r.nextInt(2)<1){
            return new Broadsword();
        }else{
            return new GiantAxe();
        }
    }





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
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // need to optimize

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
//        for(Behaviour behaviour : behaviours) {
//            //actions.add(behaviour.getAction(this, map));
//            Action action = behaviour.getAction(this, map);
//            /*Action action = behaviour.getAction(this, map);
//            if (action != null)
//                return action;*/
//            System.out.println("!!!!!!!!!!!!");
//            return action;
//        }

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
            return name+"("+hitPoints+"/"+maxHitPoints+") with (might)2 lives";
        }else{
            return name+"("+hitPoints+"/"+maxHitPoints+") with 1 life";
        }
    }
}
