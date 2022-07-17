package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.Player;

/**
 * class for invisibility potion.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class InvisibilityPotion extends Potion {

    /**
     * Constructors for InvisibilityPotion.
     */
    public InvisibilityPotion(int x, int y, String type) {
        super(x, y, type);
    }
    
    public InvisibilityPotion(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }

    public InvisibilityPotion(int x, int y) {
        this(x, y, "invisibility_potion");
    }
    
    public InvisibilityPotion() {
        this(0, 0, "invisibility_potion");
    }
    
    /**
     * Override Methods
     */
    @Override
    public void use(Player player) {
        // TODO
    }
}
