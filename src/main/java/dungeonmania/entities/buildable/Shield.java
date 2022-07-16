package dungeonmania.entities.buildable;

import org.json.JSONObject;

import dungeonmania.entities.collectable.CollectableEntity;
import dungeonmania.entities.collectable.IEquipmentBehavior;
import dungeonmania.entities.moving.Player;

/**
 * class for Shield.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Shield extends CollectableEntity implements IEquipmentBehavior {
    private float defense;

    /**
     * Constructors for Shield.
     */
    public Shield(int x, int y, String type) {
        super(x, y, type);
        this.setDurability(3);
        this.defense = 2;
    }
    
    public Shield(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Shield() {
        this(0, 0, "shield");
    }
    
    public Shield(int x, int y) {
        this(x, y, "shield");
    }    
    
    /**
     * Get the defense value.
     *
     * @return defense (float) - the defense of the shield
     */
    public float getDefenseValue() {
        return defense;
    }
    
    /**
     * Override Methods
     */
    @Override
    public void usedInBattle(Player player) {
        this.decreaseDurability();
        if (this.isBroken()) {
            player.getInventory().removeCollection(this);
        }
    }

    @Override
    public boolean isBroken() {
        return this.getDurability() == 0;
    }
}