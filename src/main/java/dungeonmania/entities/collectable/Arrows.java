package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.player.Player;

/**
 * class for Arrows.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Arrows extends CollectableEntity implements IConsumableBehavior {
    /**
     * Constructors for Arrows.
     */
    public Arrows(int x, int y, String type) {
        super(x, y, type);
    }
    
    public Arrows(JSONObject json) {
        super(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Arrows() {
        this(0, 0, "arrow");
    }

    public Arrows(int x, int y) {
        this(x, y, "arrow");
    }

    /**
     * Consumed by the player then be removed from inventory.
     * This is used for Treasure, Key, Wood, Arrows
     * 
     * @param player
     */
    @Override
    public void consumedByPlayer(Player player) {
        player.getInventory().removeCollection(this);
    }
}