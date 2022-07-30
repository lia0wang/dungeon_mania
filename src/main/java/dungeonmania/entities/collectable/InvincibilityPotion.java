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
    public InvincibilityPotion(int x, int y, String type, int effectLasting) {
        super(x, y, type, effectLasting);
    }
    
    public InvincibilityPotion(JSONObject config, JSONObject dungeon) {
        this(dungeon.getInt("x"), dungeon.getInt("y"), dungeon.getString("type"), config.getInt("invisibility_potion_duration"));
    }

    /* 
    public InvincibilityPotion(int x, int y) {
        this(x, y, "invincibility_potion");
    }
    
    public InvincibilityPotion() {
        this(0, 0, "invincibility_potion");
    }
    */
    
    /**
     * Override Methods
     */
    @Override
    public void use(Player player) {
        player.getInventory().removeCollection(this);
        player.getPlayerState().becomeInvincible(player, this.getEffectDuration());
    }
}
