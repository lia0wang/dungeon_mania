package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.buildable.Bow;
import dungeonmania.entities.moving.Player;

public class BowTest {
    
    @Test
    public void testBowConstructor() {
        Bow bow = new Bow(10, 20);
        JSONObject json = bow.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("bow", json.getString("type"));
        
        Bow bow2 = new Bow(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"bow\"}"));
        JSONObject json2 = bow2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("bow", json2.getString("type"));
    }
    
    @Test
    public void testBuildBowThenUsedInBattle() {
        Player player = new Player(0, 0, null);
        Wood wood = new Wood(1, 2);
        Arrows arrows1 = new Arrows(2, 3);
        Arrows arrows2 = new Arrows(3, 4);
        Arrows arrows3 = new Arrows(4, 5);
        
        player.getInventory().addCollection(wood); // get materials to build the bow
        player.getInventory().addCollection(arrows1);
        player.getInventory().addCollection(arrows2);
        player.getInventory().addCollection(arrows3);
        
        assert(!player.getInventory().containsCollectionByType("bow")); // check the bow is non-existent
        player.getInventory().build("bow"); // build the bow
        assert(player.getInventory().containsCollectionByType("bow")); // check the bow is in the player's inventory
        
        assert(player.getInventory().getNumberOfCollectionByType("wood") == 0); // check materials are used up
        assert(player.getInventory().getNumberOfCollectionByType("arrow") == 0);
        
        Bow bow = (Bow) player.getInventory().getCollectionByType("bow"); // get the bow
        assert(bow.getAttackDamage() == 2); 
        assert(bow.getDurability() == 3);
        
        bow.usedInBattle(player);
        bow.usedInBattle(player);
        bow.usedInBattle(player);
        
        assert(bow.isBroken());
    }
}
