package game.enums;

/**
 * Enum that represents an ability of Actor, Item, or Ground.
 */
public enum Abilities {
    REST, //for player to do the restAction
    VALLEY_DAMAGE, // for valley
    CREATE_UNDEAD, // for cemetery
    DEAL, // for vendor and Player
    FOLLOWED, // for player
    RESURRECT, //for skeleton's skill
    EMBER_FORM, //for Lord of Cinder
    CHANCE_TO_DIE, // for undead, it has a 10% chance to die instantly
    BIGGER_DETECT_RANGE, //for the Darkmoon Longbow weapon, by holding it, actor can attack enemy that within 3(or other) squares away
    FOLLOW_PLAYER,
    BURN_GROUND
}
