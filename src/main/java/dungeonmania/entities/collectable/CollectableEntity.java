package dungeonmania.entities.collectable;

import java.util.ArrayList;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;
import dungeonmania.response.models.ItemResponse;

/**
 * class for collectable entities.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 * @attributes - durability (int) - durability of the entity
 *
 */
public class CollectableEntity extends Entity {
    private int durability;
    
    /**
     * Constructor for CollectableEntity.
     * 
     * @param x
     * @param y
     * @param type
     */
    public CollectableEntity(int x, int y, String type) {
        super(x, y, type);
    }

    /**
     * Constructor for CollectableEntity - ItemResponse.
     *
     * @param id
     * @param type
     */
    public CollectableEntity(String id, String type) {
        super(id, type);
    }
    
    /**
     * Get the durability of the entity.
     *
     * @return durability
     */
    public int getDurability() {
        return durability;
    }
    
    /**
     * Set the durability of the entity.
     *
     * @param durability
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }
    
    /**
     * Be collected by the player then be removed from the entity arraylist.
     *
     * @param player
     * @param entities
     */
    public void collectedByPlayer(Player player, ArrayList<Entity> entities) {
        player.getInventory().addCollection(this);
        entities.remove(this);
    }

    /**
     * Create the ItemResponse of the entity.
     * @return ItemResponse
     */
    public ItemResponse getItemResponse() {
        return new ItemResponse(this.getId(), this.getType());
    }
}