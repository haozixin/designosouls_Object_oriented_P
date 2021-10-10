package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.MimicOrChestInterface;
import game.weapons.EnemyIntrinsicWeapon;

public class MimicOrChest extends Enemy implements MimicOrChestInterface {
    Location location;
    public static final int SOULS = 200;

    /**
     * Constructor.
     */
    public MimicOrChest(Location location) {
        super("Chest", '?', 100);
        lockBehaviours();
        this.location = location;
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        //  allow the open chest action only when the monster hasn't been opened
        if (otherActor instanceof Player && hasCapability(Status.LOCKED)) {
            actions.add(new OpenChestAction(this));
        }
        return actions;
    }

    private Location getLocation() {
        return location;
    }

    public void setDisplayChar(char newDisplayChar) {
        this.displayChar = newDisplayChar;
    }
    public void setName(String newName){
        this.name = newName;
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

    public void dropTokens(){

    }

}
