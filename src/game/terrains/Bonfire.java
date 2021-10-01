package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.ActivateBonfireAction;
import game.actions.RestAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.BonfireInterface;

/**
 * A class that represents Bonfire.
 */
public class Bonfire extends Ground implements BonfireInterface {
    private String name;

    public Bonfire() {
        super('B');
    }

    public Bonfire(String name) {
        super('B');
        this.name = name;
    }

    /**
     * Constructor
     */
    public Bonfire(Status status,String name) {
        super('B');
        addCapability(status);
        this.name = name;
        System.out.println("-----------------------"+this.name);
    }

    /**
     * Returns an Action list. (allowable actions that actors can do with the kind of ground)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions to be executed
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if(this.hasCapability(Status.LIGHTED)){
            actions.add(new ActivateBonfireAction(this));
        }else{
            if (actor.hasCapability(Abilities.REST)){
                actions.add(new RestAction(this));
            }
        }

        return actions;
    }

    public String getName() {

        System.out.println("------------------------------------------------"+name);
        return name;
    }
}
