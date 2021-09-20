package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.actors.LordOfCinder;
import game.actors.Player;
import game.actors.Skeleton;
import game.actors.Undead;
import game.enums.Abilities;
import game.interfaces.Behaviour;
import game.weapons.Broadsword;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action implements Behaviour {

	/**
	 * The Actor that is to be attacked
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

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		if (weapon instanceof Broadsword ? true : false){
			// have a rate to double damage
			((Broadsword) weapon).BsPassiveSkill();
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

			if (actor instanceof Player ? true:false){
				Player player = (Player)actor;
				if (target instanceof Undead ? true:false){

					player.addSouls(Undead.SOULS);
					map.removeActor(target);
				}
				else if (target instanceof Skeleton ? true:false){
					// skeleton
					Skeleton skeleton = (Skeleton) target;
					if (skeleton.hasCapability(Abilities.RESURRECT)){

						//on the ResurrectAction, it will show resurrect massage and remove skeleton's this ability
						return result;
					}else{
						map.removeActor(target);
					}
				}
				else if (target instanceof LordOfCinder ? true:false){

					player.addSouls(LordOfCinder.getSOULS());
					map.removeActor(target);
				}
			}else{
				if (target instanceof Player ? true:false){
					// soft-reset
				}
			}
			// problem - when the actor or target both are not Player


			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	// it will shows console as menu options
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		return null;
	}
}
