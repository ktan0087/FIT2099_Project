package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    HOSTILE_TO_PLAYER, // can be attack by player
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE,
    FERTILE,
    UNDROPPABLE,
    CAN_SMASH_KOOPA_SHELL,
    DORMANT,
    AGGRO, //aggressive behaviour
    RESET
}
