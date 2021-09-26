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
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public class LordOfCinder extends Actor {
    public static final int SOULS = 5000;
    MeleeWeapon weapon;
    private int threshold = 250;
    private boolean stunned = false;

    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints );
        this.addCapability(Abilities.EMBER_FORM);
        weapon = getWeapon();
        this.addItemToInventory(new CindersOfLord());
    }

    /**
     * get Lord of Cinder's weapon
     * @return YhormGreatMachete
     */
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

    /**
     * a boolean method where it tells whether Lord of Cinder is stunned or not
     * @param stunned
     */
    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
}
