package dungeonmania.entities.collectable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Player;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

import static dungeonmania.TestUtils.getInventory;



public class PotionTest {
    @Test
    public void testInvincibilityPotionPickUpAndConsumed() {
        InvincibilityPotion potion  = new InvincibilityPotion(10, 20, "invincibility_potion", 1);
        Player player = new Player(0, 0, null);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(potion); // Simulate the key is added to the Dungeon
        
        potion.collectedByPlayer(player, entities);
        assert(!entities.contains(potion)); 
        assert(player.getInventory().contains(potion)); 
        
        // Check the potion is no longer exist in the Dungeon and the inventory
        potion.use(player);
        assert(!player.getInventory().contains(potion));
        assert(entities.contains(potion) == false);
        assertEquals(true, player.isInvincible());

    }

    @Test
    public void testInvisibilityPotionPickUpAndConsumed() {
        InvisibilityPotion potion  = new InvisibilityPotion(10, 20, "invisibility_potion", 1);
        Player player = new Player(0, 0, null);
        ArrayList<Entity> entities = new ArrayList<Entity>();
        entities.add(potion); // Simulate the key is added to the Dungeon
        
        potion.collectedByPlayer(player, entities);
        assert(!entities.contains(potion)); 
        assert(player.getInventory().contains(potion)); 
        
        // Check the potion is no longer exist in the Dungeon and the inventory
        potion.use(player);
        assert(!player.getInventory().contains(potion));
        assert(entities.contains(potion) == false);
        assertEquals(true, player.isInvisible());

    }

    @Test
    void testPotionLastForLimitedTime() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_potionTest_PickUpAndConsume", "c_potionTest_effectLastForLimitedTime");

        // pick up invincibility potion
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "invincibility_potion").size());
        
        Dungeon d = dmc.getDungeon();
        Player p = d.getPlayer();
        assertEquals(false, p.isInvincible());

        // this potion only last for 1 tick
        String InvinciblePotionId = getInventory(res, "invincibility_potion").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.tick(InvinciblePotionId));
        assertEquals(0, getInventory(res, "invincibility_potion").size());
        assertEquals(true, p.isInvincible());

        res = dmc.tick(Direction.DOWN);
        assertEquals(false, p.isInvincible());

        // pick up invisibility potion
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "invisibility_potion").size());
        assertEquals(false, p.isInvisible());

        // this potion only last for 2 ticks
        String InvisiblePotionId = getInventory(res, "invisibility_potion").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.tick(InvisiblePotionId));
        assertEquals(0, getInventory(res, "invisibility_potion").size());
        assertEquals(true, p.isInvisible());

        res = dmc.tick(Direction.DOWN);
        assertEquals(true, p.isInvisible());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(false, p.isInvisible());
    }

    @Test
    void testSecondPotionQueued() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_potionTest_EffectQueued", "c_potionTest_effectLastForLimitedTime");

        // consume invincibility potion that last for 3 ticks, 0, 1, 2
        // consume invisibility potion at tick 1 that last for 2 ticks
        // the player will become invisible after tick 2, ie: tick 3
        // invisible for 3, 4
        // then become default at tick 5

        // pick up invincible potion
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "invincibility_potion").size());
        
        // pick up invisible potion
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, getInventory(res, "invisibility_potion").size());

        Dungeon d = dmc.getDungeon();
        Player p = d.getPlayer();

        // use invincible potion that lasts for 3 ticks
        String InvinciblePotionId = getInventory(res, "invincibility_potion").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.tick(InvinciblePotionId));
        assertEquals(0, getInventory(res, "invincibility_potion").size());
        assertEquals(true, p.isInvincible());

        // use invisible potion that lasts for 2 ticks, but this effect is queued
        String InvisiblePotionId = getInventory(res, "invisibility_potion").get(0).getId();
        res = assertDoesNotThrow(() -> dmc.tick(InvisiblePotionId));
        assertEquals(0, getInventory(res, "invisibility_potion").size());
        assertEquals(true, p.isInvincible());
        assertEquals(false, p.isInvisible());

        res = dmc.tick(Direction.DOWN);
        assertEquals(true, p.isInvincible());

        // invisible came into effect after the invincible worn out
        res = dmc.tick(Direction.RIGHT);
        assertEquals(false, p.isInvincible());
        assertEquals(true, p.isInvisible());
        
        res = dmc.tick(Direction.DOWN);
        assertEquals(true, p.isInvisible());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(false, p.isInvisible());
    }
}
