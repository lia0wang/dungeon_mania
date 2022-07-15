package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.player.Player;

/**
 * class for Key.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 * @attributes - keyId (int) - the identifier of the key
 *
 */
public class Key extends CollectableEntity implements IConsumableBehavior {
    private int keyId;
    /**
     * Constructors for Key.
     */
    public Key(int x, int y, int keyId) {
        super(x, y, "key");
        this.keyId = keyId;
    }
    
    public Key(JSONObject json) {
        super(json.getInt("x"), json.getInt("y"), json.getString("type"));
        this.keyId = json.getInt("key");
    }
    
    public Key(int keyId) {
        this(0, 0, keyId);
    }
    
    /**
     * Get the keyId.
     *
     * @return keyId (int) - the identifier of the key
     */
    public int getKeyId() {
        return keyId;
    }
    
    /**
     * Add the "key": keyId pair to the JSONObject.
     *
     * @return json (JSONObject) - the JSONObject with the "key": keyId pair
     */
    @Override
    public JSONObject getJSONObject() {
        JSONObject json = super.getJSONObject();
        json.put("key", this.keyId);
        return json;
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
