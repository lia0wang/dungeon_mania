package dungeonmania.entities.collectable;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONObject;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;

/**
 * class for Bomb.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public class Bomb extends CollectableEntity implements IBombBehavior {
    private final int RADIUS = 1;
    
    public Bomb(int x, int y, String type) {
        super(x, y, type);
    }
    
    public Bomb(JSONObject json) {
        this(json.getInt("x"), json.getInt("y"), json.getString("type"));
    }
    
    public Bomb() {
        this(0, 0, "bomb");
    }
    
    @Override
    public void droppedByPlayer(Player player, ArrayList<Entity> entities) {
        player.getInventory().removeCollection(this);
        entities.add(this);
    }
}