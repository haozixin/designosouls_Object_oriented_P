package game.terrains;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.Player;
import game.weapons.YhormGreatMachete;

public class BurnedArea extends Ground {
    /**
     * The duration of the flame
     */
    private int burningTime;

    /**
     * Constructor.
     */
    public BurnedArea(int burningTime) {
        super('v');
        this.burningTime = burningTime;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }


    @Override
    public void tick(Location location) {
        --burningTime;
        if(burningTime<0){
            // ground become back to dirt if the burning time is out
            location.setGround(new Dirt());
        }
        super.tick(location);
        // actor is on the burned area but without holding that weapon
        // because the actor who is holding the weapon will not get the damage from the passive skill
        if (location.containsAnActor() && !(location.getActor().getWeapon() instanceof YhormGreatMachete)) {
            int hurtPoint = 25;
            location.getActor().hurt(hurtPoint);
        }
    }
}
