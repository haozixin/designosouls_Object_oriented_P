package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.actors.Player;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	// only player could pass this kind of ground so that only player can enter Bonfire
	@Override
	public boolean canActorEnter(Actor actor) {
		if(actor instanceof Player ? true:false){
			return super.canActorEnter(actor);
		}else{
			return false;
		}
	}
}
