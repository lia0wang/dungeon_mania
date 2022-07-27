package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import dungeonmania.entities.buildable.Shield;
import dungeonmania.entities.moving.Player;

public class ShieldTest {
    
    @Test
    public void testShieldConstructor() {
        Shield shield = new Shield(10, 20);
        JSONObject json = shield.getJSONObject();
        
        assertEquals(10, json.getInt("x"));
        assertEquals(20, json.getInt("y"));
        assertEquals("shield", json.getString("type"));
        
        Shield shield2 = new Shield(new JSONObject("{\"x\":5,\"y\":6,\"type\":\"shield\"}"));
        JSONObject json2 = shield2.getJSONObject();
        
        assertEquals(5, json2.getInt("x"));
        assertEquals(6, json2.getInt("y"));
        assertEquals("shield", json2.getString("type"));
    }
    
    @Test
    public void testBuildShieldThenUsedInBattle() {
        Player player = new Player(0, 0, null);
        Wood wood = new Wood(1, 2);
        Wood wood2 = new Wood(2, 3);        
        Treasure treasure = new Treasure(3, 4);
        Key key = new Key(4, 5, 1);

        player.getInventory().addCollection(wood); // get materials to build the shield
        player.getInventory().addCollection(wood2);
        player.getInventory().addCollection(treasure);
        player.getInventory().addCollection(key);
        
        assert(!player.getInventory().containsCollectionByType("shield")); // check the shield is non-existent
        player.getInventory().build("shield"); // build the shield
        assert(player.getInventory().containsCollectionByType("shield")); // check the shield is in the player's inventory
        
        assert(player.getInventory().getNumberOfCollectionByType("wood") == 0); // check materials are used up
        assert(player.getInventory().getNumberOfCollectionByType("treasure") == 0);
        assert(player.getInventory().getNumberOfCollectionByType("key") == 1);
        
        Shield shield = (Shield) player.getInventory().getCollectionByType("shield");
        assert(shield.getDefenseValue() == 2); 
        assert(shield.getDurability() == 3);
        
        shield.usedInBattle(player);
        shield.usedInBattle(player);
        shield.usedInBattle(player);
        
        assert(shield.isBroken());

        Wood wood3 = new Wood(5, 6);
        Wood wood4 = new Wood(6, 7);
        
        player.getInventory().addCollection(wood3); // get materials to build the shield again
        player.getInventory().addCollection(wood4);
        
        player.getInventory().build("shield"); // build the shield
        
        assert(player.getInventory().getNumberOfCollectionByType("wood") == 0); // check materials are used up
        assert(player.getInventory().getNumberOfCollectionByType("key") == 0);
    }
}
