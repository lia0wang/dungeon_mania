package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.Player;

/**
 * class for invincibility potion.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class InvincibilityPotion extends Potion {

    /**
     * Constructors for InvincibilityPotion.
     */
    public InvincibilityPotion(int x, int y, String type) {
        super(x, y, type);
    }
    
    public InvincibilityPotion(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }

    public InvincibilityPotion(int x, int y) {
        this(x, y, "invincibility_potion");
    }
    
    public InvincibilityPotion() {
        this(0, 0, "invincibility_potion");
    }
    
    /**
     * Override Methods
     */
    @Override
    public void use(Player player) {
        // TODO
    }
}
