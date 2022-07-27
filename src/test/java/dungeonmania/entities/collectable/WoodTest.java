package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;

public class WoodTest {
    
    @Test
    public void testWoodConstructor() {
        Wood wood = new Wood(10, 20);
        JSONObject json = wood.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("wood", json.getString("type"));
        
        Wood wood2 = new Wood(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"wood\"}"));
        JSONObject json2 = wood2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("wood", json2.getString("type"));
    }
    
    @Test
    public void testWoodConsumedByPlayer() {
        Wood wood = new Wood(10, 20);
        Player player = new Player(0, 0, null);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(wood); // Simulate the wood is added to the Dungeon
        
        wood.collectedByPlayer(player, entities);
        assert(!entities.contains(wood)); // Check the wood is removed from the Dungeon
        assert(player.getInventory().contains(wood)); // Check the wood is added to the player's inventory
        
        wood.consumedByPlayer(player);
        
        // Check the wood is non-existent in the Dungeon and the player's inventory
        assert(!player.getInventory().contains(wood));
        assert(entities.contains(wood) == false);
    }
}