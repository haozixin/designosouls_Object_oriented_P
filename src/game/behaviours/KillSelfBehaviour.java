package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.KillSelfAction;
import game.interfaces.Behaviour;

import java.util.Random;

import static game.actors.Undead.CHANCE_TO_DIE;

public class KillSelfBehaviour extends Action implements Behaviour {


    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" kills itself!!";
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random r = new Random();
        if(r.nextInt(100)<=CHANCE_TO_DIE && map.contains(actor)){
            return new KillSelfAction();
        }
        return null;

    }
}
