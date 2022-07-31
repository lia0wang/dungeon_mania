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
    public InvisibilityPotion(int x, int y, String type, int effectLasting) {
        super(x, y, type, effectLasting);
    }
    
    public InvisibilityPotion(JSONObject config, JSONObject dungeon) {
        this(dungeon.getInt("x"), dungeon.getInt("y"), dungeon.getString("type"), config.getInt("invisibility_potion_duration"));
    }

    /* 
    public InvisibilityPotion(int x, int y) {
        this(x, y, "invisibility_potion");
    }
    
    public InvisibilityPotion() {
        this(0, 0, "invisibility_potion");
    }
    */
    
    /**
     * Override Methods
     */
    @Override
    public void use(Player player) {
        player.getInventory().removeCollection(this);
        // todo: if already in a effect of a potion, this is queued
        //PlayerState currentState = player.getPlayerState();
        player.getPlayerState().becomeInvisible(player, this.getEffectDuration());
    }
}
