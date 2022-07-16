package dungeonmania.entities.moving;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.buildable.Bow;
import dungeonmania.entities.buildable.Shield;
import dungeonmania.entities.collectable.CollectableEntity;
import dungeonmania.entities.collectable.Key;

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
    private List<CollectableEntity> collections;
    /**
     * Constructor for Inventory.
     */
    public Inventory() {
        this.collections = new ArrayList<CollectableEntity>();
    }
    
    /**
     * Get the inventory of the player.
     *
     * @return collections
     */
    public Inventory getInventory() {
        return this;
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
    
    /**
     * Check if the inventory contains a collectable entity.
     * @param entity
     * @return true if the inventory contains the entity, false otherwise.
     */
    public boolean contains(CollectableEntity entity) {
        return this.collections.contains(entity);
    }
    
    /**
     * Check if the inventory contains a collectable entity by type.
     * @param type
     * @return true if the inventory contains the entity, false otherwise.
     */
    public boolean containsCollectionByType(String type) {
        return this.collections.stream().anyMatch(entity -> entity.getType().equals(type));
    }

    /**
     * Check if the inventory contains a collectable entity by id.
     * @param id
     *
     * @return true if the inventory contains a collectable entity by id, false otherwise
     */
    public boolean containsCollectionById(String id) {
        return this.collections.stream()
                .anyMatch(entity -> entity.getId().equals(id));
    }
    
    /**
     * Get the first collectable entity by type.
     * @param type
     *
     * @return the first collectable entity by type
     */
    public CollectableEntity getCollectionByType(String type) {
        return this.collections.stream()
                .filter(entity -> entity.getType().equals(type))
                .findFirst()
                .orElse(null);
    }

    /**
     * Get a list of collectable entities in the collection by type.
     * @param type
     *
     * @return list of collectable entities in the collection by type
     */
    public List<CollectableEntity> getListOfCollectionsByType(String type) {
        return this.collections.stream()
                .filter(entity -> entity.getType().equals(type))
                .collect(Collectors.toList());
    }

    /**
     * Get the number of collectable entities in the collection by type.
     * @param type
     *
     * @return number of collectable entities in the collection by type
     */
    public int getNumberOfCollectionByType(String type) {
        return this.collections.stream()
                .filter(entity -> entity.getType().equals(type))
                .collect(Collectors.toList()).size();
    }
    
    /**
     * Find a collectable entity in the collection by id.
     * @param id
     *
     * @return collectable entity in the collection by id
     */
    public CollectableEntity findCollectionById(String id) {
        return this.collections.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Get the key of the collection by keyId.
     * @param keyId
     *
     * @return key of the collection by keyId
     */
    public Key getKeyByKeyId(int keyId) {
        return (Key) this.collections.stream()
                .filter(entity -> entity.getType().equals("key") && ((Key) entity).getKeyId() == keyId)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Check if the inventory has enough materials to craft a certain type of buildable entity - Shield & Bow.
     * @param type
     *
     * @return true if the inventory has enough materials, false otherwise
     */
    public boolean hasEnoughMaterialsToCraft(String type) {
        switch (type) {
            case "shield":
                return this.getNumberOfCollectionByType("wood") >= 2
                    && (this.getNumberOfCollectionByType("treasure") >= 1
                    || this.getNumberOfCollectionByType("key") >= 1);
            case "bow":
                return this.getNumberOfCollectionByType("wood") >= 1
                    && this.getNumberOfCollectionByType("arrow") >= 3;
            default:
                return false;
        }
    }
    
    /**
     * Use materials to craft a certain type of buildable entity
     * @param type
     */
    public void useMaterial(String type) {
        this.removeCollection(this.getCollectionByType(type));
    }

    /**
     * Build a certain type of buildable entity - Shield & Bow.
     * @param type
     *
     * @return true if the buildable entity is built, false otherwise
     */
    public void build(String type) {
        assert this.hasEnoughMaterialsToCraft(type);
        switch (type) {
            case "shield":
                for (int i = 0; i < 2; i++) {
                    this.removeCollection(this.getCollectionByType("wood"));
                }

                if (this.getNumberOfCollectionByType("treasure") > 0) {
                    this.removeCollection(this.getCollectionByType("treasure"));
                } else if (this.getNumberOfCollectionByType("key") > 0) {
                    this.removeCollection(this.getCollectionByType("key"));
                }
                
                Shield shield = new Shield();
                this.addCollection(shield);
                break;
            case "bow":
                this.removeCollection(this.getCollectionByType("wood"));
                for (int i = 0; i < 3; i++) {
                    this.removeCollection(this.getCollectionByType("arrow"));
                }

                Bow bow = new Bow();
                this.addCollection(bow);
                break;
            default:
                break;
        }
    }
}
