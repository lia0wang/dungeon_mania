package dungeonmania.entities.staticEntity;

import java.util.HashMap;

import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.MovingEntity;

public class SwampTile extends Entity{
    private int movementFactor;
    private HashMap<MovingEntity, Integer> movementMap = new HashMap<MovingEntity, Integer>();

    public SwampTile(int x, int y, String type, int factor) {
        super(x, y, type);
        this.movementFactor = factor;
    }

    /*
     * Player attempts to move
     * 
     */
    public boolean tryToMove(MovingEntity entity) {
        if (!movementMap.containsKey(entity)) {
            movementMap.put(entity, movementFactor);
            return false;
        } else {
            if (movementMap.get(entity) > 0) {
                movementMap.put(entity, movementMap.get(entity) - 1);
                return false;
            }
            return true;
        }
    }
}