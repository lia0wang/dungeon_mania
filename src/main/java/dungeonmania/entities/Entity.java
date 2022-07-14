package dungeonmania.entities;

import dungeonmania.util.Position;
import dungeonmania.response.models.EntityResponse;

import org.json.JSONObject;

/**
 * Base class for all entities.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 2.0
 *
 * @attributes - id (String)                - unique id of the entity
 *             - type (String)              - type of the entity
 *             - position (Position)        - position of the entity
 *             - isInteractable (boolean)   - whether the entity is interactable (only pertains to mercenaries and zombie toast spawners)
 *
 */
public class Entity {
    private String id;
    private String type;
    private Position position;
    private boolean isInteractable;
    
    /**
     * Constructor for Entity - See 4.1 Input Specification - Entities.
     *
     * @param x
     * @param y
     * @param type
     */
    public Entity(int x, int y, String type) {
        this.position = new Position(x, y);
        this.type = type;
        this.id = generateId(type, this.position);
        this.isInteractable = false;
    }
    
    /**
     * Constructor for Entity - ItemResponse.
     *
     * @param id
     * @param type
     */
    public Entity(String id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Get the id of the entity.
     * @return id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Set the id of the entity.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Get the type of the entity.
     * @return type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Set the type of the entity.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Get the position of the entity.
     * @return position
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * Set the position of the entity.
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    
    /**
     * Get the isInteractable of the entity.
     * @return isInteractable
     */
    public boolean isInteractable() {
        return isInteractable;
    }
    
    /**
     * Set the isInteractable of the entity.
     * @param isInteractable
     */
    public void setInteractable(boolean isInteractable) {
        this.isInteractable = isInteractable;
    }

    /**
     * Get the EntityResponse of the entity.
     * @return EntityResponse
     */
    public EntityResponse getEntityResponse() {
        return new EntityResponse(this.id, this.type, this.position, this.isInteractable);
    }
    
    /**
     * Get the JSONObject of the entity.
     * @return JSONObject
     */
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("type", this.type);
        jsonObject.put("x", this.position.getX());
        jsonObject.put("y", this.position.getY());
        jsonObject.put("layer", this.position.getLayer());
        jsonObject.put("isInteractable", this.isInteractable);
        return jsonObject;
    }
    
    /**
     * Check if the entity is at the same position as the given entity.
     * @param entity
     * @return boolean
     */
    public boolean isAtSamePosition(Entity entity) {
        return this.getPosition().equals(entity.getPosition());
    }
    
    /**
     * Generate an unique id for the entity.
     * @param type (String) - type of the entity
     * @param position (Position) - position of the entity
     * @return id
     */
    private String generateId(String type, Position position) {
        return type + "_" + Integer.toString(position.getX()) + "_" + Integer.toString(position.getY()) + "_" + Integer.toString(position.getLayer());
    }
    
    /**
     * Override the toString method.
     */
    @Override
    public String toString() {
        return "Entity [id=" + id + ", type=" + type + ", position=" + position + ", isInteractable=" + isInteractable + "]";
    }
    
    /**
     * Override the equals method.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entity other = (Entity) obj;
        return this.id.equals(other.id);
    }

}
