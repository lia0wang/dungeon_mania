package dungeonmania.entities.battles;

import java.util.List;
import java.util.ArrayList;

import dungeonmania.entities.collectable.*;
import dungeonmania.response.models.*;

public class Round {
    private double deltaPlayerHealth;
    private double deltaEnemyHealth;
    private List<CollectableEntity> weaponryUsed = new ArrayList<CollectableEntity>();

    /**
     * Constructor for Rounds
     *
     * @param deltaPlayerHealth
     * @param deltaEnemyHealth
     */
    public Round(double deltaPlayerHealth, double deltaEnemyHealth, ArrayList<CollectableEntity> weaponsUsed) {
        this.deltaPlayerHealth = deltaPlayerHealth;
        this.deltaEnemyHealth = deltaEnemyHealth;
        weaponryUsed = weaponsUsed;
    }

    /**
     * Response for Rounds
     *
     * @return round response
     */
    public RoundResponse getRoundResponse() {
        List<ItemResponse> weaponResponse = new ArrayList<>();
        for (CollectableEntity c : weaponryUsed) {
            weaponResponse.add(c.getItemResponse());
        }
        return new RoundResponse(deltaPlayerHealth, deltaEnemyHealth, weaponResponse);
    }
}
