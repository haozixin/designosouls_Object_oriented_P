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




	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name,Actor target) {
		super(name, 'u', 50, target);
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
	@Override
	public void addCapability(Enum<?> capability) {
		super.addCapability(dieInstantly());
	}
	//Bryan's part
	private Enum<?> dieInstantly() {
		Random r = new Random();
		if (r.nextInt(100)<=10) {
			hitPoints=0;
		}
		return null;
	}
}
