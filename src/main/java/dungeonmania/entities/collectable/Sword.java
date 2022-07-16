package dungeonmania.entities.collectable;

import org.json.JSONObject;

import dungeonmania.entities.moving.Player;

/**
 * class for Sword.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Sword extends CollectableEntity implements IEquipmentBehavior, IWeaponBehavior {
    private float attackDamage;

    /**
     * Constructors for Sword.
     */
    public Sword(int x, int y, String type) {
        super(x, y, type);
        this.setDurability(3);
        this.setAttackDamage(1);
    }
    
    public Sword(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Sword() {
        this(0, 0, "sword");
    }
    
    public Sword(int x, int y) {
        this(x, y, "sword");
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