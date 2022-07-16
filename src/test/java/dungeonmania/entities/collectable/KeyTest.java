package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;

public class KeyTest {
    
    @Test
    public void testKeyConstructor() {
        Key key = new Key(10, 20, 1);
        JSONObject json = key.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("key", json.getString("type"));
        assertEquals(1, json.getInt("key"));
        
        Key key2 = new Key(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"key\",\"key\":2}"));
        JSONObject json2 = key2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("key", json2.getString("type"));
        assertEquals(2, json2.getInt("key"));
    }
    
    @Test
    public void testKeyConsumedByPlayer() {
        Key key = new Key(10, 20, 3);
        Player player = new Player(0, 0);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(key); // Simulate the key is added to the Dungeon
        
        key.collectedByPlayer(player, entities);
        assert(!entities.contains(key)); // Check the key is removed from the Dungeon
        assert(player.getInventory().contains(key)); // Check the key is added to the player's inventory
        
        key.consumedByPlayer(player);
        
        // Check the key is non-existent in the Dungeon and the player's inventory
        assert(!player.getInventory().contains(key));
        assert(entities.contains(key) == false);
    }
}