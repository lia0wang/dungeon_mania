package dungeonmania.entities.buildable;

import org.json.JSONObject;

import dungeonmania.entities.collectable.CollectableEntity;
import dungeonmania.entities.collectable.IEquipmentBehavior;
import dungeonmania.entities.collectable.IWeaponBehavior;
import dungeonmania.entities.moving.Player;

/**
 * class for Bow.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 * @attributes - attackDamage (int) - the attack damage of the bow
 */
public class Bow extends CollectableEntity implements IWeaponBehavior, IEquipmentBehavior {
    private float attackDamage;

    /**
     * Constructors for Bow.
     */
    public Bow(int x, int y, String type) {
        super(x, y, type);
        this.setDurability(3);
        this.setAttackDamage(2);
    }
    
    public Bow(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Bow() {
        this(0, 0, "bow");
    }
    
    public Bow(int x, int y) {
        this(x, y, "bow");
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
    
    @Override
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    
    @Override
    public float getAttackDamage() {
        return attackDamage;
    }
}