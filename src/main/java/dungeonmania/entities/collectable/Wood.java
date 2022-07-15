package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.Player;

/**
 * class for Wood.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Wood extends CollectableEntity implements IConsumableBehavior {
    /**
     * Constructors for Wood.
     */
    public Wood(int x, int y, String type) {
        super(x, y, type);
    }
    
    public Wood(JSONObject json) {
        super(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Wood() {
        this(0, 0, "wood");
    }

    public Wood(int x, int y) {
        this(x, y, "wood");
    }

    /**
     * Consumed by the player then be removed from inventory.
     * This is used for Treasure, Key, Wood, Arrow
     * 
     * @param player
     */
    @Override
    public void consumedByPlayer(Player player) {
        player.getInventory().removeCollection(this);
    }
}