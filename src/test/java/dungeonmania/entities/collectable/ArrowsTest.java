package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;

public class ArrowsTest {
    
    @Test
    public void testArrowsConstructor() {
        Arrows arrows = new Arrows(10, 20);
        JSONObject json = arrows.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("arrow", json.getString("type"));
        
        Arrows arrows2 = new Arrows(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"arrow\"}"));
        JSONObject json2 = arrows2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("arrow", json2.getString("type"));
    }
    
    @Test
    public void testArrowsConsumedByPlayer() {
        Arrows arrows = new Arrows(10, 20);
        Player player = new Player(0, 0, null);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(arrows); // Simulate the arrow is added to the Dungeon
        
        arrows.collectedByPlayer(player, entities);
        assert(!entities.contains(arrows)); // Check the arrow is removed from the Dungeon
        assert(player.getInventory().contains(arrows)); // Check the arrow is added to the player's inventory
        
        arrows.consumedByPlayer(player);
        
        // Check the arrow is non-existent in the Dungeon and the player's inventory
        assert(!player.getInventory().contains(arrows));
        assert(entities.contains(arrows) == false);
    }
}