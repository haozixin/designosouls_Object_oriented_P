package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.SoftResetAction;
import game.interfaces.Behaviour;

public class SoftResetBehaviour extends Action implements Behaviour {
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new SoftResetAction();
    }
}
