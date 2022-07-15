package dungeonmania.entities.collectable;

import dungeonmania.entities.moving.player.Player;

/**
 * interface for consumable behavior - Treasure, Key, Wood, Arrow
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public interface IConsumableBehavior {
    /**
     * Consumed by the player then be removed from inventory.
     * This is used for Treasure, Key, Wood, Arrow
     *
     * @param player
     */
    public void consumedByPlayer(Player player);
}
