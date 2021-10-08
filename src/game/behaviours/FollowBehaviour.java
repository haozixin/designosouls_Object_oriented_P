package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actors.AldrichTheDevourer;
import game.actors.Player;
import game.enums.Abilities;
import game.interfaces.Behaviour;
import game.utilities.Utility;
import game.weapons.DarkmoonLongbow;
import static game.weapons.DarkmoonLongbow.DETECT_RANGE;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FollowBehaviour extends Actions implements Behaviour {


	/**
	 * follow target
	 * it might be get from Constructor's parameter
	 */
	private Actor target;
	/**
	 * as long as the player stands on the place that is within the range of detection, it will be true
	 * true - has found/detected the target
	 * false - haven't found/detected the target
	 */
	private boolean hasFoundTarget =false;


	/**
	 * Constructor.
	 *
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	public FollowBehaviour() {
	}


	private boolean biggerRangeDetect(Actor actor, GameMap map){
		// the boss knows its target is the player but the player haven't been detected yet
		if(!hasFoundTarget){
			Location here = map.locationOf(actor);
			Location there = map.locationOf(target);
			int distanceInX = Utility.distanceInX(here,there);
			int distanceInY = Utility.distanceInY(here,there);
			if(distanceInX> DETECT_RANGE || distanceInY>DETECT_RANGE){
			}else {
				hasFoundTarget =true;
			}
		}
		return hasFoundTarget;
	}

	/**
	 * detect the target actor(player) - the maximum range is 1
	 * @param actor enemies is doing the action
	 * @param map gameMap
	 * @return boolean value - true means the enemy found the target, false means the it haven't detected the player
	 */
	private boolean detection(Actor actor, GameMap map) {
		if(!hasFoundTarget){
			Location here = map.locationOf(actor);
			for (Exit exit : here.getExits()) {
				Location destination = exit.getDestination();
				if (destination.getActor() instanceof Player) {
					target = destination.getActor();
					hasFoundTarget =true;
				}
			}
		}
		return hasFoundTarget;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {

		// Skeleton and Undead haven't got their target
		// they have to detect in each turn
		detection(actor,map);

		// Because boss has got its target by passing parameter(i.e. target!=null)
		// So, before the boss check locations of the target
		// it has to check if the target and actor(boss/enemy) are in the same current map
		if(!map.contains(target) || !map.contains(actor)){
			return null;
		}


		if(actor.getWeapon() instanceof DarkmoonLongbow){
			if(!(actor.hasCapability(Abilities.FOLLOW_PLAYER))){
				if (!(biggerRangeDetect(actor,map))){
					return null;
				}
			}
		}

		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = Utility.distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = Utility.distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}


}