package dungeonmania.entities.moving;

import java.util.ArrayList;

import org.json.JSONObject;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.collectable.Key;
import dungeonmania.util.Direction;
import dungeonmania.entities.buildable.*;
import dungeonmania.entities.collectable.CollectableEntity;
import dungeonmania.entities.collectable.Sword;

public class Player extends MovingEntity {
    private Inventory inventory;
    private MovementBehaviour movement;
    private PlayerState playerState;
    private double attack;
    private double health;
    
    public Player(int x, int y, Dungeon dungeon) {
        super(x, y, "player", dungeon);
        this.inventory = new Inventory();
        this.playerState = new DefaultState();
        this.movement = new PlayerMovement();
        this.attack = dungeon.getConfigs().getInt("player_attack");
        this.health = dungeon.getConfigs().getInt("player_health");
    }
    
    public MovementBehaviour getMovement() {
        return movement;
    }    

    public void move(Direction direction) {
        movement.move(direction, this);
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public PlayerState getPlayerState() {
        return this.playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public boolean isInvincible() {
        if (this.playerState instanceof InvincibleState) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInvisible() {
        if (this.playerState instanceof InvisibleState) {
            return true;
        } else {
            return false;
        }
    }

    public Key getKeyInInventory(int keyId) {
        return getInventory().getKeyByKeyId(keyId);
    }

    /**
     * Get player attack
     *
     * @return attack
     */
    public double getAttack() {
        double baseAttack = attack;
        ArrayList<CollectableEntity> weaponsUsed = getInventory().getCurrentWeapons();
        JSONObject configs = dungeon.getConfigs();
        for (CollectableEntity e : weaponsUsed) {
            switch (e.getType()) {
                case "sword":
                    baseAttack += configs.getDouble("sword_attack");
                    Sword sword = (Sword) e;
                    sword.usedInBattle(this);
                    continue;
                case "midnight_armour":
                    baseAttack += configs.getDouble("midnight_armour_attack");
                    continue;
                case "bow":
                    baseAttack = baseAttack * 2;
                    Bow bow = (Bow) e;
                    bow.usedInBattle(this);
                    continue;
            }
        }
        return baseAttack;
    }

    /**
     * Get damage reduction from player items
     *
     * @return damageReduction
     */
    public double getDamageReduction() {
        double damageReduction = 0.0;
        ArrayList<CollectableEntity> weaponsUsed = getInventory().getCurrentWeapons();
        JSONObject configs = dungeon.getConfigs();
        for (CollectableEntity e : weaponsUsed) {
            switch (e.getType()) {
                case "midnight_armour":
                    damageReduction += configs.getDouble("midnight_armour_defence");
                    continue;
                case "shield":
                    damageReduction += configs.getDouble("shield_defence");
                    Shield shield = (Shield) e;
                    shield.usedInBattle(this);
                    continue;
            }
        }
        return damageReduction;
    }

    /**
     * Get player health
     *
     * @return health
     */
    public double getHealth() {
        return health;
    }

    /**
     * Set player health
     *
     * @param health
     */
    public void setHealth(double health) {
        this.health = health;
    }
}
