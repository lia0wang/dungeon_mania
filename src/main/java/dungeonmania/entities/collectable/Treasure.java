package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.Player;

/**
 * class for Treasure.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Treasure extends CollectableEntity implements IConsumableBehavior {
    /**
     * Constructors for Treasure.
     */
    public Treasure(int x, int y, String type) {
        super(x, y, type);
    }
    
    public Treasure(JSONObject json) {
        super(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Treasure() {
        this(0, 0, "treasure");
    }

    public Treasure(int x, int y) {
        this(x, y, "treasure");
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
