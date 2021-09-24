package game.actors;


import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.PlayerIntrinsicWeapon;

import java.util.ArrayList;
import java.util.Random;

/**
 * An undead minion.
 */
public class Undead extends Enemy {
	/**
	 * SOULS - how many souls the Undead could yield when it's killed / how many souls the player could get
	 * from the skeleton after killing it
	 */
	public static final int SOULS = 50;


	private ArrayList<Behaviour> behaviours = new ArrayList<>();

	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name,Actor target) {
		super(name, 'u', 50);
		behaviours.add(new FollowBehaviour(target));
		behaviours.add(new WanderBehaviour());
		this.addCapability(Status.HOSTILE_TO_PLAYER);

		// Bryan's part:
		//this.addCapability(dieInstantly());
	}



	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
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
		// loop through all behaviours

		for(Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}


	/**
	 * Do some damage to the skeleton. But the hitPoints only can go down to 0 at most.
	 *
	 * If the player's hitpoints go down to zero, it will be knocked out.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		hitPoints = Math.max(hitPoints, 0);
	}

	public static int getSOULS() {
		return SOULS;
	}



	/**
	 * override toString to show some basic information for each Undead, such as hitPoints, weapon that the skeleton holds and so on
	 * @return
	 */
	@Override
	public String toString() {
		return name+" ("+hitPoints+"/"+maxHitPoints+")"+" (no weapons)";
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new PlayerIntrinsicWeapon(20, "punches","fist");

	}

	//Bryan's part
	// it causes a serious problem. please Mimic the implementation of other features
//	@Override
//	public void addCapability(Enum<?> capability) {
//		super.addCapability(dieInstantly());
//	}


//	private Enum<?> dieInstantly() {
//		Random r = new Random();
//		if (r.nextInt(100)<=10) {
//			hitPoints=0;
//		}
//		return null;
//	}
}
