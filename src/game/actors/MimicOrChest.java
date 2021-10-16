package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.OpenChestAction;
import game.enums.Status;
import game.interfaces.ambiguousEnemy;
import game.interfaces.Soul;
import game.items.TokenOfSouls;
import game.weapons.EnemyIntrinsicWeapon;

import java.util.Random;

public class MimicOrChest extends Enemy implements ambiguousEnemy,Soul {
    public static final int MaximumTokenNumbers = 3;
    Location location;



    /**
     * Constructor.
     */
    public MimicOrChest(Location location) {
        super("Chest", '?', 100);
        souls =200;
        lockBehaviours();
        this.location = location;
        initializeToken();
    }

    private void initializeToken(){
        // get a random number from1-3
        Random r = new Random();
        int randomNumber = (r.nextInt(MaximumTokenNumbers)+1);
        for(int i = 0; i<randomNumber;i++){
            TokenOfSouls tokenOfSouls = new TokenOfSouls();
            transferSouls(tokenOfSouls);
            addItemToInventory(tokenOfSouls);
        }

    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }



    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        //  allow the open chest action only when the monster hasn't been opened
        if (otherActor instanceof Player && hasCapability(Status.LOCKED)) {
            actions.add(new OpenChestAction(this));
        }
        if(!hasCapability(Status.LOCKED)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    public Location getLocation() {
        return location;
    }

    public void setDisplayChar(char newDisplayChar) {
        this.displayChar = newDisplayChar;
    }
    public void setName(String newName){
        this.name = newName;
    }


    public void beOpened(){
        this.removeCapability(Status.LOCKED);
    }

    public int countToken(){
        return this.getInventory().size();
    }

    /**
     * first case when player open the "chest"
     */
    public void becomeMimic(){
        //replace its display character from "?" to be "M" and its name
        this.setDisplayChar('M');
        this.setName("Mimic");
        //give it back all normal behaviours
        this.setBehaviours();
    }

    /**
     *
     * @param actor The actor performing the action, the player
     * @param map The map the actor is on.
     */
    public void dropTokens(Actor actor,GameMap map){
        this.setHitPoints(0);
        if (!this.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : this.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(this, map);
            map.removeActor(this);
        }
    }

    @Override
    public void setBehaviours() {
        super.setBehaviours();
    }

    /**
     * override toString to show some basic information for each skeleton, such as hitPoints, weapon that the skeleton holds and so on
     * @return
     */
    @Override
    public String toString() {
        if (!this.hasCapability(Status.LOCKED)) {
            return name + "(" + hitPoints + "/" + maxHitPoints + ")";
        }
        return name;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new EnemyIntrinsicWeapon(55,"kicks","leg");
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }

}
