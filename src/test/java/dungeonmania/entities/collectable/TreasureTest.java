package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;

public class TreasureTest {
    
    @Test
    public void testTreasureConstructor() {
        Treasure treasure = new Treasure(10, 20);
        JSONObject json = treasure.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("treasure", json.getString("type"));
        
        Treasure treasure2 = new Treasure(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"treasure\"}"));
        JSONObject json2 = treasure2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("treasure", json2.getString("type"));
    }
    
    @Test
    public void testTreasureConsumedByPlayer() {
        Treasure treasure = new Treasure(10, 20);
        Player player = new Player(0, 0, null);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(treasure); // Simulate the treasure is added to the Dungeon
        
        treasure.collectedByPlayer(player, entities);
        assert(!entities.contains(treasure)); // Check the treasure is removed from the Dungeon
        assert(player.getInventory().contains(treasure)); // Check the treasure is added to the player's inventory
        
        treasure.consumedByPlayer(player);
        
        // Check the treasure is non-existent in the Dungeon and the player's inventory
        assert(!player.getInventory().contains(treasure));
        assert(entities.contains(treasure) == false);
    }
}