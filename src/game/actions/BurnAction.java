package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BurnAction extends Action {
	private Actor target;

	public BurnAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		target.hurt(25);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

}
