package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.BurnAction;
import game.enums.Abilities;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;
import game.weapons.YhormGreatMachete;

/**
 * The boss of Design o' Souls
 */
public class YhormTheGiant extends LordOfCinder {
    public static final int SOULS = 5000;
    MeleeWeapon weapon;
    private int threshold = 250;
    private boolean stunned = false;

    /**
     * Constructor.
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'Y', 500);
        this.addCapability(Abilities.EMBER_FORM);
        weapon = getWeapon();
        this.addItemToInventory(new CindersOfLord());
    }

    public MeleeWeapon getWeapon() {
        return new YhormGreatMachete();
    }

    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (hitPoints<=threshold) {
            ((YhormGreatMachete)weapon).emberForm();
        }
    }

    /**
     *
     * @return SOULS
     */
    public static int getSOULS() {
        return SOULS;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.stunned) {
            this.stunned = false;
            return new DoNothingAction();
        }
        if (this.isConscious() && this.hasCapability(Abilities.EMBER_FORM)) {

        }
        return new DoNothingAction();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        Location otherLoc = map.locationOf(otherActor);
        Location yhormLoc = map.locationOf(this);
        if(Math.abs(otherLoc.x()-yhormLoc.x()) < 2 && Math.abs(otherLoc.y()-yhormLoc.y()) < 2) {
            actions.add(new BurnAction(otherActor));
        }
        return actions;
    }

    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
}
