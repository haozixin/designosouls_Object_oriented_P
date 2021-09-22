package game.terrains;

import edu.monash.fit2099.engine.*;
import game.CemeteryHelper;
import game.actors.Player;
import game.actors.Undead;
import game.enums.Abilities;

import java.util.Random;

public class Cemetery extends Ground {
    public static final char CEMETERY_CHAR = 'c';
    private int successRate;
    private CemeteryHelper cemeteryHelper;


    public Cemetery() {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        successRate=25;
    }

    public Cemetery(CemeteryHelper cemeteryHelper) {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        successRate=25;
        this.cemeteryHelper = cemeteryHelper;
    }

    private void createUndead(Location location){
        Random r = new Random();
        Actor player = cemeteryHelper.getPlayer();
        if (r.nextInt(100)<successRate){
            cemeteryHelper.getGameMap().at(location.x(), location.y()).addActor(new Undead("Undead",player));
        }

    }



    @Override
    public void tick(Location location) {
        createUndead(location);

    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

}
