package dungeonmania.entities.collectable;

/**
 * abstract class for Potion.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 * @attributes - keyId (int) - the identifier of the key
 *
 */
public abstract class Potion extends CollectableEntity {
    public Potion(int x, int y, String type) {
        super(x, y, type);
    }
}
