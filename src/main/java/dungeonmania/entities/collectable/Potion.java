package dungeonmania.entities.collectable;

import dungeonmania.entities.moving.Player;

/**
 * abstract class for Potion.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public abstract class Potion extends CollectableEntity {
    private int effectLasting;

    public Potion(int x, int y, String type, int effectLasting) {
        super(x, y, type);
        this.effectLasting = effectLasting;
    }
    
    public int getEffectDuration() {
        return this.effectLasting;
    }

    public void setEffectDuration(int effectLasting) {
        this.effectLasting = effectLasting;
    }

    abstract public void use(Player player);
}
