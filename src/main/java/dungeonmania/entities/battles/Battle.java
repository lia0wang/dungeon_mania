package dungeonmania.entities.battles;

import java.util.List;

import java.util.ArrayList;

import org.json.JSONObject;

import dungeonmania.response.models.*;
import dungeonmania.entities.moving.*;
import dungeonmania.entities.*;

public class Battle {
    private Entity enemy;
    private double initialPlayerHealth;
    private double initialEnemyHealth = 0.0;
    private double playerDamage;
    private double enemyDamage;
    private List<Round> rounds = new ArrayList<Round>();
    private Dungeon dungeon;
    private JSONObject configs;

    /**
     * Constructor for Battles
     *
     * @param enemy
     * @param dungeon
     * @param configs
     */
    public Battle(Entity enemy, Dungeon dungeon, JSONObject configs) {
        this.enemy = enemy;
        this.initialPlayerHealth = configs.getDouble("player_health");
        switch (enemy.getType()) {
            case "spider":
                this.initialEnemyHealth = configs.getDouble("spider_health");
            case "zombie_toast":
                this.initialEnemyHealth = configs.getDouble("zombie_health");
            case "mercenary":
                this.initialEnemyHealth = configs.getDouble("mercenary_health");
        }
        
        this.dungeon = dungeon;
        this.configs = configs;
    }

    /**
     * Resolves the battle, creates the required rounds needed to end the battle.
     *
     */
    public void resolveBattle() {
        Double currPlayerHealth = initialPlayerHealth;
        Double currEnemyHealth = initialEnemyHealth;

        while (currPlayerHealth > 0 && currEnemyHealth > 0) {
            //rounds.add(new Round(deltaPlayerHealth, deltaEnemyHealth))
        }
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
        return new BattleResponse(enemy, roundResponses, initialPlayerHealth, initialEnemyHealth);
    }
}
