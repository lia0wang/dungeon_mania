package dungeonmania.entities.collectable;

import dungeonmania.entities.moving.player.Player;

/**
 * interface for weapon behavior - Sword, Shield, Bow.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public interface IWeaponBehavior {
    /**
     * Used by the player then it is removed from the inventory if the durability is 0.
     * This is used for Sword, Bow, Shield
     */
    public void usedInBattle(Player player);
}
