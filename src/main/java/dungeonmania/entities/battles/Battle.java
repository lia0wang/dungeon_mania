package dungeonmania.entities.battles;

import java.util.List;
import java.util.ArrayList;

import dungeonmania.response.models.*;
import dungeonmania.entities.moving.*;

public class Battle {
    private MovingEntity enemy;
    private double initialPlayerHealth;
    private double initialEnemyHealth;
    private List<Round> rounds = new ArrayList<Round>();

    /**
     * Constructor for Battles
     *
     * @param enemy
     * @param initialPlayerHealth
     * @param initialEnemyHealth
     */
    public Battle(MovingEntity enemy, double initialPlayerHealth, double initialEnemyHealth) {
        this.enemy = enemy;
        this.initialPlayerHealth = initialPlayerHealth;
        this.initialEnemyHealth = initialEnemyHealth;
    }

    /**
     * Add a round to the battle
     *
     * @param round
     */
    public void addRound(Round newRound) {
        this.rounds.add(newRound);
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
