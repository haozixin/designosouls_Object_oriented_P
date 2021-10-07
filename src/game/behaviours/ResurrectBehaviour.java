package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.ResurgenceAction;
import game.actors.Skeleton;
import game.enums.Abilities;
import game.interfaces.Behaviour;

/**
 * A class that figures out a MoveAction that will resurrect the skeleton(since the skeleton has the ability and behaviour)
 */
public class ResurrectBehaviour extends Action implements Behaviour {

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    /**
     * if the skeleton is conscious and has the ability of RESURRECT
     * it will return the ResurgenceAction
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action to be executed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Skeleton ? true:false){
            if (!actor.isConscious() && actor.hasCapability(Abilities.RESURRECT)){
                return new ResurgenceAction((Skeleton)actor);
            }
        }
        return null;
    }
}
