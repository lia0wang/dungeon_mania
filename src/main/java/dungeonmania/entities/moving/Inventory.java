package dungeonmania.entities.moving;

import java.util.ArrayList;

import dungeonmania.entities.collectable.CollectableEntity;

/**
 * class for inventory of player.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 * @attributes - collections (ArrayList<CollectableEntity>) - collection of collectable entities
 *
 */
public class Inventory {
    private ArrayList<CollectableEntity> collections;
    
    /**
     * Constructor for Inventory.
     */
    public Inventory() {
        this.collections = new ArrayList<CollectableEntity>();
    }
    
    /**
     * Add a collectable entity to the collection.
     * @param entity
     */
    public void addCollection(CollectableEntity entity) {
        this.collections.add(entity);
    }
    
    /**
     * Remove a collectable entity from the collection.
     * @param entity
     */
    public void removeCollection(CollectableEntity entity) {
        this.collections.remove(entity);
    }
}
