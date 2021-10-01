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

import static game.Application.FIRELINK_SHRINE_BONFIRE;

/**
 * A class that represents Bonfire.
 */
public class Bonfire extends Ground implements BonfireInterface {
    private String name;
    /**
     * temp name
     */
    static String tempName;


    /**
     * Each instance created in FancyGroundFactory will used default constructor finally
     * So, in order to pass in parameters, we have to build another constructor to same a temp parameter value.
     */
    public Bonfire() {
        super('B');
        name = tempName;
        initializeStatus();

    }

    /**
     * Constructor - with parameter
     * @param name
     */
    public Bonfire(String name){
        super('B');
        tempName = name;
    }

    private void initializeStatus(){
        if (name == FIRELINK_SHRINE_BONFIRE){
            this.addCapability(Status.LIGHTED);
        }
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

        if(!this.hasCapability(Status.LIGHTED)){
            actions.add(new ActivateBonfireAction(this));
        }else{
            if (actor.hasCapability(Abilities.REST)){
                actions.add(new RestAction(this));
            }
        }

        return actions;
    }

    public String getName() {
        return name;
    }

}
