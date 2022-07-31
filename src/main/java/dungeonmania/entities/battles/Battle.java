package dungeonmania.entities.battles;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;

import dungeonmania.response.models.*;
import dungeonmania.entities.moving.*;
import dungeonmania.entities.*;
import dungeonmania.entities.collectable.CollectableEntity;

public class Battle {
    private MovingEntity enemy;
    private double PlayerHealth;
    private double EnemyHealth = 0.0;
    private double playerDamage;
    private double enemyDamage;
    private List<Round> rounds = new ArrayList<Round>();
    private ArrayList<CollectableEntity> weaponsUsed;
    private Player player;
    private boolean playerIsAlive = true;
    private boolean isInvincible = false;

    /**
     * Constructor for Battles
     *
     * @param enemy
     * @param player
     * @param configs
     */
    public Battle(MovingEntity enemy, Dungeon dungeon, JSONObject configs) {
        this.enemy = enemy;
        this.player = dungeon.getPlayer();
        PlayerHealth = player.getHealth();
        playerDamage = player.getAttack();
        EnemyHealth = enemy.getHealth();
        enemyDamage = enemy.getAttack() - player.getDamageReduction();
        if (enemyDamage < 0) {
            enemyDamage = 0;
        }

        weaponsUsed = player.getInventory().getCurrentWeapons();

        if (player.getPlayerState() instanceof InvincibleState) {
            isInvincible = true;
            enemyDamage = 0;
        }
        resolveBattle(dungeon);
    }

    /**
     * Resolves the battle, creates the required rounds needed to end the battle.
     *
     */
    public void resolveBattle(Dungeon dungeon) {
        Double currPlayerHealth = PlayerHealth;
        Double currEnemyHealth = EnemyHealth;

        while (currPlayerHealth > 0 && currEnemyHealth > 0) {
            currEnemyHealth -= (playerDamage/5);
            currPlayerHealth -= (enemyDamage/10);
            rounds.add(new Round(-(enemyDamage/10), -(playerDamage/5), weaponsUsed));
            if (isInvincible) {
                break;
            }
        }

        if (currPlayerHealth <= 0) {
            playerIsAlive = false;
        } else if (currEnemyHealth <= 0) {
            player.setHealth(currPlayerHealth);
            dungeon.getEnemies().remove(enemy);
        } else {
            player.setHealth(currPlayerHealth);
            enemy.setHealth(currEnemyHealth);
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
        return new BattleResponse(enemy.getType(), roundResponses, PlayerHealth, EnemyHealth);
    }
}
