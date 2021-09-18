package game.Terrains;

import edu.monash.fit2099.engine.*;
import game.Generator;
import game.Undead;
import game.enums.Abilities;

public class Cemetery extends Ground {
    public static final char CEMETERY_CHAR = 'c';
    private int successRate;
    //private GameMap gameMap;
    private Generator generator;


    public Cemetery() {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        successRate=25;
    }

    public Cemetery(Generator generator) {
        super(CEMETERY_CHAR);
        addCapability(Abilities.CREATE_UNDEAD);
        successRate=25;
        this.generator = generator;
    }

    private void createUndead(Location location){
        generator.getGameMap().at(location.x()+1, location.y()+1).addActor(new Undead("Undead"));
    }



    @Override
    public void tick(Location location) {
        createUndead(location);

    }

}
