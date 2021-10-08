package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.actors.*;
import game.enums.Abilities;
import game.interfaces.PlayerInterface;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action{

	/**
	 * The Actor that is to be attacked
	 * it might be enemy or player
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Execute the Action.
	 * consider all actor might use the same attack action, so I add a judgement process
	 * which contains two cases when the target is going to die - 1,target is enemy / 2, target is player
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();


		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}


		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			String result1 = distinguishTarget(actor, map, result);
			if (result1 != null) return result1;


			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * A class to distinguish Targets, because different targets need to do different following operations
	 * @param actor the actor who is going to perform the attack
	 * @param map The map the actor is on.
	 * @param result output massage that is created on executed()
	 * @return
	 */
	private String distinguishTarget(Actor actor, GameMap map, String result) {

		// if the actor who is going to perform the attack is Player
		if (actor instanceof Player){
			PlayerInterface player = (Player) actor;

			// what will happen if Undead is going to die
			if (target instanceof Undead){

				player.addSouls(Undead.SOULS);
				map.removeActor(target);
			}
			// what will happen if Skeleton is going to die
			else if (target instanceof Skeleton){
				// skeleton
				Skeleton skeleton = (Skeleton) target;
				if (skeleton.hasCapability(Abilities.RESURRECT)){

					//on the ResurrectAction, it will show resurrect massage and remove skeleton's this ability
					return result;
				}else{
					map.removeActor(target);
				}
			}
			// what will happen if LordOfCinder is going to die
			else if (target instanceof YhormTheGiant){

				player.addSouls(YhormTheGiant.getSOULS());
				map.removeActor(target);
			}
			else if(target instanceof AldrichTheDevourer){
				//
			}
		}
		// if other enemies kill the player, it will execute soft-rest functionality
		else{
			if (target instanceof Player){
				// soft-reset
			}
		}
		return null;
	}

	/**
	 * it will shows message on console
	 * @param actor The actor performing the action.
	 * @return a String that will shows console as menu options
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}



}
