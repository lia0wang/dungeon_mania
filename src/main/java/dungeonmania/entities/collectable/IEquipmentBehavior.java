package dungeonmania.entities.collectable;

import dungeonmania.entities.moving.Player;

/**
 * interface for equipment behavior - Sword, Shield, Bow.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public interface IEquipmentBehavior {
    /**
     * Used by the player then it is removed from the inventory if the durability is 0.
     * This is used for Sword, Bow, Shield
     */
    public void usedInBattle(Player player);
    
    /**
     * Check if the equipment is broken.
     *
     * @return true if the equipment is broken, false otherwise.
     */
    public boolean isBroken();
}
