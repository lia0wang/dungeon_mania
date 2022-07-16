package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.Entity;

import dungeonmania.entities.moving.Player;

public class SwordTest {

    @Test
    public void testSwordConstructor() {
        Sword sword = new Sword();
        JSONObject json = sword.getJSONObject();
        
        assertEquals(0, json.getInt("x"));
        assertEquals(0, json.getInt("y"));
        assertEquals("sword", json.getString("type"));
        assert(sword.getDurability() == 3);
        assert(sword.getAttackDamage() == 1);
        
        JSONObject inputJsonObject = new JSONObject("{\"x\":5,\"y\":6,\"type\":\"sword\"}");
        Sword sword2 = new Sword(inputJsonObject);
        JSONObject json2 = sword2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("sword", json2.getString("type"));
        assert(sword2.getDurability() == 3);
        assert(sword2.getAttackDamage() == 1);
    }
    
    @Test
    public void testSwordConsumedByPlayer() {
        Sword sword = new Sword();
        Player player = new Player(0, 0);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(sword); // Simulate the sword is added to the Dungeon
    
        sword.collectedByPlayer(player, entities);
        assert(sword.getDurability() == 3); // Check the initial durability of the sword
        assert(!entities.contains(sword)); // Check the sword is removed from the Dungeon
        assert(player.getInventory().contains(sword)); // Check the sword is added to the player's inventory

        sword.usedInBattle(player);
        assert(sword.getDurability() == 2);
        sword.usedInBattle(player);
        sword.usedInBattle(player); // The durability of the sword is 0 now
        
        // Check the sword is non-existent in the Dungeon and the player's inventory
        assert(!player.getInventory().contains(sword));
        assert(entities.contains(sword) == false);
    }
    
}