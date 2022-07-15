package dungeonmania.entities.collectable;

import java.util.ArrayList;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.player.Player;

/**
 * interface for bomb behavior.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public interface IBombBehavior {
    /**
     * Dropped by the player then be added to the entity arraylist.
     * This is used for Bomb
     *
     * @param player
     * @param entities
     */
    public void droppedByPlayer(Player player, ArrayList<Entity> entities);
}
