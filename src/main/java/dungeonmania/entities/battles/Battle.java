package dungeonmania.entities.battles;

import java.util.List;

import java.util.ArrayList;

import org.json.JSONObject;

import dungeonmania.response.models.*;
import dungeonmania.entities.moving.*;
import dungeonmania.entities.*;
import dungeonmania.entities.buildable.*;
import dungeonmania.entities.collectable.CollectableEntity;
import dungeonmania.entities.collectable.Sword;

public class Battle {
    private Entity enemy;
    private double initialPlayerHealth;
    private double initialEnemyHealth = 0.0;
    private double playerDamage;
    private double enemyDamage;
    private List<Round> rounds = new ArrayList<Round>();
    private ArrayList<CollectableEntity> weaponsUsed;
    private Player player;
    private JSONObject configs;
    private boolean playerIsAlive = true;

    /**
     * Constructor for Battles
     *
     * @param enemy
     * @param player
     * @param configs
     */
    public Battle(Entity enemy, Player player, JSONObject configs) {
        this.enemy = enemy;
        initialPlayerHealth = configs.getDouble("player_health");
        playerDamage = configs.getDouble("player_attack");
        switch (enemy.getType()) {
            case "zombie_toast":
                initialEnemyHealth = configs.getDouble("zombie_health");
                enemyDamage = configs.getDouble("zombie_attack");
            default:
                initialEnemyHealth = configs.getDouble(enemy.getType() + "_health");
                enemyDamage = configs.getDouble(enemy.getType() + "_attack");
        }
        
        weaponsUsed = player.getInventory().getCurrentWeapons();
        for (CollectableEntity e : weaponsUsed) {
            switch (e.getType()) {
                case "sword":
                    playerDamage += configs.getDouble("sword_attack");
                    Sword sword = (Sword) e;
                    sword.usedInBattle(player);
                case "midnight_armour":
                    playerDamage += configs.getDouble("midnight_armour_attack");
                    enemyDamage -= configs.getDouble("midnight_armour_defence");
                case "bow":
                    playerDamage = playerDamage * 2;
                    Bow bow = (Bow) e;
                    bow.usedInBattle(player);
                case "shield":
                    enemyDamage -= configs.getDouble("shield_defence");
                    Shield shield = (Shield) e;
                    shield.usedInBattle(player);
                
            }
        }

        if (enemyDamage < 0) {
            enemyDamage = 0;
        }

        this.player = player;
        this.configs = configs;
        resolveBattle();
    }

    /**
     * Resolves the battle, creates the required rounds needed to end the battle.
     *
     */
    public void resolveBattle() {
        Double currPlayerHealth = initialPlayerHealth;
        Double currEnemyHealth = initialEnemyHealth;

        while (currPlayerHealth > 0 && currEnemyHealth > 0) {
            rounds.add(new Round(currPlayerHealth - (enemyDamage/10), currEnemyHealth - (playerDamage/5), weaponsUsed));
        }

        if (currPlayerHealth <= 0) {
            playerIsAlive = false;
        }
    }

    /**
     * Check to see if player has survived a battle
     *
     * @return playerIsAlive boolean
     */
    public boolean getPlayerStatus() {
        return playerIsAlive;
    }

    /**
     * Response for Battles
     *
     * @return battle response
     */
    public BattleResponse getBattleResponse() {
        List<RoundResponse> roundResponses = new ArrayList<>();
        for (Round r : rounds) {
            roundResponses.add(r.getRoundResponse());
        }
        return new BattleResponse(enemy.getType(), roundResponses, initialPlayerHealth, initialEnemyHealth);
    }
}
