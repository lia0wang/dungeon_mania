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
    public Potion(int x, int y, String type) {
        super(x, y, type);
    }
    
    abstract public void use(Player player);
}
