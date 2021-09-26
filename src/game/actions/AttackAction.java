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
import game.weapons.StormRuler;
import game.weapons.YhormGreatMachete;

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

	/**
	 * Perform the Action.
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
		int extraDamage =0;

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		if (weapon instanceof Broadsword){
			// have a rate to double damage
			extraDamage = ((Broadsword) weapon).passiveSkill();
			//damage is half if player use storm ruler and enemy is not Lord of Cinder
		} else if(weapon instanceof StormRuler && !(target instanceof LordOfCinder)){
			extraDamage -= weapon.damage()/2;
		}
		int damage = weapon.damage()+extraDamage;
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			if (actor instanceof Player){
				Player player = (Player)actor;
				if (target instanceof Undead){

					player.addSouls(Undead.SOULS);
					map.removeActor(target);
				}
				else if (target instanceof Skeleton){
					// skeleton
					Skeleton skeleton = (Skeleton) target;
					if (skeleton.hasCapability(Abilities.RESURRECT) && rand.nextInt(100) < 50){
						//on the ResurrectAction, it will show resurrect massage and remove skeleton's this ability
						skeleton.removeCapability(Abilities.RESURRECT);
						skeleton.heal(100);
						return result;
					}else{
						player.addSouls(Skeleton.SOULS);
						map.removeActor(target);
					}
				}
				else if (target instanceof LordOfCinder){

					player.addSouls(LordOfCinder.getSOULS());
					map.removeActor(target);
				}
			}
			else{
				if (target instanceof Player ? true:false){
					// soft-reset
				}
			}



			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/** it will show message on console as menu options
	 *
	 * @param actor The actor performing the action.
	 * @return a String that will shows console as menu options
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		return null;
	}
}
