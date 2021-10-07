package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.interfaces.Behaviour;

public class SoftResetAction extends Action{
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(map,actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been reset!";
    }

}
