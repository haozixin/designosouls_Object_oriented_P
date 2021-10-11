package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)

    //HOSTILE_TO_PLAYER, // for player can be attacked by enemies

    LIGHTED, // for A3 - the new Bonfire

    RANGED_WEAPON, // for Darkmoon Longbow weapon's passive skill

    WITHIN_ATTACK_RANGE,

    LOCKED, // for the ambiguous enemy, it will become enemy or token of souls after it is opened

    FROM_YHORM, // has this tag means the cinders of lord is from Yhorm
    FROM_ALDRICH, // has this tag means the cinders of lord is from Aldrich
    ONLY_FOR_YHORM, // has this tag means the weapon can only used by Yhorm
    RAGE_MODE,
    SECOND_PHASE
}
